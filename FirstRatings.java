/* Abraham Ferrero note: Please mind that I copypasted my last project "12-19-2017 StepOneStarterProgram" 
 * here and erased every explanatory note from it in order to keep woking.
 * Should you not understand something in the "FirstRatings" class, 
 * go to the last project and see how the methods work and how everything was developed and why.*/
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * This class has been created as a requirement for this Assignment.
 * 
 * @author (Abraham Ferrero) 
 * @version (19/DIC/2017)
 */
public class FirstRatings {
    private ArrayList<Movie> csvmethod(CSVParser parser){
        ArrayList<Movie> MovieData = new ArrayList<Movie>();
        for(CSVRecord movie : parser){
            Movie newMovie = new Movie(movie.get("id"),movie.get("title"),movie.get("year"), movie.get("genre"),movie.get("country"),movie.get("director"),Integer.parseInt(movie.get("minutes")),movie.get("poster"));
            MovieData.add(newMovie);
        }
        return MovieData;
    }
    
    public ArrayList<Movie> loadMovies(String filename){
        //This giving me error now.
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> MovieData = csvmethod(parser);
        return MovieData;
    }
    
    private HashMap<String,Integer> DirectorAndMovies(ArrayList<Movie> films){
        HashMap<String,Integer> mapDirector = new HashMap<String,Integer>();
        for(Movie film : films){
            String Director = film.getDirector();
            String new1 = "";
            int index = 0;
            if (Director.contains(",")){
                    new1 = Director.substring(index,Director.indexOf(","));
                    index = new1.length()+2;
                    String rest = Director.substring(index);
                    int index2= 0;
                    while(rest.contains(",")){
                       new1 = rest.substring(index2,rest.indexOf(","));
                       if(!mapDirector.containsKey(new1)){
                           mapDirector.put(new1,1);
                        }
                       else{
                           mapDirector.put(new1,mapDirector.get(new1)+1);
                        }
                       index2 = new1.length()+2;
                       rest= rest.substring(index2);
                       index2 = 0;
                    }
                    if(!mapDirector.containsKey(rest)){
                        mapDirector.put(rest,1);
                    }
                    else{
                        mapDirector.put(rest,mapDirector.get(rest)+1);
                    }
            }
            else{
                new1 = Director;
            }
            if(!mapDirector.containsKey(new1)){
                mapDirector.put(new1,1);
            }
            else{
                mapDirector.put(new1,mapDirector.get(new1)+1);
            }
        }
        return mapDirector;
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> MovieData = loadMovies("ratedmoviesfull.csv");
        int count = 0;
        int comedy = 0;
        int minutesLong = 0;
        for(Movie move : MovieData){
            count++;
            System.out.println(move.getCountry());
            System.out.println(move.toString());
            if (move.getGenres().contains("Comedy")){
                comedy++;
            }
            if(move.getMinutes() > 150){
                minutesLong++;
            }
        }
        System.out.println("Total movies = " + count);
        System.out.println("Total comedies = " + comedy);
        System.out.println("Movies longer than 150min = " + minutesLong);

        HashMap<String,Integer>mapDirector = DirectorAndMovies(MovieData);
        ArrayList<String> MaxDirectors = new ArrayList<String>();
        int max = 0;
        String maxDirector = "";
        for(String s : mapDirector.keySet()){
            if (mapDirector.get(s) > max){
                max = mapDirector.get(s);
            }
        }
        for(String s : mapDirector.keySet()){
            if (mapDirector.get(s) == max){
                MaxDirectors.add(s);
            }
        }
        System.out.println("Maximum number of movies by any director is: " + max);
        System.out.println("Directors who directed this many movies: \n");
        for(String s : MaxDirectors){
            System.out.println(s);
        }
       
    }
    //This one has been created in order to make a working constructor in SecondRatings class.
    private ArrayList<Rater> csvRater(CSVParser parser){
        ArrayList<Rater> loadRaters = new ArrayList<Rater>();
        for(CSVRecord rate : parser){
            Rater newRater = new EfficientRater(rate.get("rater_id"));
            loadRaters.add(newRater);
            newRater.addRating(rate.get("movie_id"),Double.parseDouble(rate.get("rating")));
        }
        return loadRaters;
    }
    
