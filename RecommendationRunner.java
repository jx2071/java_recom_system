import java.util.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate(){
        ArrayList<String> allMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<String> copy = new ArrayList<String>(allMovies);
        Random rand = new Random();
        ArrayList<String> moviesToRate = new ArrayList<String>();
        for(int i=0;i<10;i++){
            int index = rand.nextInt(allMovies.size()-i);
            String movieId = copy.get(index);
            moviesToRate.add(movieId);
            copy.remove(index);
        }
        return moviesToRate;
    }
    
    public void printRecommendationsFor(String raterId){
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratings = fr.getSimilarRatings(raterId, 20, 2);
        Collections.sort(ratings,Collections.reverseOrder());
        if(ratings.size()==0){
            System.out.println("No Similar Rater Found, Please Try Again");
        }
        for (int i=0;i<20&&i<ratings.size();i++){
            Rating rating = ratings.get(i);
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }   
    }
}
