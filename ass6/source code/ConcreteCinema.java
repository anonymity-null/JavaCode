import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConcreteCinema implements Cinema{
    List<Movie> movies;
    List<MovieHall> movieHalls;
    private int count=1;

    ConcreteCinema(){
        movies = new ArrayList<>();
        movieHalls=new ArrayList<>();
    }

    @Override
    public void addMovieHall(int capacity) {
        MovieHall movieHall=new MovieHall();
        movieHall.setCapacity(capacity);
        movieHalls.add(movieHall);
    }

    @Override
    public List<String> getAllMovieHallsCapacity() {
        List<String> hallsCapacity=new ArrayList<>();
        for (MovieHall m : movieHalls) {
            hallsCapacity.add(m.toString());
        }
        return hallsCapacity;
    }

    @Override
    public void addMovie(String name, int runtime, int hallNumber, double price, int type, Time startTime) {
        Movie movie;
        MovieHall movieHall=movieHalls.get(hallNumber-1);
        if (type==0){
            movie=new OrdinaryMovie();

        }else {
            movie=new ThreeDMovie();

        }
        movie.setId(count);
        movie.setName(name);
        movie.setRuntime(runtime);
        movie.setPrice(price);
        movie.setStartTime(startTime);
        movie.setHallNumber(hallNumber);
        movie.setTicketsLeft(movieHall.getCapacity());

        boolean b=true;

        if (movieHall.getMovies().size()>0){
            for (Movie m : movieHall.getMovies()) {
                /*System.out.println("指针位置电影："+(m.getEndTime() + 20) +" 比较 加入电影:"+startTime.toMinute()
                        +"--开始时间:"+m.getStartTime()+" 时长："+m.getRuntime()+" 加入电影开始时间："+startTime);*/

                if ((m.getEndTime() + 20 > startTime.toMinute()&&m.getStartTime().toMinute()<startTime.toMinute())||
                        (startTime.toMinute()+runtime+20>m.getStartTime().toMinute()&&startTime.toMinute()<m.getStartTime().toMinute())) {
                    b=false;
                    break;
                }
            }
        }

        if (b){
            movies.add(movie);
            movieHall.getMovies().add(movie);
            count++;
        }

    }

    @Override
    public List<Movie> getAllMovies() {
        return movies;
    }

    @Override
    public List<Movie> getMoviesFromMovieHallOrderByStartTime(int hallNumber) {
        //List<Movie> result=new ArrayList<>();
        List<Movie> getMovies=movieHalls.get(hallNumber - 1).getMovies();
        Collections.sort(getMovies);

        return getMovies;
    }

    @Override
    public double reserveMovie(int movieId, int arg) {
        Movie movie=movies.get(movieId-1);
        //String name = movie.getClass().getName();
        return movie.purchase(arg);

    }

    @Override
    public Movie getMovieById(int movieId) {
        return movies.get(movieId-1);
    }

    @Override
    public double getOneMovieIncome(int movieId) {
        Movie movie = movies.get(movieId - 1);
        return movie.getIncome();
    }

    @Override
    public double getTotalIncome() {
        double a=0;
        for (Movie m :
                movies) {
            a+=m.income;
        }

        return a;
    }

    @Override
    public List<Movie> getAvailableMoviesByName(Time currentTime, String name) {
        List<Movie> result=new ArrayList<>();
        for (Movie m : movies) {
            System.out.println(m);
        }
        for (Movie m : movies) {
            if (m.getName().equals(name)){
                if (m.getStartTime().getMinute()>currentTime.getMinute()&&m.getTicketsLeft()>0){
                    result.add(m);
                }
            }
        }
        return result;
    }
}
