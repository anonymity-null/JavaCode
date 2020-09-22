public class OrdinaryMovie extends Movie{

    @Override
    public String toString() {
        return super.toString()+" OrdinaryMovie";
    }

    @Override
    public double purchase(int arg) {
        //System.out.println("余票："+super.getTicketsLeft());

        if (super.getTicketsLeft()<arg){
            income+=super.getTicketsLeft()*super.getPrice();
            double price=super.getTicketsLeft()*super.getPrice();
            ticketsLeft-=super.getTicketsLeft();
            //setIncome(income);
            return price;
        }else {
            income+=arg*super.getPrice();
            ticketsLeft-=arg;
            //setIncome(income);
            return arg*super.getPrice();
        }

    }
}
