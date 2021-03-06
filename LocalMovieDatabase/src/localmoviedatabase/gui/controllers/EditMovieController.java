/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import localmoviedatabase.be.Movie;
import localmoviedatabase.gui.AppModel;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class EditMovieController extends LmdbController implements Initializable
{
    
    private LmdbController mainController;
    private AppModel appModel;
   

    @FXML
    private Button saveEditMovie;
    @FXML
    private Button cancelEditWindow;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtRating;
    @FXML
    private Label ratingInfo;

    public void EditMovieController()
    {
        txtName.setText(LmdbController.getGetTitle());
        txtRating.setText("" + LmdbController.getGetRating());
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        EditMovieController();
    }    

    /**
     * saves the edits done to the movie
     * @param event the event that runs the code
     */
    @FXML
    private void saveEditMovie(ActionEvent event)
    {
        appModel = new AppModel();
        
        
        int rating = Integer.parseInt(txtRating.getText());
        

        if (rating > 0 && rating < 11) {
        Movie movie = new Movie(LmdbController.getGetId(), rating, txtName.getText().trim());
        appModel.updateMovie(movie);
        
        Stage stage = (Stage) saveEditMovie.getScene().getWindow();
        stage.close();
        }

        else{
        ratingInfo.setText("Invalid Rating!");
        }

    }

    /**
     * cancels the edit of the movie
     * @param event the event that runs the code
     */
    @FXML
    private void cancelEditMovie(ActionEvent event)
    {
        Stage stage = (Stage) cancelEditWindow.getScene().getWindow();
        stage.close();
    }
    
}
