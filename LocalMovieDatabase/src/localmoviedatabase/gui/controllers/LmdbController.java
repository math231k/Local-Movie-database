

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import localmoviedatabase.be.Genre;
import localmoviedatabase.be.Movie;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import localmoviedatabase.gui.AppModel;


/**
 *
 * @author math2
 */
public class LmdbController implements Initializable
{
    //private final MockMovieManager mmm = new MockMovieManager();
    //private final SearchMovies search = new SearchMovies();
    private AppModel model;
    private static int getRating;
    private static int getId;
    private static int getGenreId;
    private static String getTitle;
    private static String getPath;
    private Genre lastSelectedGenre;
    private Movie lastSelectedMovie;
    
    
    private Label label;
    @FXML
    private TableColumn<Movie, String> movieTitle;
    @FXML
    private TableColumn<Movie, String> movieRating;
    @FXML
    private TableColumn<Genre, String> categoryName;
    @FXML
    private TextField showTitle;
    private TextField showCategory;
    @FXML
    private TextField showRating;
    @FXML
    private TableView<Movie> movieTableView;
    @FXML
    private TableView<Genre> categoryTableView;
    @FXML
    private Button categoryRemove;
    @FXML
    private Button movieRemove;
    @FXML
    private TextField searchMovie;
    @FXML
    private Button movieEdit;
    @FXML
    private Button playButton;
    @FXML
    private Button categoryEdit;
    
    @FXML
    private Text genreTxt;

    @FXML
    private Button addMovie;
    @FXML
    private Button removeMovieFromCategoryBtn;
    @FXML
    private Button addCategory;
    @FXML
    private Button addToCategory1;
    @FXML
    private ListView<Movie> genreMoviesLst;

    
    /**
     * initializes our table views and movie selection upon startup
     * @param url
     * @param rb 
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new AppModel();
        movieTable();
        categoryTable();
        selectedMovie();
        selectedMovieFromGenre();
        genreMoviesLst.setItems(model.getGenreMovieList());
        model.deletionReminder();
        
        }

    /**
     * the list of movies
     */
    public void movieTable() 
    {
        movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        movieTableView.getColumns().clear();
        movieTableView.getColumns().addAll(movieTitle, movieRating);
        movieTableView.setItems(model.getMovies());
    }

    
    /**
     * the list of categories
     */
    public void categoryTable()
    {
        categoryName.setCellValueFactory(new PropertyValueFactory<>("genreName"));
        categoryTableView.getColumns().clear();
        categoryTableView.getColumns().addAll(categoryName);
        categoryTableView.setItems(model.getCategories());
    }
    

