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

    
    public MovieManager()
    {
        movieDBDAO = new MovieDBDAO();
    }
    
    public List<Movie> getAllMovies()
    {
        return movieDBDAO.readMovie();
    }

    public void addMovie(Movie m) {
        if (m != null){
        movieDBDAO.createMovie(m);
        }
    }

    public void removeMovie(Movie m) {
        movieDBDAO.deleteMovie(m);
    }

    public void updateMovie(Movie m) {
        movieDBDAO.updateMovie(m);
    }



    
    
    
    
    
}
