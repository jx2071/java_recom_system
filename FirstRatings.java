import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRating here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    private ArrayList<Movie> movieData;
    private ArrayList<Rater> raterData;
    public FirstRatings(){
        movieData = new ArrayList<Movie>();
        raterData = new ArrayList<Rater>();
    }
    
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for(CSVRecord rd:parser){
            //Gathering information of one movie
            String id = rd.get(0);
            String title = rd.get(1);
            String year = rd.get(2);
            String country = rd.get(3);
            String genre = rd.get(4);
            String director = rd.get(5);
            int minutes = Integer.parseInt(rd.get(6));
            String poster = rd.get(7);
            //Creatng a movie object storeing all information
            Movie movie = new Movie(id,title,year,genre,director,country,poster,minutes);
            movies.add(movie);
        }
        System.out.println("Loaded "+movies.size()+" Movies.");
        return movies;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource("data/"+filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> raters = new ArrayList<Rater>();
        for(CSVRecord rd:parser){
            //Getting information of the current CSV line
            String raterid = rd.get(0);
            String movieid = rd.get(1);
            double rating = Double.parseDouble(rd.get(2));
            int index = findRater(raterid,raters);
            //If the rater is already in the list, get the rater and add rating
            if(index != -1){
                Rater rater = raters.get(index);
                rater.addRating(movieid,rating);
                raters.set(index,rater);
            }
            //If not in the list, create new a rater and add rating
            else{
                Rater rater = new PlainRater(raterid);
                rater.addRating(movieid,rating);
                raters.add(rater);
            }
        }
        System.out.println("Loaded "+raters.size()+" Raters.");
        return raters;
    }
    
    public int findRater(String raterid,ArrayList<Rater> raters){
        boolean find = false;
        int index = 0;
        for(Rater currentRater:raters){
                if(currentRater.getID().equals(raterid)){
                    find = true;
                    break;
                }
                index++;
        }
        if(find){
            return index;
        }
        return -1;
    }
    
    public ArrayList<Movie> filterByGenre(String genre){
        ArrayList<Movie> filtered = new ArrayList<Movie>();
        for(Movie movie:movieData){
            if(movie.getGenres().toLowerCase().contains(genre.toLowerCase())){
                filtered.add(movie);
            }
        }
        System.out.println(filtered.size()+" movies are "+genre);
        return filtered;
    }
    
    public ArrayList<Movie> filterByLength(int length){
        ArrayList<Movie> filtered = new ArrayList<Movie>();
        for(Movie movie:movieData){
            if(movie.getMinutes()>length){
                filtered.add(movie);
            }
        }
        System.out.println(filtered.size()+" movies are more than "+length+" minutes");
        return filtered;
    }
    
    public void movieDirectorMax(){
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        for(Movie movie:movieData){
            String[] directors = movie.getDirector().split(",");
            for(int i =0;i<directors.length;i++){
                if(!map.containsKey(directors[i])){
                    ArrayList<String> directedMovies = new ArrayList<String>();
                    directedMovies.add(movie.getTitle());
                    map.put(directors[i],directedMovies);
                }
                else{
                    ArrayList<String> directedMovies = map.get(directors[i]);
                    directedMovies.add(movie.getTitle());
                    map.put(directors[i],directedMovies);
                }
            }
        }
        int max = 0;
        String name = "";
        for(String director:map.keySet()){
            if(map.get(director).size()>max){
                name = director;
                max = map.get(director).size();
            }
        }
        System.out.println(name+" directed most movies: " + max);
    }
    
    public ArrayList<Rating> findRatings(String raterid){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        int index = findRater(raterid,raterData);
        if(index!=-1){
            ratings = raterData.get(index).getRatings();
        }
        System.out.println("Rater "+raterid+" has rated "+ratings.size()+" movies");
        return ratings;
    }
    
    public void findRaterWithMostRatings(){
        String id = "";
        int max = 0;
        for(Rater rater:raterData){
            if(rater.getRatings().size()>max){
                id = rater.getID();
                max = rater.getRatings().size();
            }
        }
        System.out.println("Rater "+id+" has the most ratings: "+findRatings(id).size());
    }
    
    public void findRatingsOfMovie(String movieid){
        ArrayList<Rater> ratedRaters = new ArrayList<Rater>();
        for(Rater rater:raterData){
            if(rater.hasRating(movieid)){
                ratedRaters.add(rater);
            }
        }
        System.out.println("Movie "+movieid+" is rated by "+ratedRaters.size()+" raters");
    }
    
    public void findAllMoviesRated(){
        ArrayList<String> moviesRated = new ArrayList<String>();
        for(Rater rater:raterData){
            ArrayList<String> movieOfRater = rater.getItemsRated();
            movieOfRater.removeAll(moviesRated);
            moviesRated.addAll(movieOfRater);
        }
        System.out.println(moviesRated.size()+" movies are rated");
    }
    
    /*
    public void testLoadMovies(){
        //String filename = "ratedmovies_short.csv";
        String filename = "ratedmoviesfull.csv";
        movieData = loadMovies(filename);
    }
    
    public void testLoadRaters(){
        //String filename = "ratings_short.csv";
        String filename = "ratings.csv";
        raterData = loadRaters(filename);
    }
    
    public void testFilterByGenre(String genre){
        ArrayList<Movie> filtered = filterByGenre(genre);
        //for(Movie movie:filtered){
        //  System.out.println(movie.getTitle()+"\t"+movie.getGenres());
        //}
    }
    
    public void testFilterByLength(int length){
        ArrayList<Movie> filtered = filterByLength(length);
        //for(Movie movie:filtered){
        //    System.out.println(movie.getTitle()+"\t"+movie.getMinutes());
        //}
    }
    
    public void testMostMovies(){
        movieDirectorMax();
    }
    
    public void testFindRaterRatings(String raterid){
        //String raterid = "2";
        ArrayList<Rating> ratings = findRatings(raterid);
    }
    
    public void testFindRaterWithMostRatings(){
        findRaterWithMostRatings();
    }
    
    public void testFindRatingsOfMovie(String movieid){
        //String movieid = "1798709";
        findRatingsOfMovie(movieid);
    }
    
    public void testFindAllMoviesRated(){
        findAllMoviesRated();
    }
    */
}
