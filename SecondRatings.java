/* Abraham Ferrero note: Please mind that I copypasted my last project "17-01-2017 StepTwo" 
 * here and erased every explanatory note from it in order to keep woking.
 * Should you not understand something in the "FirstRatings" class, 
 * go to the last project and see how the methods work and how everything was developed and why.*/
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private HashMap<String, HashMap<String,Rating>> loadRaters;
    public SecondRatings() {
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings ratings = new FirstRatings();
        myMovies = ratings.loadMovies("data/" + moviefile);
        myRaters = ratings.csvMethod2("data/" + ratingsfile);
        loadRaters = ratings.loadRaters("data/" + ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        for(Movie m : myMovies){
            String movie_id = m.getID();
            getAverageByID(movie_id,minimalRaters);
            Rating a = new Rating(movie_id, getAverageByID(movie_id,minimalRaters));
            if(a.getValue() > -1){
                averageRatings.add(a);
            }
        }
        return averageRatings;
    }
    
    public String getTitle(String id){
        for(Movie m : myMovies){
            if(id.equals(m.getID())){
                return m.getTitle();
            }
        }
        return "Movie ID not found in the database";
    }
    
    public String getID(String title){
        for(Movie m : myMovies){
            if(title.equals(m.getTitle())){
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}
