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
import java.time.Duration;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import localmoviedatabase.be.Genre;
import localmoviedatabase.be.Movie;
import javafx.scene.input.KeyEvent;
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

    private final AppModel model = new AppModel();
    private final MockMovieManager mmm = new MockMovieManager();
    private final SearchMovies search = new SearchMovies(); 
    private MediaView mediaView;
    private TextField catNameFld;
    private TextField setTitleFld;
    private TextField pathFld;
    
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
    @FXML
    private Button addToCategory;
    private TableView<Movie> genreMovieTableView;
    @FXML
    private Text genreTxt;
    @FXML
    private ListView<Movie> genreMoviesLst;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
 
        movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        

        categoryName.setCellValueFactory(new PropertyValueFactory<>("name"));

        
            movieTableView.setItems(model.getMovies());
            categoryTableView.setItems(model.getCategories());
            //genreMovieTableView.setItems(model.getGenreMovieList());
            
            selectedMovie();
            
    
    }

    private void movieTable() throws DalException, IOException
    {
        movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        movieTableView.getColumns().clear();
        movieTableView.setItems(model.getMovies());
        movieTableView.getColumns().addAll(movieTitle, movieRating);
    }

    private void categoryTable() throws DalException
    {
        categoryName.setCellValueFactory(new PropertyValueFactory<>("genreName"));
        categoryTableView.getColumns().clear();
        categoryTableView.setItems(model.getCategories());
        categoryTableView.getColumns().addAll(categoryName);
    }
    
    private void genreMoviesTable()
    {
        genreMoviesLst.setItems(model.getGenreMovieList());
    }

    /**
     * shows information about selected movie
     */
    private void selectedMovie()
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
                    showRating.setText(newValue.getRating());
                }
            }
        });
        
    }

    private void addCategory(ActionEvent event) throws DalException, SQLException
    {
        
        Genre g = new Genre(catNameFld.getText());
        model.createGenre(g);
        model.getCategories();
       
    }

    @FXML
    private void removeCategory(ActionEvent event) throws DalException
    {
        Genre g = categoryTableView.getSelectionModel().getSelectedItem();
        model.removeGenre(g);
        System.out.println(g.getId());
        model.getCategories();
        
    }

    private void addMovie(ActionEvent event) throws DalException, IOException
    {
        
        String path = pathFld.getText();

        
        Movie mo = new Movie(setTitleFld.getText(), 200 + "", path);
        
        model.addMovie(mo);
        model.getMovies();
        
       
        
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/localmoviedatabase/gui/views/editMovie.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.setTitle("Edit movie");
            stage.showAndWait();
            stage.setAlwaysOnTop(true);
 
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
    private void editCategory(ActionEvent event)
    {
    }

    @FXML
    private void searchMovie(KeyEvent event) throws DalException, IOException
    {
        String input = searchMovie.getText();
        ObservableList<Movie> result = model.searchMovie(input);
        movieTableView.setItems(result);
    }

    private void FindPath(ActionEvent event) {
    JFileChooser jfc = new JFileChooser();
        FileNameExtensionFilter mp4Filter = new FileNameExtensionFilter(".mp4 Files", "mp4");
        FileNameExtensionFilter mpeg4Filter = new FileNameExtensionFilter(".mpeg4 Files", "mpeg4");
        jfc.setFileFilter(mp4Filter);
        jfc.setFileFilter(mpeg4Filter);
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setCurrentDirectory(new File("."));

        int returnValue = jfc.showOpenDialog(null);
        
        String path = jfc.getSelectedFile().toString();
        
        String replace = path.replace("\\", "/");
        
        pathFld.setText(replace);
                
        
    }

    @FXML
    private void AddMovieToCategory(ActionEvent event) {
    
            Movie selectedMovie = movieTableView.getSelectionModel().getSelectedItem();
        
            model.addMovieToCategory(selectedMovie);
            model.getGenreMovieList();
            
            
        
    }
    
    
    
}
