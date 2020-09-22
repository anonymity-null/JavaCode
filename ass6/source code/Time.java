public class Time {

    public int getHour() {
        return hour;
    }

    public int getMinute(){
        return minute;
    }
    public int toMinute(){
        return hour*60+minute;
    }

    public boolean isThan(Time time){

        if (this.hour>time.hour){
            System.out.println("this.hour="+this.hour);
            System.out.println("time.hour="+time.hour);
            return true;
        }else if (this.hour==time.hour){
            if (this.minute>time.minute){
                System.out.println("this.minute="+this.minute);
                System.out.println("time.minute="+time.minute);
                return true;
            }
        }else {
            System.out.println("this.hour="+this.hour);
            System.out.println("time.hour="+time.hour);
        }
        return false;
    }

    public int setHour(int hour) {
        return this.hour + hour;
    }

    public int  setMinute(int minute) {
        return this.minute + minute;
    }

    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        return String.format("%02d",hour)+":"+String.format("%02d",minute);
    }
}
