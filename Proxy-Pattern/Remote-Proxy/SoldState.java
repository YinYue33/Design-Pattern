public class SoldState extends State {

    private static State soldState = new SoldState();

    private SoldState(){};

    public void dispense(GumballMachine gumballMachine){
        gumballMachine.dispense();
        if(gumballMachine.getCount() == 0){
            gumballMachine.setState(HasQuaterState.getInstance());
        }else{
            gumballMachine.setState(NoQuaterState.getInstance());
        }
    };

    public static State getInstance(){
        return soldState;
    };
}
