

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.AmbientLight;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import localmoviedatabase.be.Genre;
import localmoviedatabase.be.Movie;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import localmoviedatabase.bll.util.SearchMovies;
import localmoviedatabase.dal.dbaccess.DalException;
import localmoviedatabase.dal.dbmanagers.mockdatamanagers.MockMovieManager;


/**
 *
 * @author math2
 */
public class LmdbController implements Initializable
{
    private MockMovieManager mmm = new MockMovieManager();
    private SearchMovies search = new SearchMovies();
    private AppModel model;
    public static int getRating;
    public static int getId;
    public static int getGenreId;
    public static int getRelDate;
    public static String getTitle;
    public static String getGenre;
    public static String getLength;
    public static String getPath;
    public Genre lastSelectedGenre;
    
    
    private Label label;
    @FXML
    private TableColumn<Movie, String> movieTitle;
    @FXML
    private TableColumn<Movie, String> movieRating;
    @FXML
    private TableColumn<Genre, String> categoryName;
    @FXML
    private TextField showTitle;
    @FXML
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
    private MediaView mediaView;
    private TextField catNameFld;
    @FXML
    private Button addToCategory;
    @FXML

    private ListView<Movie> genreMoviesLst;
    @FXML
    private Text genreTxt;

    @FXML
    private Button addMovie;
    @FXML
    private Button removeMovieFromCategoryBtn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new AppModel();
        movieTable();
        categoryTable();
        selectedMovie();
        }

    public void movieTable() 
    {
        movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        movieTableView.getColumns().clear();
        movieTableView.getColumns().addAll(movieTitle, movieRating);
        movieTableView.setItems(model.getMovies());
    }

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
        showCategory.setEditable(false);
        showRating.setEditable(false);
        
        movieTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        movieTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>() {
            @Override
            public void changed(ObservableValue<? extends Movie> arg0, Movie oldValue, Movie newValue)
            {
                if (newValue != null)
                {
                    showTitle.setText(newValue.getTitle());
                    showCategory.setText(newValue.getCategory());
                    showRating.setText(newValue.getRating() + "");
                }
            }
        });
        
    }

    private void addCategory(ActionEvent event) throws DalException, SQLException
    {
        
        Genre g = new Genre(catNameFld.getText());
        model.createGenre(g);
        model.getCategories();
        
        System.out.println(g.getId());
       
    }

    @FXML
    private void removeCategory(ActionEvent event)
    {
        Genre g = categoryTableView.getSelectionModel().getSelectedItem();
        model.removeGenre(g);
    }

    @FXML
    private void addMovie(ActionEvent event)
    {
        JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter mp4Filter = new FileNameExtensionFilter(".mp4 Files", "mp4");
        FileNameExtensionFilter mpeg4Filter = new FileNameExtensionFilter(".mpeg4 Files", "mpeg4");
        jfc.setFileFilter(mp4Filter);
        jfc.setFileFilter(mpeg4Filter);
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setCurrentDirectory(new File("."));

        int returnValue = jfc.showOpenDialog(null);
        String path = jfc.getSelectedFile().toString();
        String title = jfc.getSelectedFile().getName();
        
        Media m = new Media(jfc.getSelectedFile().getPath());
        
       
        
    }

    @FXML
    private void removeMovie(ActionEvent event)
    {
        Movie m = movieTableView.getSelectionModel().getSelectedItem();
        model.removeMovie(m);
        
    }

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

    @FXML
    private void playSelectedMovie(ActionEvent event)
    {
        if(!movieTableView.getSelectionModel().isEmpty()){
        String path = movieTableView.getSelectionModel().getSelectedItem().getPath();
        
        File file = new File(path);
        Media media = new Media(file.toURI().toString());
        MediaPlayer mp = new MediaPlayer(media);
        
        mediaView.setMediaPlayer(mp);
        mp.play();
        }
        
        else if(movieTableView.getSelectionModel().isEmpty()){
            model.noMovieSelected();
        }
        
    }

    @FXML
    private void editCategory(ActionEvent event) throws IOException, DalException
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

   @FXML
    private void searchMovie(KeyEvent event) throws DalException, IOException
    {
        String input = searchMovie.getText();
        ObservableList<Movie> resultMovies = model.searchMovie(input);
        ObservableList<Genre> resultGenre = model.searchGenre(input);
        movieTableView.setItems(resultMovies);
        if (input.length()>0){
            categoryTableView.setItems(resultGenre);
        }
        else
        {
            categoryTable();
        }
    }


    @FXML
    private void AddMovieToCategory(ActionEvent event) {
        Movie moToBeAdded = movieTableView.getSelectionModel().getSelectedItem();
        Genre geToBeAdded = categoryTableView.getSelectionModel().getSelectedItem();
        
        model.addMovieToCategory(moToBeAdded, geToBeAdded);
        model.fetchMoviesFromGenre(geToBeAdded);
        System.out.println("Movie Added");
    }

    @FXML
    private void getMoviesInCategory(MouseEvent event) {
        populateMoviesInGenreList();
        Genre selectedGenre = categoryTableView.getSelectionModel().getSelectedItem();
        lastSelectedGenre = selectedGenre;
        model.fetchMoviesFromGenre(selectedGenre);
    }
    
    private void populateMoviesInGenreList() {
        // custom rendering of the list cell
        genreMoviesLst.setCellFactory(param -> new ListCell<Movie>() {
            @Override
            protected void updateItem(Movie item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getTitle() == null) {
                    setText(null);
                } else {
                    setText((this.getIndex() + 1) + ". " + item.getTitle());
                }
            }
        });

        // add data to listview
        genreMoviesLst.setItems(model.getGenreMovieList());
    }

    @FXML
    private void loadMovie(MouseEvent event) {
    
    }

    @FXML
    private void removeMovieFromCategory(ActionEvent event) {
    Movie selectedMovie = genreMoviesLst.getSelectionModel().getSelectedItem();
    model.removeMovieFromGenre(selectedMovie, lastSelectedGenre);
    populateMoviesInGenreList();
    model.fetchMoviesFromGenre(lastSelectedGenre);
    }
   
}

