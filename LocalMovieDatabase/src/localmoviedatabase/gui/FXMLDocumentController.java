/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author math2
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TableColumn<?, ?> movieTitle;
    @FXML
    private TableColumn<?, ?> movieRating;
    @FXML
    private TableColumn<?, ?> categoryName;
    @FXML
    private TextField showTitle;
    @FXML
    private TextField showCategory;
    @FXML
    private TextField showRating;
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
