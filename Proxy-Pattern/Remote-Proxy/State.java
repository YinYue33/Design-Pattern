import java.io.*;

public abstract class State implements Serializable {

    public void insertQuarter(GumballMachine gumballMachine){};
    public void ejectQuarter(GumballMachine gumballMachine){};
    public void turnCrank(GumballMachine gumballMachine){};
    public void dispense(GumballMachine gumballMachine){};
    public void refill(GumballMachine gumballMachine){};

}
