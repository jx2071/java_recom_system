
public class TrueFilter implements Filter {
    @Override
    public boolean satisfies(String id) {
        return true;
    }
    public String toString(){
        return "TrueFilter";
    }
}
