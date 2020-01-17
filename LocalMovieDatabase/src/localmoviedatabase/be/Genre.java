/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author math2
 */
public class Genre
{

    private String genreName;
    private List<Movie> movies = new ArrayList<>();
    private int id;

    public Genre(String genreName)
    {
        this.genreName = genreName;
    }

    //constructor for editing category
    public Genre(int id, String genreName)
    {
        this.id = id;
        this.genreName = genreName;
    }
    
    public String getGenreName()
    {
        return genreName;
    }

    public void setGenreName(String genreName)
    {
        this.genreName = genreName;
    }

    public List<Movie> getMovies()
    {
        return movies;
    }

    public int getId()
    {
        return id;
    }
    
    public void deleteMovie(Movie movie) {
        //movies.remove(movie);
        System.out.println(movies);
    }

    public boolean addMovieToGenre(Movie movie)
    {
        movies.add(movie);
        return true;
    }

    public List<Movie> getGenreMovieList()
    {
        return movies;
    }

    public void setId(int id) {
        this.id = id;
    }
}
