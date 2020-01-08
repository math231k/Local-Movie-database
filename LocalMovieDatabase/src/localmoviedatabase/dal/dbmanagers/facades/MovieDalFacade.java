/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.dal.dbmanagers.facades;

import java.util.List;
import localmoviedatabase.be.Movie;

/**
 *
 * @author math2
 */
public interface MovieDalFacade {
    public boolean createMovie(Movie movie);
    public boolean deleteMovie(Movie movie);
    public List<Movie> readMovie();
    public boolean updateMovie(Movie movie);
}
