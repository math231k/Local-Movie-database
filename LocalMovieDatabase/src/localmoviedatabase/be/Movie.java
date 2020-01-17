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
    private String title;
    private String length;
    private int rating;
    private int relDate;
    private String path;

    public Movie(int id, String title, int rating, int relDate, String path)
    {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.relDate = relDate;
        this.path = path;
    }

    public Movie(String title, String length, String path)
    {
        this.title = title;
        this.length = length;
        this.path = path;
    }

    
    

    //constructor for editing movies
    public Movie(int id, int rating, String title)
    {
        this.id = id;
        this.rating = rating;
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        
        this.rating = rating;
   
    }

    public int getRelDate() {
        return relDate;
    }

    public void setRelDate(int relDate) {
        this.relDate = relDate;
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString(){
        return (title +" "+ length +" "+ rating);
    }

    
    public String getPath() {
        return path;
    }

    public void setId(int id) {
        this.id = id;
    }

}
