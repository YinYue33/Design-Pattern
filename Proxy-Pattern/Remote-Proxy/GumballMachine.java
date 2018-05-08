import java.rmi.*;
import java.rmi.server.*;

public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote{
    private State state;
    private int count;
    private String location;

    public GumballMachine(String location, int numberGumballs) throws RemoteException {
        this.location = location;
        this.count = numberGumballs;
    }

    public int getCount() {
        return count;
    }
    public State getState() {
        return state;
    }
    public String getLocation() {
        return location;
    }

    public void insertQuarter() {
        state.insertQuarter(this);
    }
    public void ejectQuarter() {
        state.ejectQuarter(this);
    }
    public void turnCrank() {
        state.turnCrank(this);
        state.dispense(this);
    }

    public void refill(int count){
        this.count += count;
        state.refill(this);
    }


    //use protect to constrain that only State can call this method
    protected void setState(State state) {
        this.state = state;
    }

    protected void dispense() {
        if (count != 0) {
            count = count - 1;
        }
    }


}
