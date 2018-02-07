import java.util.*;
import java.lang.Object;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    private int helperMoviesAndRatings(){
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        System.out.println("Number of total of Raters: " + MoviesAndRatings.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of total of movies: " + MovieDatabase.size());
        //Insert the minimum raters here:
        int minimum = 3;
        return minimum;
    }
    
    public void printAverageRatings(){
        int minimum = helperMoviesAndRatings();
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        ArrayList<Rating> arrayMovies = MoviesAndRatings.getAverageRatings(minimum);
        Collections.sort(arrayMovies);
        for(Rating r : arrayMovies){
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d+ 
                                                  " " + movieTitle);
        }
        System.out.println("Movies with at least " + minimum + " ratings: " +
                                arrayMovies.size());
    }
    
    public void printAverageRatingsByYearAfterAndGenre (){
        YearAfterFilter filterYear = new YearAfterFilter(1990);
        GenreFilter filterGenre = new GenreFilter("Drama");
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        int minimum = helperMoviesAndRatings();
        AllFilters yearAndGenre = new AllFilters();
        yearAndGenre.addFilter(filterYear);
        yearAndGenre.addFilter(filterGenre);
        ArrayList<Rating> arrayMovies = MoviesAndRatings.getAverageRatingsByFilter(minimum,yearAndGenre);
        Collections.sort(arrayMovies);
        for(Rating r : arrayMovies){
             String item = r.getItem();
             String movieTitle = MovieDatabase.getTitle(item);
             System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d+ 
                                                    " " + movieTitle + " Director: " + MovieDatabase.getDirector(item));
        }
        System.out.println("Movies returned = " + arrayMovies.size());
    }
    //Because the output is the same as with the "MovieRunnerWithFilters" class, we
    //know our FouthRatings class works perfectly and we are in the right direction.
    
    public void printSimilarRatings (){
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String rater_id = "71";
        int numTopRaters = 20;
        int minimalRate = 5;
        ArrayList<Rating> getSimilarRatings = MoviesAndRatings.getSimilarRatings(rater_id,numTopRaters,minimalRate);
        for(Rating r : getSimilarRatings){
            //MovieDatabase class gets superhandy:
            System.out.println(MovieDatabase.getTitle(r.getItem()));// + " " + r.getValue());
        }
    }
    
    public void printSimilarRatingsByGenre(){
        GenreFilter filterGenre = new GenreFilter("Mystery");
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String rater_id = "964";
        int numTopRaters = 20;
        int minimalRate = 5;
        ArrayList<Rating> getSimilarRatingsByFilter = MoviesAndRatings.getSimilarRatingsByFilter(rater_id,numTopRaters,minimalRate,filterGenre);
        for(Rating r : getSimilarRatingsByFilter){
            System.out.println(MovieDatabase.getTitle(r.getItem()));// + " " + r.getValue());
        }
    }
    
    public void printSimilarRatingsByDirector(){
        DirectorsFilter filterDirector = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String rater_id = "120";
        int numTopRaters = 10;
        int minimalRate = 2;
        ArrayList<Rating> getSimilarRatingsByFilter = MoviesAndRatings.getSimilarRatingsByFilter(rater_id,numTopRaters,minimalRate,filterDirector);
        for(Rating r : getSimilarRatingsByFilter){
            System.out.println(MovieDatabase.getTitle(r.getItem()));// + " " + r.getValue());
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        AllFilters GenreAndMinutes = new AllFilters();
        GenreFilter genre = new GenreFilter("Drama");
        MinutesFilter minutes = new MinutesFilter(80,160);
        GenreAndMinutes.addFilter(genre);
        GenreAndMinutes.addFilter(minutes);
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String rater_id = "168";
        int numTopRaters = 10;
        int minimalRate = 3;
        ArrayList<Rating> getSimilarRatingsByFilter = MoviesAndRatings.getSimilarRatingsByFilter(rater_id,numTopRaters,minimalRate,GenreAndMinutes);
        for(Rating r : getSimilarRatingsByFilter){
            System.out.println(MovieDatabase.getTitle(r.getItem()));// + " " + r.getValue());
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        AllFilters YearAfterAndMinutes = new AllFilters();
        YearAfterFilter year = new YearAfterFilter(1975);
        MinutesFilter minutes = new MinutesFilter(70,200);
        YearAfterAndMinutes.addFilter(year);
        YearAfterAndMinutes.addFilter(minutes);
        FourthRatings  MoviesAndRatings = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String rater_id = "314";
        int numTopRaters = 10;
        int minimalRate = 5;
        ArrayList<Rating> getSimilarRatingsByFilter = MoviesAndRatings.getSimilarRatingsByFilter(rater_id,numTopRaters,minimalRate,YearAfterAndMinutes);
        for(Rating r : getSimilarRatingsByFilter){
            System.out.println(MovieDatabase.getTitle(r.getItem()));// + " " + r.getValue());
        }
    }
}