    /**
     * shows information about selected movie
     */
    public void selectedMovie()
    {
        showTitle.setEditable(false);
        showRating.setEditable(false);
        
        movieTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        movieTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>() {
            @Override
            public void changed(ObservableValue<? extends Movie> arg0, Movie oldValue, Movie newValue)
            {
                if (newValue != null)
                {
                    showTitle.setText(newValue.getTitle());
                    showRating.setText(newValue.getRating() + "");
                }
            }
        });
        
    }
    /**
     * shows information about selected movie
     */
    public void selectedMovieFromGenre()
    {
        showTitle.setEditable(false);
        showRating.setEditable(false);
        
        genreMoviesLst.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        genreMoviesLst.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>() {
            @Override
            public void changed(ObservableValue<? extends Movie> arg0, Movie oldValue, Movie newValue)
            {
                if (newValue != null)
                {
                    showTitle.setText(newValue.getTitle());
                    showRating.setText(newValue.getRating() + "");
                }
            }
        });
        
    }

  

    /**
     * Removes selected category
     * @param event 
     */
    @FXML
    private void removeCategory(ActionEvent event)
    {
        if(!categoryTableView.getSelectionModel().isEmpty())
        {
        Genre g = categoryTableView.getSelectionModel().getSelectedItem();
        model.removeGenre(g);
        
        } else
        {
            System.out.println("Please select the category you want to remove");
        }
    }

    /**
     * Opens a new window to add a movie
     * @param event
     * @throws IOException 
     */
    @FXML
    private void addMovie(ActionEvent event) throws IOException
    {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/localmoviedatabase/gui/views/NewMovie.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        stage.setTitle("New movie");
        stage.setAlwaysOnTop(true);
        movieTable();
       
        
    }

    
    /**
     * Removes selected movie
     * @param event 
     */
    @FXML
    private void removeMovie(ActionEvent event)
    {
        Movie m = movieTableView.getSelectionModel().getSelectedItem();
        model.removeMovie(m);
        
    }

    /**
     * Opens a new view to edit selected movie
     * @param event
     * @throws IOException 
     */
    @FXML
    private void editMovie(ActionEvent event) throws IOException
    {
        if(!movieTableView.getSelectionModel().isEmpty())
        {
        Movie editMovie = movieTableView.getSelectionModel().getSelectedItem();
        getId = editMovie.getId();
        getRating = editMovie.getRating();
        getTitle = editMovie.getTitle();
        
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/localmoviedatabase/gui/views/editMovie.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        stage.setTitle("Edit movie");
        stage.setAlwaysOnTop(true);
        movieTableView.getColumns().clear();
        movieTable();
        } else
        {
            System.out.println("Please select a movie to edit");
        }
 
    }

    /**
     * Plays the selected movie in a new view
     * @param event
     * @throws IOException 
     */
    @FXML
    private void playSelectedMovie(ActionEvent event) throws IOException
    {
        
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/localmoviedatabase/gui/views/VideoPlayerView.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        stage.setTitle("Movie Player");
        stage.setAlwaysOnTop(true);
        
    }

    
    /**
     * Edit selected category in a new view
     * @param event
     * @throws IOException 
     */
    @FXML
    private void editCategory(ActionEvent event) throws IOException
    {
        if(!categoryTableView.getSelectionModel().isEmpty())
        {
        Genre category = categoryTableView.getSelectionModel().getSelectedItem();
        getGenreId = category.getId();
        getTitle = category.getGenreName();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/localmoviedatabase/gui/views/EditCategory.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        stage.setTitle("Edit category");
        stage.setAlwaysOnTop(true);
        categoryTableView.getColumns().clear();
        categoryTable();
        } else
        {
            System.out.println("Please select a category to edit");
        }
    }

    
    /**
     * Searches the database for a specific String
     * @param event 
     */
   @FXML
    private void searchMovie(KeyEvent event)
    {
        String input = searchMovie.getText();
        ObservableList<Movie> resultMovies = model.searchMovie(input);
        movieTableView.setItems(resultMovies);
        categoryTable();       
    }


    /**
     * Adds a movie to a category
     * @param event 
     */
    @FXML
    private void AddMovieToCategory(ActionEvent event) {
        Movie moToBeAdded = movieTableView.getSelectionModel().getSelectedItem();
        Genre geToBeAdded = categoryTableView.getSelectionModel().getSelectedItem();
        
        model.addMovieToCategory(moToBeAdded, geToBeAdded);
        model.fetchMoviesFromGenre(geToBeAdded);
        //addMoviesToGenreList();
        System.out.println("Movie Added");
    }

     /**
     * sets the selected Movie
     * @param event 
     */
    @FXML
    private void setSlectedMovie(MouseEvent event) {
        lastSelectedMovie = genreMoviesLst.getSelectionModel().getSelectedItem();
        System.out.println(lastSelectedMovie.toString());
    }
    
    /**
     * gets all movies from a selected genre
     * @param event 
     */
    @FXML
    private void getMoviesInCategory(MouseEvent event) {
        Genre selectedGenre = categoryTableView.getSelectionModel().getSelectedItem();
        model.fetchMoviesFromGenre(selectedGenre);
        lastSelectedGenre = selectedGenre;
    }
    
    /**
     * Adds a Movie to a Genre
     */
    public void addMoviesToGenreList(){
        model.fetchMoviesFromGenre(lastSelectedGenre);
        ObservableList<Movie> oldMoviesInGenre = model.getGenreMovieList();
        ObservableList<Movie> moviesInGenre = model.getGenreMovieList();
        
        for (Movie movie : oldMoviesInGenre) {
        {
            moviesInGenre.add(movie);           
        }        
        genreMoviesLst.setItems(moviesInGenre);
    }
    }


    /**
     * removes a Movie From a Genre
     * @param event 
     */
    @FXML
    private void removeMovieFromCategory(ActionEvent event) {
    //Movie selectedMovie = genreMoviesLst.getSelectionModel().getSelectedItem();
    model.removeMovieFromGenre(lastSelectedMovie, lastSelectedGenre);
    model.fetchMoviesFromGenre(lastSelectedGenre);
    
    }

    /**
     * Opens a new window where you can add a category
     * @param event
     * @throws IOException 
     */
    @FXML
    private void addCategory(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/localmoviedatabase/gui/views/NewCategory.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.showAndWait();
        stage.setTitle("New category");
        stage.setAlwaysOnTop(true);
        categoryTable();
    }
   
    /**
     * Prepares a media for the mediaPlayer to play
     * @return a Media object
     */
    public Media getMediaToPlay(){
        
        if(getPath != null){
            System.out.println(getPath);
            File file = new File(getPath);
            Media media = new Media(file.toURI().toString());
            return media;
        } else{
            model.noMovieSelected();
            return null;
        }
        
        
    }

    /**
     * sets the selected movie
     * @param event 
     */
    @FXML
    private void setSelectedMovie(MouseEvent event) {
        lastSelectedMovie = movieTableView.getSelectionModel().getSelectedItem();
        getPath = lastSelectedMovie.getPath();
        System.out.println(lastSelectedMovie.getPath());
    }


    /**
     * gets the rating of a selected movie
     * @return the rating as an int
     */
    public static int getGetRating() {
        return getRating;
    }

    /**
     * gets the id of a selected Movie
     * @return the id as an int
     */
    public static int getGetId() {
        return getId;
    }

    /**
     * gets the genre id of a selected genre 
     * @return the genre id as an integer
     */
    public static int getGetGenreId() {
        return getGenreId;
    }

    /**
     * returns the title of a movie
     * @return the title as a String
     */
    public static String getGetTitle() {
        return getTitle;
    }
    
    /**
    * gets the path of a movie
    * @return the path as a string
    */
    public static String getGetPath() {
        return getPath;
    }
    
    
}

