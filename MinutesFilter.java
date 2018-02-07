
/**
 * Filter created by Abraham Ferrero as a requirement for Assignment 2
 * 
 * @author (Abraham Ferrero) 
 * @version (25/01)
 */
public class MinutesFilter implements Filter{
    private int myMinMinutes;
    private int myMaxMinutes;
    public MinutesFilter(int minMinutes, int maxMinutes){
        myMinMinutes = minMinutes;
        myMaxMinutes = maxMinutes;
    }
    
    public boolean satisfies(String id){
        return MovieDatabase.getMinutes(id) >= myMinMinutes && 
               MovieDatabase.getMinutes(id) <= myMaxMinutes;
    }
}
