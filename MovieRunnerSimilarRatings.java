import java.util.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    private void printOut(ArrayList<Rating> average, String print) {
        System.out.println("Found " + average.size() + " Movies");
        Collections.sort(average,Collections.reverseOrder());
        for (Rating rating : average) {
            System.out.println(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()));
            if (print.contains("Year")) {
                System.out.println("\t" + MovieDatabase.getYear(rating.getItem()));
            }
            if (print.contains("Genre")) {
                System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
            }
            if (print.contains("Minute")) {
                System.out.println("\t" + MovieDatabase.getMinutes(rating.getItem()) + " minutes");
            }
            if (print.contains("Director")) {
                System.out.println("\tby " + MovieDatabase.getDirector(rating.getItem()));
            }
        }
    }  
    public void testSimilarRatings(){
        FourthRatings fr = new FourthRatings();
        int minRaters=5;
        int numRaters=20;
        String raterId = "71";
        ArrayList<Rating> average = fr.getSimilarRatings(raterId, numRaters, minRaters);
        printOut(average,"");
    }
    public void testSimilarRatingsByGenre(String genre){
        FourthRatings fr = new FourthRatings();
        int minRaters=5;
        int numRaters=20;
        String raterId = "964";
        Filter f = new GenreFilter(genre);
        ArrayList<Rating> average = fr.getSimilarRatingsByFilter(raterId, numRaters, minRaters, f);
        printOut(average, f.toString());
    }
    public void testSimilarRatingsByDirectors(String directors){
        FourthRatings fr = new FourthRatings();
        int minRaters=2;
        int numRaters=10;
        String raterId = "120";
        Filter f = new DirectorsFilter(directors);
        ArrayList<Rating> average = fr.getSimilarRatingsByFilter(raterId, numRaters, minRaters, f);
        printOut(average, f.toString());
    }
    public void testSimilarRatingsByGenreAndMinutes(String genre,int minMin, int maxMin){
        FourthRatings fr = new FourthRatings();
        int minRaters=3;
        int numRaters=10;
        String raterId = "168";
        Filter mf = new MinutesFilter(minMin,maxMin);
        Filter gf = new GenreFilter(genre);
        AllFilters f = new AllFilters();
        f.addFilter(mf);
        f.addFilter(gf);
        ArrayList<Rating> average = fr.getSimilarRatingsByFilter(raterId, numRaters, minRaters, f);
        printOut(average, f.toString());
    }
    public void testSimilarRatingsByYearAndMinutes(int year,int minMin, int maxMin){
        FourthRatings fr = new FourthRatings();
        int minRaters=5;
        int numRaters=10;
        String raterId = "314";
        Filter mf = new MinutesFilter(minMin,maxMin);
        Filter yf = new YearAfterFilter(year);
        AllFilters f = new AllFilters();
        f.addFilter(mf);
        f.addFilter(yf);
        ArrayList<Rating> average = fr.getSimilarRatingsByFilter(raterId, numRaters, minRaters, f);
        printOut(average, f.toString());
    }
/*
    public void testAverageRatings() {
        int minRater = 2;
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> average = fr.getAverageRatings(minRater);
        //System.out.println(average.size());
        printOut(average, "");
    }
   
    public void testByYearAndGenre(int year, String genre) {
        Filter yearFilter = new YearAfterFilter(year);
        Filter genreFilter = new GenreFilter(genre);
        AllFilters filters = new AllFilters();
        filters.addFilter(yearFilter);
        filters.addFilter(genreFilter);
        int minRater = 8;
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> average = fr.getAverageRatingsByFilter(filters, minRater);
        //System.out.println(average.size());
        printOut(average, filters.toString());
    }
    */
}
