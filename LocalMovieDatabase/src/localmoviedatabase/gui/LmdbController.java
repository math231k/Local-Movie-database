/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.length;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import localmoviedatabase.dal.dbaccess.DalException;
import localmoviedatabase.dal.dbmanagers.mockdatamanagers.MockMovieManager;


/**
 *
 * @author math2
 */
public class LmdbController implements Initializable
{

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
    private Button categoryAdd;
    @FXML
    private Button categoryRemove;
    @FXML
    private Button movieAdd;
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
    
    private MockMovieManager mmm = new MockMovieManager();

    private AppModel model;
    @FXML
    private MediaView mediaView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            model = new AppModel();
            movieTable();
            categoryTable();
            
        } catch (IOException ex)
        {
            Logger.getLogger(LmdbController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DalException ex)
        {
            Logger.getLogger(LmdbController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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

    @FXML
    private void addCategory(ActionEvent event)
    {
        
        
    }

    @FXML
    private void removeCategory(ActionEvent event)
    {
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
    private void editMovie(ActionEvent event)
    {
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
    private void searchMovie(KeyEvent event)
    {
    }
}
