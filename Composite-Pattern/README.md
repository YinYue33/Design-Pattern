# Composite Pattern

## Motivation
As a menu designer in a fancy reteraurant in Manhatton, you decided to add a dessert sub menu to the Dinner menu to attract those who addicted to desserts. Dessert menu should be a menu and a menu item of Dinner Menu.
[sub dessert menu](Submenu.jgp)

## Goal
What we want is taken dessert menu as a menu and a menu item at the same time. Similar cases includes: 
* In graphic application, we want to treat a component and a group of components the same (such as you group some shapes and move them as it is just one component)
* Equitments are often organized into part-whole hierarchies. Chassis contain drices and planar boards, a bus contains cards, a cabinet contains chassis, buses and so forth

## Solution
### Interface for both menu item and menu
```Java
public abstract class MenuComponent {
    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }
    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }
    public MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }
    public String getName() {
        throw new UnsupportedOperationException();
    }
    public double getPrice() {
        throw new UnsupportedOperationException();
    }
    public void print() {
        throw new UnsupportedOperationException();
    }
    Iterator<MenuComponent> createIterator(){
        throw new UnsupportedOperationException();
    }
}
```
### Menu Item and Menu only override functions they need
```Java
public class MenuItem extends MenuComponent {
    String name;
    double price;
    public MenuItem(String name, double price)
    {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public void print() {
        System.out.print(“ “ + getName());
        System.out.println(“, “ + getPrice());
    }

    public Iterator<MenuComponent> createIterator() {
        return new NullIterator<>();
    }
}

public class Menu extends MenuComponent {
    ArrayList<MenuComponent> menuComponents = new ArrayList<>();
    String name;
    public Menu(String name) {
        this.name = name;
    }
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }
    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }
    public String getName() {
        return name;
    }

    public void print() {
        System.out.print(“\n” + getName());
        System.out.println(“---------------------”);
        Iterator<MenuComponent> iterator = menuComponents.iterator();
        while (iterator.hasNext()) {
            MenuComponent menuComponent = iterator.next();
            menuComponent.print();
        }
    }

    public Iterator<MenuComponent, Menu> createIterator() {
        return new CompositeIterator<>(menuComponents.iterator());
    }
}
```
### Iterator, very important!!
```Java
public class CompositeIterator<C, E> implements Iterator<C> { 
    //C stands for component, E stands for composite item ( like menu )
    Stack<Iterator<C>> stack = new Stack<>();
    public CompositeIterator(Iterator<C> iterator) {
        stack.push(iterator);
    }
    public C next() {
        if (hasNext()) {
            Iterator<C> iterator = stack.peek();
            C component = iterator.next();
            if (component instanceof E) {
            stack.push(component.createIterator());
        }
            return component;
        } else {
            return null;
        }
    }
    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        } else {
            Iterator<C> iterator = stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}

public class NullIterator<C> implements Iterator<C> {
    public C next() {
        return null;
    }
    public boolean hasNext() {
        return false;
    }
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
```
### Client1: iterator all items in the menu, print it if it is a menu item
This is an external iterator, can be used to iterator each element in the menu
```Java
public void printMenu(Menu menu) {
    Iterator<MenuComponent> iterator = menu.createIterator();
    System.out.println(“\n MENU\n----”);
    while (iterator.hasNext()) {
    MenuComponent menuComponent = iterator.next();
    if (menuComponent instanceof menuItem) {
            menuComponent.print();
    }
}
```

### Client2: use recursion
```Java
public void printMenu(Menu menu) {
    menu.print();
}
```
## Definition
The Composite Pattern allows you to compose objects into tree structures( see the picture above) to represent part-whole hierarchies. Composite lets clients treat individual objects (menu item) and compositions of objects (menu) uniformly.