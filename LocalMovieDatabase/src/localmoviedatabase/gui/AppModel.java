/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import localmoviedatabase.be.Genre;
import localmoviedatabase.be.Movie;
import localmoviedatabase.bll.CategoryManager;
import localmoviedatabase.bll.MovieManager;
import localmoviedatabase.dal.dbaccess.DalException;

/**
 *
 * @author math2
 */
public class AppModel
{
    
    private MovieManager movieManager;
    private CategoryManager categoryManager;
    public ObservableList<Movie> movies = FXCollections.observableArrayList();
    public ObservableList<Genre> categories = FXCollections.observableArrayList();

    
    public AppModel() throws IOException
    {
        movieManager = new MovieManager();
        categoryManager = new CategoryManager();
    }
    
    public ObservableList<Genre> getCategories() throws DalException
    {
        categories.clear();
        categories.addAll(categoryManager.getAllCategories());
        return categories;
    }        
    
    public ObservableList<Movie> getMovies() throws DalException, IOException
    {
        movies.clear();
        movies.addAll(movieManager.getAllMovies());
        return movies;
        
    }
    


    
    
    
    
}
