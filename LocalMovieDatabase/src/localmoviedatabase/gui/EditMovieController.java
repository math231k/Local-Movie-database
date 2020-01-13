/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui;

import java.io.IOException;
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
import localmoviedatabase.dal.dbaccess.DalException;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class EditMovieController implements Initializable
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
        txtName.setText(LmdbController.getTitle);
        txtRating.setText("" + LmdbController.getRating);
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        EditMovieController();
    }    

    @FXML
    private void saveEditMovie(ActionEvent event) throws IOException, DalException
    {
        appModel = new AppModel();
        
        
        int rating = Integer.parseInt(txtRating.getText());
        
        if (rating > 0 && rating < 11) {
        Movie movie = new Movie(LmdbController.getId, rating, txtName.getText().trim());
        appModel.updateMovie(movie);
        
        Stage stage = (Stage) saveEditMovie.getScene().getWindow();
        stage.close();
        }
        else{
        ratingInfo.setText("Invalid Rating!");
        }
    }

    @FXML
    private void cancelEditMovie(ActionEvent event)
    {
        Stage stage = (Stage) cancelEditWindow.getScene().getWindow();
        stage.close();
    }
    
}
