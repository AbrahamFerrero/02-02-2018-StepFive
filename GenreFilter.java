
/**
 * Class created as a requirement for Assignment 2 by Abraham Ferrero
 * 
 * @author (Abraham Ferrero) 
 * @version (25/01/2018)
 */
public class GenreFilter implements Filter {
    private String myGenre;
    public GenreFilter(String genre){
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(myGenre);
    }
}
