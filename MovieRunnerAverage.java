import java.util.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void testAverageRatings(){
        int minRater = 12;
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
        ArrayList<Rating> average = sr.getAverageRatings(minRater);
        Collections.sort(average);
        for(Rating rating:average){
            System.out.println(rating.getValue()+"\t"+sr.getTitleByID(rating.getItem()));
        }
    }
    
    public void testAverageOneMovie(){
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv","ratings.csv");
        String title = "Vacation";
        int minRater = 0;
        double average = sr.getAverageByID(sr.getIdByTitle(title), minRater);
        System.out.println(average +"\t"+title);
    }
}
