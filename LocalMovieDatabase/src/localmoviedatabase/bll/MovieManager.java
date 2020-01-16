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

    
    public MovieManager()
    {
        movieDBDAO = new MovieDBDAO();
    }
    
    public List<Movie> getAllMovies()
    {
        return movieDBDAO.readMovie();
    }

    public Movie addMovie(String category, String title, int rating, int relDate, String path) {
        
        movie = movieDBDAO.createMovie(category, title, rating, relDate, path);
        return movie;
        
    }

    public void removeMovie(Movie m) {
        movieDBDAO.deleteMovie(m);
    }

    public void updateMovie(Movie m) {
        movieDBDAO.updateMovie(m);
    }



    
    
    
    
    
}
