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
import javafx.stage.Stage;

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

    public EditMovieController()
    {
        //todo
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    

    @FXML
    private void saveEditMovie(ActionEvent event)
    {
    }

    @FXML
    private void cancelEditMovie(ActionEvent event)
    {
        Stage stage = (Stage) cancelEditWindow.getScene().getWindow();
        stage.close();
    }
    
}
