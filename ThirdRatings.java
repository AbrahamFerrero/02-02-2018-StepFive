import java.util.*;
public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    private HashMap<String, HashMap<String,Rating>> loadRaters;
    
    public ThirdRatings() {
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings ratings = new FirstRatings();
        myRaters = ratings.csvMethod2(ratingsfile);
        loadRaters = ratings.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return loadRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){
        double count = 0;
        double numRatings = 0;
        double average = 0;
        if(minimalRaters == 0){
            return 0.0;
        }
        for(HashMap<String,Rating> rating : loadRaters.values()){
            for(Rating rat : rating.values()){
                if(rat.getItem().equals(id)){
                    double value = rat.getValue();
                    numRatings++;
                    count = count + value;
                }
            }
        }
        if(numRatings< minimalRaters){
            return -1;
        }
        else{
            average = count/numRatings;
            return average;
        }
    }
    
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String m : movies){
            getAverageByID(m,minimalRaters);
            Rating a = new Rating(m,getAverageByID(m,minimalRaters));
            if(a.getValue() > -1){
                averageRatings.add(a);
            }
        }
        return averageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> averageAndFilter = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String m : movies){
            getAverageByID(m,minimalRaters);
            Rating a = new Rating(m,getAverageByID(m,minimalRaters));
            if(a.getValue() > -1){
                averageAndFilter.add(a);
            }
        }
        return averageAndFilter;
    }
}
