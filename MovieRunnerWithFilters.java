import java.util.*;

/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    private void printOut(ArrayList<Rating> average, String print) {
        System.out.println("Found " + average.size() + " Movies");
        Collections.sort(average);
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

    public void testAverageRatings() {
        int minRater = 35;
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        ArrayList<Rating> average = sr.getAverageRatings(minRater);
        //System.out.println(average.size());
        printOut(average, "");
    }

    public void testAverageRatingsByYear(int minYear) {
        Filter yearFilter = new YearAfterFilter(minYear);
        int minRater = 20;
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        ArrayList<Rating> average = sr.getAverageRatingsByFilter(yearFilter, minRater);
        //System.out.println(average.size());
        printOut(average, "Year");
    }

    public void testAverageRatingsByGenre(String genre) {
        Filter genreFilter = new GenreFilter(genre);
        int minRater = 20;
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        ArrayList<Rating> average = sr.getAverageRatingsByFilter(genreFilter, minRater);
        //System.out.println(average.size());
        printOut(average, genreFilter.toString());
    }

    public void testAverageRatingsByMinutes(int minMin, int maxMin) {
        Filter minutesFilter = new MinutesFilter(minMin, maxMin);
        int minRater = 5;
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        ArrayList<Rating> average = sr.getAverageRatingsByFilter(minutesFilter, minRater);
        //System.out.println(average.size());
        printOut(average, minutesFilter.toString());
    }

    public void testAverageRatingsByDirector(String director) {
        Filter directorFilter = new DirectorsFilter(director);
        int minRater = 4;
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        ArrayList<Rating> average = sr.getAverageRatingsByFilter(directorFilter, minRater);
        printOut(average, directorFilter.toString());
    }

    public void testByYearAndGenre(int year, String genre) {
        Filter yearFilter = new YearAfterFilter(year);
        Filter genreFilter = new GenreFilter(genre);
        AllFilters filters = new AllFilters();
        filters.addFilter(yearFilter);
        filters.addFilter(genreFilter);
        int minRater = 8;
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        ArrayList<Rating> average = sr.getAverageRatingsByFilter(filters, minRater);
        //System.out.println(average.size());
        printOut(average, filters.toString());
    }
    
    public void testByDirectorAndMinute(String director, int minMin,int maxMin){
        Filter directorFilter = new DirectorsFilter(director);
        Filter minutesFilter = new MinutesFilter(minMin, maxMin);
        AllFilters filters = new AllFilters();
        filters.addFilter(directorFilter);
        filters.addFilter(minutesFilter);
        int minRater = 3;
        ThirdRatings sr = new ThirdRatings("ratings.csv");
        ArrayList<Rating> average = sr.getAverageRatingsByFilter(filters, minRater);
        //System.out.println(average.size());
        printOut(average, filters.toString());
    }
}
