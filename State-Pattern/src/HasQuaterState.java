public class HasQuaterState extends State {
    private static State hasQuaterState = new HasQuaterState();

    private HasQuaterState(){};

    public void ejectQuarter(GumballMachine gumballMachine){
        gumballMachine.setState(NoQuaterState.getInstance());
    };

    public void turnCrank(GumballMachine gumballMachine){
        gumballMachine.setState(SoldState.getInstance());
    };

    public static State getInstance(){
        return hasQuaterState;
    };
}
