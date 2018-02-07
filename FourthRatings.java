import java.util.*;
/**
 * Class created for this assignment StepFour
 * 
 * @author (Abraham Ferrero) 
 * @version (29/1/2018)
 */
public class FourthRatings {
    //As we can see, every instance variable has been eliminated as requested
    public FourthRatings() {
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile){
        RaterDatabase.addRatings("data/" + ratingsfile);
    }
    
    public Rater getRater(String rater_id){
        return RaterDatabase.getRater(rater_id);
    }
    
    public int getRaterSize(){
        return RaterDatabase.size();
    }
    //Modified as requested in this assignment by using RaterDatabase class:
    private double dotProduct(Rater me, Rater r){
        /*This method returns a double value with the affinity between two Raters. The higher the number,
        the higher the affinity as well*/
        double dotProduct = 0;
        for(int i=0; i<me.numRatings();i++){
            ArrayList<Rating> movieA = new ArrayList<Rating>();
            for(String s : r.getItemsRated()){
                if(me.getItemsRated().contains(s)){
                    double myRating = me.getRating(s);
                    double hisRating = r.getRating(s);
                    //System.out.println("Bingo! : " + s + " is in both!, I voted " + myRating + " and he voted " + hisRating);
                    double finalValue =0;
                    finalValue =(myRating -5)*(hisRating-5);
                    dotProduct = dotProduct+ finalValue;
                }
            }
        }
        return dotProduct;
    }
    
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if(!r.getID().equals(id)){
                if(dotProduct(me,r)>0){
                    similarRatings.add(new Rating(r.getID(),dotProduct(me,r)));
                }
            }
        }
        Collections.sort(similarRatings,Collections.reverseOrder());
        return similarRatings;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        try{
        ArrayList<Rating> similarRatings = getSimilarities(id);
        ArrayList<Rating> getRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        double numratings = 0;
        HashMap<String,ArrayList<Double>>favRaters = new HashMap<String,ArrayList<Double>>();
        for(String movieID : movies){
            for(int i=0; i<numSimilarRaters; i++){
                Rating r = similarRatings.get(i);
                //This gets how big is the similarity:
                double coef = r.getValue();
                //This gets each similar rater id:
                String rater_id = r.getItem();
                ArrayList<Rater> Raters = RaterDatabase.getRaters();
                for(Rater rat : Raters){
                    if(rater_id.equals(rat.getID())){
                        for(String s : rat.getItemsRated()){
                            //If the movie was voted by my "soulmates":
                            if(s.equals(movieID)){
                                //if my hash does not have the movie, add movie and similarity value:
                                ArrayList<Double> coefs = new ArrayList<Double>();
                                if(!favRaters.containsKey(s)){
                                    coefs.add(coef*rat.getRating(s));
                                    favRaters.put(s,coefs);
                                }
                                //If it is already in hashmap, add the similarity rate to the value hashmap:
                                else{
                                    ArrayList<Double> mine = favRaters.get(s);
                                    mine.add(coef*rat.getRating(s));
                                    favRaters.put(s,mine);
                                }
                            }
                        }
                    }
                }
            }
        }
        for ( String s : favRaters.keySet()){
            if( favRaters.get(s).size() >=minimalRaters){
                double total =0;
                for(double num : favRaters.get(s)){
                    total = total+ num;
                }
                //Final calculation for the similarity rate, according to minimalRaters parameter:
                double finalValue = total/favRaters.get(s).size();
                getRatings.add(new Rating(s,finalValue));
            }
        }
        //We sort them to have a rational TOP:
        Collections.sort(getRatings,Collections.reverseOrder());
        return getRatings;
       }
       catch (Exception e){
           System.out.println("No minimal raters found, or not many similar raters found, please refresh the main page and try voting again as other movies will be generated.");
           return null;
       }
   }
   
   public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        try{
        ArrayList<Rating> similarRatings = getSimilarities(id);
        ArrayList<Rating> getRatings = new ArrayList<Rating>();
        //Same method as before, but as easy as running a filterCriteria.
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        double numratings = 0;
        HashMap<String,ArrayList<Double>>favRaters = new HashMap<String,ArrayList<Double>>();
        for(String movieID : movies){
            for(int i=0; i<numSimilarRaters; i++){
                Rating r = similarRatings.get(i);
                //This gets how big is the similarity:
                double coef = r.getValue();
                //This gets each similar rater id:
                String rater_id = r.getItem();
                ArrayList<Rater> Raters = RaterDatabase.getRaters();
                for(Rater rat : Raters){
                    if(rater_id.equals(rat.getID())){
                        for(String s : rat.getItemsRated()){
                            //If the movie was voted by my "soulmates":
                            if(s.equals(movieID)){
                                //if my hash does not have the movie, add movie and similarity value:
                                ArrayList<Double> coefs = new ArrayList<Double>();
                                if(!favRaters.containsKey(s)){
                                    coefs.add(coef*rat.getRating(s));
                                    favRaters.put(s,coefs);
                                }
                                //If it is already in hashmap, add the similarity rate to the value hashmap:
                                else{
                                    ArrayList<Double> mine = favRaters.get(s);
                                    mine.add(coef*rat.getRating(s));
                                    favRaters.put(s,mine);
                                }
                            }
                        }
                    }
                }
            }
        }
        for ( String s : favRaters.keySet()){
            if( favRaters.get(s).size() >=minimalRaters){
                double total =0;
                for(double num : favRaters.get(s)){
                    total = total+ num;
                }
                //Final calculation for the similarity rate, according to minimalRaters parameter:
                double finalValue = total/favRaters.get(s).size();
                getRatings.add(new Rating(s,finalValue));
            }
        }
        //We sort them to have a rational TOP:
        Collections.sort(getRatings,Collections.reverseOrder());
        return getRatings;
       }
       catch (Exception e){
           System.out.println("One of the variables is out of bounds, insert smaller parameter variables or another user");
           return null;
       }
   }
}
