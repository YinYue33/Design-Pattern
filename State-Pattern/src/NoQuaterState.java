public class NoQuaterState extends State {

    private static State noQuaterState = new NoQuaterState();

    private NoQuaterState(){};

    public void insertQuarter(GumballMachine gumballMachine){
        gumballMachine.setState(HasQuaterState.getInstance());
    };

    public static State getInstance(){
        return noQuaterState;
    };
}
