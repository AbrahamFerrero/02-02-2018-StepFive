/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import org.apache.commons.csv.*;
import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        Rating newRat = new Rating(item,rating);
        myRatings.put(newRat.getItem(),new Rating(item,rating));
    }
    
    public HashMap<String,Rating> getaRating(){
        return myRatings;
    }
    
    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }
    public double getRating(String item) {
        for(String r: myRatings.keySet()){
            if(r.equals(item)){
                return myRatings.get(r).getValue();
            }
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String r: myRatings.keySet()){
            list.add(r);
        }
        return list;
    }
}
