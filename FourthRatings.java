import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    public double getAverageByID(String movieid, int minRater){
        int raterNumb = 0;
        double total = 0;
        RaterDatabase.initialize("ratings.csv");
        for(Rater rater:RaterDatabase.getRaters()){
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
    
    private double dotProduct(Rater me,Rater other){
        ArrayList<String> myReviews = me.getItemsRated();
        double dotProduct = 0;
        for(String review:myReviews){
            if(other.hasRating(review)){
                double myShift = me.getRating(review)-5;
                double otherShift = other.getRating(review)-5;
                dotProduct = dotProduct+ myShift*otherShift;
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        RaterDatabase.initialize("ratings.csv");
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        Rater myRater = RaterDatabase.getRater(id);
        for(Rater otherRater:RaterDatabase.getRaters()){
            if(otherRater!=myRater){
                double dot = dotProduct(myRater,otherRater);
                if(dot>0){
                    Rating rating = new Rating(otherRater.getID(),dot);
                    similarRatings.add(rating);
                }
            }
        }
        Collections.sort(similarRatings,Collections.reverseOrder());
        return similarRatings;
    }
    
    public ArrayList<Rating> getSimilarRatings(String raterId, int numRaters, int minRaters){
        return getSimilarRatingsByFilter(raterId,numRaters,minRaters,new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterId, int numRaters, int minRaters,Filter f){
        ArrayList<Rating> similarRaters = getSimilarities(raterId);
        ArrayList<Rating> similarRating = new ArrayList<Rating>();
        if(numRaters<=similarRaters.size()){
            ArrayList<String> movieids = MovieDatabase.filterBy(f);
            for(String movieid:movieids){
                int raterNumb = 0;
                double total = 0;
                for(int i =0;i<numRaters;i++){
                    Rater currentRater = RaterDatabase.getRater(similarRaters.get(i).getItem());
                    double weight = similarRaters.get(i).getValue();
                    if(currentRater.hasRating(movieid)){
                        raterNumb ++;
                        total = total+currentRater.getRating(movieid)*weight;
                    }
                }
                if(raterNumb>=minRaters&&raterNumb!=0){
                    Rating currRating = new Rating(movieid,total/raterNumb);
                    similarRating.add(currRating);
                }
            }
        }
        return similarRating;
    }
}
