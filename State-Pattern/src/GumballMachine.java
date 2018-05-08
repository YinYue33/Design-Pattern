public class GumballMachine {
    private State state;
    private int count;

    public GumballMachine(int count){
        this.count = count;
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

    public int getCount(){
        return count;
    }


}
