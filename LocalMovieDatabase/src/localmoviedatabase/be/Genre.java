/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.be;

import java.util.List;

/**
 *
 * @author math2
 */
public class Genre
{

    String genreName;
    List<Movie> movies;
    int id;

    public Genre(String genreName)
    {
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

    public boolean addMovieToGenre(Movie movie)
    {
        movies.add(movie);
        return true;
    }

    public List<Movie> getGenreMovieList()
    {
        return movies;
    }
}
