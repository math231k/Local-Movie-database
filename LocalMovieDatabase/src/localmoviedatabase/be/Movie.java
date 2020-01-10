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


    public Movie(String title, String length, String path)
    {
        this.title = title;
        this.length = length;
        this.path = path;
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
        return rating + "/10";
    }

    public void setRating(int rating) {
        if (rating > 0 && rating <= 10)
        {
        this.rating = rating;
        }
        else
        {
            System.out.println("Please select a rating from 1 to 10");
        }
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

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getPath() {
        return path;
    }

    public void setId(int id) {
        this.id = id;
    }

}
