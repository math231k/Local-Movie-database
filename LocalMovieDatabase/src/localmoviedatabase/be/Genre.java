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

    public Genre(int id, String genreName)
    {
        this.id = id;
        this.genreName = genreName;
    }
    
    /**
     * Gets the name of the genre
     * @return the name of the genre as a String
     */
    public String getGenreName()
    {
        return genreName;
    }

    /**
     * Sets the name of the genre
     * @param genreName the name to be set
     */
    public void setGenreName(String genreName)
    {
        this.genreName = genreName;
    }


    /**
     * gets the id of the genre
     * @return the id as an integer
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the id of the genre
     * @param id the id to be set
     */
    public void setId(int id) {
        this.id = id;
    }
}
