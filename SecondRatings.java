
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile,String raterfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(raterfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minRater){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for(Movie movie:myMovies){
            String movieid = movie.getID();
            double average = getAverageByID(movieid,minRater);
            if(average != 0){
                Rating rating = new Rating(movieid,average);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }
    
    public String getTitleByID(String movieid){
        for(Movie movie:myMovies){
            if(movie.getID().equals(movieid)){
                return movie.getTitle();
            }
        }
        return "Movie ID not found!";
    }
    
    public String getIdByTitle(String title){
        for(Movie movie:myMovies){
            if(movie.getTitle().equals(title)){
                return movie.getID();
            }
        }
        return "No Such Title";
    }
    
    public double getAverageByID(String movieid, int minRater){
        int raterNumb = 0;
        double total = 0;
        for(Rater rater:myRaters){
            if(rater.hasRating(movieid)){
                raterNumb ++;
                total += rater.getRating(movieid);
            }
        }
        
        if(raterNumb >= minRater&&raterNumb!=0){
            return total/raterNumb;
        }
        return 0.0;
    }
}