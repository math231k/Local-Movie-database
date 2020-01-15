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
    
    final private MovieManager movieManager;
    final private CategoryManager categoryManager;
    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    private ObservableList<Genre> categories = FXCollections.observableArrayList();
    private ObservableList<Movie> moviesInGenre = FXCollections.observableArrayList();
    private SearchMovies search = new SearchMovies();
    private Genre currentlySelectedGenre;

    
    public AppModel()
    {
        movieManager = new MovieManager();
        categoryManager = new CategoryManager();
        fetchMovies();
        fetchCategories();
    }
    
    /**
     * adds all database Genres to an ObservableList
     */
    public void fetchCategories()
    {
        categories.clear();
        categories.addAll(categoryManager.getAllCategories());
    }        
    

    /**
     * Adds all Movies to an Observable List
     */
    public void fetchMovies()
    {
        movies.clear();
        movies.addAll(movieManager.getAllMovies());
        
        
    }
    
    /**
     * adds all movies from a specified genre to an ObservableList
     * @param genre 
     */
    public void fetchMoviesFromGenre(Genre genre){
        
        moviesInGenre.clear();
        moviesInGenre.addAll(categoryManager.getAllMoviesInGenre(genre));
        currentlySelectedGenre = genre;
        
    }


    /**
    * Adds a movie to the database
    * @param m the movie to be added
    */
    public void addMovie(Movie m) {
        movieManager.addMovie(m);
    }

    /**
     * Removes a movie from the database
     * @param m the movie to be deleted
     */
    public void removeMovie(Movie m) {
        movieManager.removeMovie(m);
    }
    

    public void updateMovie(Movie m){
        movieManager.updateMovie(m);
        getMovies();
        
    }
    

    public void updateCategory(Genre g)
    {
        categoryManager.updateGenre(g);
        getCategories();
    }
    
    
    /**
     * Returns a list with a search result consisting of movies
     * @param input the String to compare to
     * @return an ObservableList of movies
     */
    public ObservableList<Movie> searchMovie(String input){
        List<Movie> filter = search.searchMovie(getMovies(),input);
        
        ObservableList<Movie> result = FXCollections.observableList(filter);
        return result;
    
    }
    
    /**
     * Returns a list with a search result consisting of genres
     * @param input the String to compare to
     * @return an ObservableList of genres
     */
    public ObservableList<Genre> searchGenre(String input){
        List<Genre> filter = search.searchCategory(getCategories(),input);
        
        ObservableList<Genre> result = FXCollections.observableList(filter);
        return result;
    }
    
    /**
     * creates a prompt warning the user that no movie is selected
     */
    public void noMovieSelected(){
        Alert noSelectionAlert = new Alert(Alert.AlertType.ERROR);
                
                noSelectionAlert.setTitle("No movie");
                noSelectionAlert.setHeaderText("Movie not Selected");
                noSelectionAlert.setContentText("Select a movie to play");
                
                noSelectionAlert.showAndWait();
    }
    
    /**
     * adds a new genre to the database
     * @param g the genre to be added
     */
    public void createGenre(Genre g){
        categoryManager.createGenre(g);
        
    }
    
    /**
     * removes a genre from the database
     * @param g the genre to be deleted
     */
    public void removeGenre(Genre g) {
    categoryManager.removeGenre(g);
    }

    /**
     * add a movie to a specified genre
     * @param movie the movie to be added
     * @param genre the genre to get the movie
     */
    public void addMovieToCategory(Movie movie, Genre genre) {
        categoryManager.addMovieToCategory(movie, genre);
        fetchMoviesFromGenre(genre);
    }

    
    /**
     * 
     * @return An Observablelist of movies in a genre
     */
    public ObservableList<Movie> getGenreMovieList(){
        moviesInGenre.clear();
        return moviesInGenre;
    }
    
    /**
     * 
     * @return an Observable list of genres
     */
    public ObservableList<Genre> getCategories(){
        categories.clear();
        fetchCategories();
        return categories;
    }
    

    /**
     * 
     * @return An observable list of movies
     */
    public ObservableList<Movie> getMovies(){
        movies.clear();
        fetchMovies();
        return movies;
    }

    void removeMovieFromGenre(Movie selectedMovie, Genre selectedGenre) {
        selectedGenre.deleteMovie(selectedMovie);
        categoryManager.removeMovieFromGenre(selectedMovie, selectedGenre);
        fetchMoviesFromGenre(selectedGenre);
        fetchCategories();
        
    }
    


    
    
    
    
}
