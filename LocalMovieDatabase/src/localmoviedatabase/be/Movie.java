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
<<<<<<< HEAD
    int id;
    private String title;
    private String length;
    private String rating;
    private int relDate;

    public Movie(String title, String length, String rating, int relDate) {
        this.title = title;
        this.length = length;
        this.rating = rating;
        this.relDate = relDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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
=======
    
    private final int id;
    private String title;
    private String category;

    public Movie(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
    
>>>>>>> c0237e306e205ab53695a01a068c055da5c6427a
}
