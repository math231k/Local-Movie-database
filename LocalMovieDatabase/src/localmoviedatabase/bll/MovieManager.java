/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.bll;

import java.io.IOException;
import java.util.List;
import localmoviedatabase.be.Genre;
import localmoviedatabase.be.Movie;
import localmoviedatabase.dal.dbaccess.DalException;
import localmoviedatabase.dal.dbmanagers.dbdao.MovieDBDAO;

/**
 *
 * @author math2
 */
public class MovieManager
{
    
    private MovieDBDAO movieDBDAO;

    
    public MovieManager()
    {
        movieDBDAO = new MovieDBDAO();
    }
    
    public List <Movie> getAllMovies()
    {
        return movieDBDAO.getAllMovies();
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
