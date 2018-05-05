# Iterator Pattern

## Motivation
You used different data structures to store morning menu items and dinner menu items, and the client wants to print all items in both menus. 

## Problem
A while loop is necessary to get all items from each data structure, which will end up with duplicated codes. Also, what if we want all items that meet certain criteria? Another function would be needed.

## Goal
Find a universal way to iterate different data structures

## Solution
Encapulate each data structure in a iterator, implement hasNext() and next() methods to get the next available elements in the structure

### An array iterator
```Java
public interface Iterator<E> {
    boolean hasNext();
    E next();
    void remove(); // optional, remove last element returned by next()
}
```
```Java
public class ArrayIterator<E> implements Iterator<E> {
    Object[] items;
    int position = 0;
    public ArrayIterator(Object[] items) {
        this.items = items;
    }
    public E next() {
        E element = (E)items[position];
        position = position + 1;
        return element ;
    }
    public boolean hasNext() {
        if (position >= items.length || items[position] == null) {
            return false;
        } else {
            return true;
        }
    }

    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException
            (“You can’t remove an item until you’ve done at least one next()”);
        }
        if (list[position-1] != null) {
            for (int i = position-1; i < (list.length-1); i++) {
                list[i] = list[i+1];
            }
            list[list.length - 1] = null;
        }
    }
}
```

```Java
client
private void printAll(Iterator<E> iterator) {
    while (iterator.hasNext()) {
        System.out.print(iterator.next());
    }
}
```

## Definition
The Iterator Pattern provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.