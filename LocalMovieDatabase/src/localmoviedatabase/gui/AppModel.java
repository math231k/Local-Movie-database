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
public final class AppModel
{
    
    private MovieManager movieManager;
    private CategoryManager categoryManager;
    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    private ObservableList<Genre> categories = FXCollections.observableArrayList();
    private ObservableList<Movie> moviesInGenre = FXCollections.observableArrayList();
    private SearchMovies search = new SearchMovies();
    private Genre currentlySelectedGenre;

    
    public AppModel() throws IOException, DalException
    {
        movieManager = new MovieManager();
        categoryManager = new CategoryManager();
        fetchMovies();
        fetchCategories();
    }
    
    public void fetchCategories()
    {
        categories.clear();
        categories.addAll(categoryManager.getAllCategories());
    }        
    
    public void fetchMovies() throws IOException, DalException
    {
        movies.clear();
        movies.addAll(movieManager.getAllMovies());
        
    }
    
    public void fetchMoviesFromGenre(Genre genre){
        moviesInGenre.clear();
        moviesInGenre.addAll(categoryManager.getAllMoviesInGenre(genre));
        currentlySelectedGenre = genre;
    }



    public void addMovie(Movie m) {
        movieManager.addMovie(m);
    }

    public void removeMovie(Movie m) {
        movieManager.removeMovie(m);
    }
    
    public void updateMovie(Movie m){
        movieManager.updateMovie(m);
        
    }
    
    public ObservableList<Movie> searchMovie(String input){
        List<Movie> filter = search.searchMovie(getMovies(),input);
        
        ObservableList<Movie> result = FXCollections.observableList(filter);
        return result;
    }
    
    public ObservableList<Genre> searchGenre(String input) throws DalException, IOException{
        List<Genre> filter = search.searchCategory(getCategories(),input);
        
        ObservableList<Genre> result = FXCollections.observableList(filter);
        return result;
    }
    
    public void noMovieSelected(){
        Alert noSelectionAlert = new Alert(Alert.AlertType.ERROR);
                
                noSelectionAlert.setTitle("No movie");
                noSelectionAlert.setHeaderText("Movie not Selected");
                noSelectionAlert.setContentText("Select a movie to play");
                
                noSelectionAlert.showAndWait();
    }
    
    public void createGenre(Genre g){
        categoryManager.createGenre(g);
        
    }

    public void removeGenre(Genre g) {
    categoryManager.removeGenre(g);
    }

    public void addMovieToCategory(Movie movie, Genre genre) {
        categoryManager.addMovieToCategory(movie, genre);
        fetchMoviesFromGenre(genre);
    }

    public ObservableList<Movie> getGenreMovieList(){
        moviesInGenre.clear();
        return moviesInGenre;
    }
    
    public ObservableList<Genre> getCategories(){
        categories.clear();
        fetchCategories();
        return categories;
    }
    
    public ObservableList<Movie> getMovies() throws IOException, DalException{
        movies.clear();
        fetchMovies();
        return movies;
    }
    


    
    
    
    
}
