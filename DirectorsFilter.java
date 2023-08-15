
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String[] myDirector;
    public DirectorsFilter(String directors){
        myDirector = directors.split(",");
    }
    public String toString(){
        return "DirectorsFilter";
    }
    public boolean satisfies(String id){
        boolean found = false;
        for(String director:myDirector){
            if(MovieDatabase.getDirector(id).contains(director)){
                found = true;
            }
        }
        return found;
    }
}
