/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.bll.util;

import java.util.ArrayList;
import java.util.List;
import localmoviedatabase.be.Genre;

import localmoviedatabase.be.Movie;

/**
 *
 * @author Mikkel Husum
 */
public class SearchMovies {

    /**
     * searches the database for a specific movie
     * @param searchBase the list to be searched
     * @param query the Strink to be searched for
     * @return 
     */
    public List<Movie> searchMovie(List<Movie> searchBase, String query) {
        List<Movie> output = new ArrayList<>();

        for (Movie movie : searchBase) {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase())
                    /*|| genre.getCategory().toLowerCase().contains(query.toLowerCase())*/) {
                output.add(movie);
            }
        }

        return output;
    }
    /**
     * Searches the database for a specifik genre
     * @param searchBase the genre list to be searched through
     * @param query the String to be searched for
     * @return a list of genre results
     */
    public List<Genre> searchCategory(List<Genre> searchBase, String query) {
        List<Genre> output = new ArrayList<>();

        for (Genre genre : searchBase) {
            
            //System.out.println("Genre: " + genre.getGenreName());
            //System.out.println("Movies In Genre: " + genre.getMovies());
            /*
            if (genre.getGenreName().toLowerCase().contains(query.toLowerCase())
                    /*|| genre.getCategory().toLowerCase().contains(query.toLowerCase())) {
                output.add(genre);
            }
            */
            if (searchForMovie(genre.getMovies(),query) == true){
                //System.out.println("Search Success");
                output.add(genre);
            }
            else 
            {
                //System.out.println("Search Fail");
                
            }
        }

        return output;
    }
    public boolean searchForMovie(List<Movie> searchBase, String query) {
        boolean output = false;

        //System.out.println("Searching Started");
        //System.out.println("Now Searching: " + searchBase);
        for (Movie movie : searchBase) {
            //System.out.println("Now Searching: " + movie);
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase())
                    /*|| genre.getCategory().toLowerCase().contains(query.toLowerCase())*/) {
                output = true;
            }
        }
        //System.out.println(output);
        return output;
    }
}
