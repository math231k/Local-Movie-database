/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.be;

/**
 *
 * @author math2
 */
public class Movie {

    private int id;  
    private String category;
    private String title;
    private String length;
    private int rating;
    private int relDate;
    private String path;

    public Movie(int id, String category, String title, int rating, int relDate, String path)
    {
        this.id = id;
        this.category = category;
        this.title = title;
        this.rating = rating;
        this.relDate = relDate;
        this.path = path;
    }

    public Movie(int id, int rating, String title)
    {
        this.id = id;
        this.rating = rating;
        this.title = title;
    }

    /**
     * gets the title of the movie
     * @return the title as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the title of the movie
     * @param title the title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * sets the path of the movie file
     * @param path the path to be set
     */
    public void setPath(String path) {
        this.path = path;
    }
    
    /**
     * gets the rating of the Movie
     * @return the rating as an integer
     */
    public int getRating() {
        return rating;
    }

    /**
     * sets the rating of the movie
     * @param rating the rating to be set
     */
    public void setRating(int rating) {       
        this.rating = rating;  
    }

    
    /**
     * gets the release date of the movie
     * @return the release date as an int
     */
    public int getRelDate() {
        return relDate;
    }

    /**
     * sets the release date of the movie
     * @param relDate the release date to be set
     */
    public void setRelDate(int relDate) {
        this.relDate = relDate;
    }

    /**
     * gets the id of the movie
     * @return the id as an integer
     */
    public int getId() {
        return id;
    }
    
    /**
     * prints some movie data as a String
     * @return A String with some movie data
     */
    @Override
    public String toString(){
        return (title +" "+ rating);
    }
    
    /**
     * gets the path from the move
     * @return the path as a String
     */
    public String getPath() {
        return path;
    }

    /**
     * sets the id of the Movie
     * @param id the id to be set
     */
    public void setId(int id) {
        this.id = id;
    }

}
