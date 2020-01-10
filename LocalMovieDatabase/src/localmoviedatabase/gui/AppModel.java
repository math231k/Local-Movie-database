/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import localmoviedatabase.be.Genre;
import localmoviedatabase.be.Movie;
import localmoviedatabase.bll.CategoryManager;
import localmoviedatabase.bll.MovieManager;
import localmoviedatabase.bll.util.SearchMovies;
import localmoviedatabase.dal.dbaccess.DalException;

/**
 *
 * @author math2
 */
public class AppModel
{
    
    private MovieManager movieManager;
    private CategoryManager categoryManager;
    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    private ObservableList<Genre> categories = FXCollections.observableArrayList();
    private SearchMovies search = new SearchMovies();

    
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


    void addMovie(Movie m) {
        
        movieManager.addMovie(m);
    }

    void removeMovie(Movie m) {
        movieManager.removeMovie(m);
    }
    
    void updateMovie(Movie m){
        movieManager.updateMovie(m);
    }
    
    public ObservableList<Movie> searchMovie(String input) throws DalException, IOException{
        List<Movie> filter = search.searchMovie(getMovies(),input);
        
        ObservableList<Movie> result = FXCollections.observableList(filter);
        return result;
    }
    
    public void noMovieSelected(){
        Alert noSelectionAlert = new Alert(Alert.AlertType.ERROR);
                
                noSelectionAlert.setTitle("No Song");
                noSelectionAlert.setHeaderText("Song not Selected");
                noSelectionAlert.setContentText("Select a song to play");
                
                noSelectionAlert.showAndWait();
    }
    
    public void createGenre(Genre g) throws DalException, SQLException{
        categoryManager.createGenre(g);
        
    }

    void removeGenre(Genre g) throws DalException, SQLException {
        categoryManager.removeGenre(g);
    }
    


    
    
    
    
}
