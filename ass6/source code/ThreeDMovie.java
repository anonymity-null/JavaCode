public class ThreeDMovie extends Movie{
    private final int GLASS_PRICE = 20;



    @Override
    public String toString() {
        return super.toString()+" ThreeDMovie" ;
    }

    @Override
    public double purchase(int arg) {
        if (ticketsLeft>0) {
            ticketsLeft-=1;
            if (arg==1){
                income+=GLASS_PRICE+super.getPrice();
                //glass_count++;
                return GLASS_PRICE+super.getPrice();
            }else {
                income += super.getPrice();
                return super.getPrice();
            }
        }else {
            income+=0;
            return 0;
        }


    }
}
