/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.bll;

import java.util.List;
import localmoviedatabase.be.Movie;
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
    
    
    
    
    
    
}
