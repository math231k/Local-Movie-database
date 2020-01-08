/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.dal.dbmanagers.mockdatamanagers;

import localmoviedatabase.be.Genre;
import localmoviedatabase.be.Movie;

/**
 *
 * @author math2
 */
public class MockMovieManager {
    
    
    public static void main(String[] args) {
       // Movie m1 = new Movie("Blyatman", "1h50m", "6/10", 1999,"");
       // Movie m2 = new Movie("Rise of Shashlik Walker", "1h50m", "6/10", 1999,"");
       // Movie m3 = new Movie("Funny dat", "1h50m", "6/10", 1999,"");
       // Movie m4 = new Movie("Hello", "1h50m", "6/10", 1999,"");
        
        Genre g1 = new Genre("Action");
        Genre g2 = new Genre("Romance");
        
       // g2.addMovieToGenre(m4);
       // g2.addMovieToGenre(m1);
       // g2.addMovieToGenre(m3);
        
        for (Movie m : g2.getGenreMovieList()) {
            System.out.println(m.toString());
        }
    }
}
