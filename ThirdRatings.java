
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String raterfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(raterfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public ArrayList<Rating> getAverageRatings(int minRater){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> movieids = MovieDatabase.filterBy(new TrueFilter());
        for(String movieid:movieids){
            double average = getAverageByID(movieid,minRater);
            if(average != 0){
                Rating rating = new Rating(movieid,average);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(Filter f, int minRater){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> movieids = MovieDatabase.filterBy(f);
        for(String movieid:movieids){
            double average = getAverageByID(movieid,minRater);
            if(average != 0){
                Rating rating = new Rating(movieid,average);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
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