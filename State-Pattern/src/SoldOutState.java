public class SoldOutState extends State {
    private static State soldOutState = new SoldOutState();

    private SoldOutState(){};

    public void refill(GumballMachine gumballMachine){
        if(gumballMachine.getCount() > 0){
            gumballMachine.setState(NoQuaterState.getInstance());
        }
    };

    public static State getInstance(){
        return soldOutState;
    };
}
