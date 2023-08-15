
public class YearAfterFilter implements Filter {
    private int myYear;
    
    public YearAfterFilter(int year) {
        myYear = year;
    }
    
    public String toString(){
        return "YearFilter";
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }

}
