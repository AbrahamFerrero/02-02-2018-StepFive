
/**
 * Filter created by Abraham Ferrero as a requirement for Assignment 2
 * 
 * @author (Abraham Ferrero) 
 * @version (25/01)
 */
public class DirectorsFilter implements Filter {
    private String myDirector;
    public DirectorsFilter(String director){
        myDirector = director;
    }
    
    public boolean satisfies(String id){
        return myDirector.contains(MovieDatabase.getDirector(id));
    }
}
