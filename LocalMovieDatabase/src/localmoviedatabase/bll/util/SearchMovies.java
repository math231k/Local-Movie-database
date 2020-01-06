/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.bll.util;

import java.util.ArrayList;
import java.util.List;

import localmoviedatabase.be.Movie;

/**
 *
 * @author Mikkel Husum
 */
public class SearchMovies {

    public static List<Movie> search(List<Movie> searchBase, String query) {
        List<Movie> output = new ArrayList<>();

        for (Movie movie : searchBase) {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase())
                    || movie.getCategory().toLowerCase().contains(query.toLowerCase())) {
                output.add(movie);
            }
        }

        return output;
    }
}
