/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.bll;

import java.util.List;
import localmoviedatabase.be.Movie;
import localmoviedatabase.dal.dbmanagers.dbdao.MovieDBDAO;
import localmoviedatabase.dal.dbmanagers.facades.MovieDalFacade;

/**
 *
 * @author math2
 */
public class MovieManager
{
    
    private final MovieDalFacade movieDBDAO;
    private Movie movie;

    /**
     * Constructor, initializes object
     */
    public MovieManager()
    {
        movieDBDAO = new MovieDBDAO();
    }
    
    /**
     * Gets all movies from the database
     * @return 
     */
    public List<Movie> getAllMovies()
    {
        return movieDBDAO.readMovie();
    }

    /**
     * Adds a new movie to the database
     * @param category
     * @param title
     * @param rating
     * @param relDate
     * @param path
     * @return 
     */
    public Movie addMovie(String title, int rating, int relDate, String path) {
        
        movie = movieDBDAO.createMovie(title, rating, relDate, path);
        return movie;
        
    }

    /**
     * Removes movie from database
     * @param m 
     */
    public void removeMovie(Movie m) {
        movieDBDAO.deleteMovie(m);
    }

    /**
     * Updates movie from database
     * @param m 
     */
    public void updateMovie(Movie m) {
        movieDBDAO.updateMovie(m);
    }



    
    
    
    
    
}
