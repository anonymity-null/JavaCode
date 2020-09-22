public abstract class Movie implements Comparable<Movie>{
    //Must declare first six fields
    private int id;
    private String name;
    private Time startTime;

    @Override
    public int compareTo(Movie m) {
        if (startTime.toMinute()>m.getStartTime().toMinute()){
            return 1;
        }return -1;
    }

    public int getEndTime() {
        return (startTime.getHour() * 60) + startTime.getMinute() + runtime;
    }


    private int runtime;
    private double price;

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

     double income=0;

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    private int hallNumber;

    public void setTicketsLeft(int ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }

    protected int ticketsLeft;
    private static int count=1;

    /*Movie(){
        id=id+count;
        count++;
    }*/


    public int getTicketsLeft() {
        return ticketsLeft;
    }

    //You can add other fields that you think are necessary.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract double purchase(int arg);

    @Override
    public String toString() {
        return
                "id=" + id + ", name='" + name +
                        "', startTime:" + startTime +
                        ", runtime=" + runtime +
                        ", price=" + price +
                        ", ticketsLeft=" + ticketsLeft;
    }
}
