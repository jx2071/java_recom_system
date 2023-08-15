import java.util.ArrayList;

public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }
    
    public String toString(){
        String name ="";
        for(Filter f:filters){
            name+=f.toString();
        }
        return name;
    }
    
    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        }
        
        return true;
    }

}
