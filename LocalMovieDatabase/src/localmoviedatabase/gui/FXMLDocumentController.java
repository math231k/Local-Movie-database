/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import localmoviedatabase.dal.dbaccess.DalException;

/**
 *
 * @author math2
 */
public class FXMLDocumentController implements Initializable
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

    private AppModel model;
    
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
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DalException ex)
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        selectedMovie();
        
    }

    private void movieTable()
    {
        movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        movieRating.setCellValueFactory(new PropertyValueFactory<>("rating"));
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
    }

    @FXML
    private void removeMovie(ActionEvent event)
    {
    }

    @FXML
    private void editMovie(ActionEvent event)
    {
    }

    @FXML
    private void playSelectedMovie(ActionEvent event)
    {
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
