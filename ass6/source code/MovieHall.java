import java.util.ArrayList;
import java.util.List;

public class MovieHall {
    private  int id=0;
    private  static int count=1;

    public List<Movie> getMovies() {
        return movies;
    }



    private List<Movie> movies;
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private int capacity;

    public MovieHall() {
        movies=new ArrayList<>();
        id=id+count;
        count++;
    }
    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return id+"-"+capacity;
    }
}