    public ArrayList<Rater> csvMethod2(String filename){
        FileResource f = new FileResource(filename);
        CSVParser parser = f.getCSVParser();
        ArrayList<Rater> loadRaters = csvRater(parser);
        return loadRaters;
    }
    
    private HashMap<String, HashMap<String,Rating>> csvLoadRaters(CSVParser parser){
       HashMap<String, HashMap<String,Rating>> idAndRatingsMap = new HashMap<String, HashMap<String,Rating>>();
        for(CSVRecord rate : parser){
           Rater newRater = new EfficientRater(rate.get("rater_id"));
           newRater.addRating(rate.get("movie_id"),Double.parseDouble(rate.get("rating")));
           for(String hey : newRater.getaRating().keySet()){
              if(!idAndRatingsMap.containsKey(newRater.getID())){
                   idAndRatingsMap.put(newRater.getID(),newRater.getaRating());
              }
              else{
                   idAndRatingsMap.get(newRater.getID()).put(hey,newRater.getaRating().get(hey));
              }
                }
        }
       return idAndRatingsMap;
    }

    public HashMap<String, HashMap<String,Rating>> loadRaters(String filename){
        FileResource f = new FileResource(filename);
        CSVParser parser = f.getCSVParser();
        HashMap<String, HashMap<String,Rating>> loadRaters = csvLoadRaters(parser);
        return loadRaters;
    }
    
    public void testLoadRaters(){
       String filename = "ratings.csv"; 
       HashMap<String, HashMap<String,Rating>> loadRaters = loadRaters(filename);
       for(HashMap<String,Rating> rating : loadRaters.values()){
            for(Rating rat : rating.values()){
           //These two will display the actual entire hasmap:
           //System.out.println("The userID: " + s + " voted " + loadRaters.get(s).size() + " movies");
           //System.out.println("These are the id and rate: " + loadRaters.get(s));
        }
       }
       System.out.println("Total raters = " + loadRaters.size());
       String specficRater = "193";
       for(HashMap<String,Rating> rating : loadRaters.values()){
            for(Rating rat : rating.values()){
               if(rat.equals(specficRater)){
                  System.out.println("The user with the specific ID " + rat + " voted " + loadRaters.get(rat).size() + " movies");
               }
            }
       }
       int max = 0;
       for(String s : loadRaters.keySet()){
           if(max < loadRaters.get(s).size()){
               max = loadRaters.get(s).size();
           }
       }
       System.out.println("Maximum number of movies rated per user: " + max);
       System.out.println("And they were voted by the users ID: ");
       int countMaxRaters = 0;
       for(String s : loadRaters.keySet()){
           if(loadRaters.get(s).size() == max){
               System.out.println(s);
               countMaxRaters++;
           }
       }
       System.out.println("Amount of TOP1 user/s by no. of votes: " + countMaxRaters);
       String movie_id = "1798709";
       int timesVoted = 0;
       for(HashMap<String,Rating> rating : loadRaters.values()){
            for(Rating eachMovie : rating.values()){
               String moviesID = eachMovie.getItem();
               if(movie_id.equals(moviesID)){
                  timesVoted++;
               }
            }
        
       }
       System.out.println("The movie with the ID: " + movie_id + " has been voted "  + timesVoted + " times");
       ArrayList<String> list = new ArrayList<String>();
       for(HashMap<String,Rating> rating : loadRaters.values()){
            for(Rating eachMovie : rating.values()){
               String moviesID = eachMovie.getItem();
               if(!list.contains(moviesID)){
                   list.add(moviesID);
               }
           }
       }
       System.out.println("Number of total different movies rated: " + list.size());
     }
    }
     
     