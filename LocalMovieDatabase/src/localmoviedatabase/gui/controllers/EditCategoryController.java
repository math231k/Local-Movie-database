/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import localmoviedatabase.be.Genre;
import localmoviedatabase.gui.AppModel;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class EditCategoryController implements Initializable
{
    
    private LmdbController mainController;
    private AppModel appModel;

    @FXML
    private TextField txtCategoryName;
    @FXML
    private Button btnSaveCategory;
    @FXML
    private Button btnCancelCategory;

    public void EditCategoryController()
    {
        txtCategoryName.setText(LmdbController.getGetTitle());
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        EditCategoryController();
    }    

    /**
     * Save button to update changes in a category
     * @param event the event that runs the code
     */
    @FXML
    private void saveEditCategory(ActionEvent event)
    {
        appModel = new AppModel();
        
        Genre category = new Genre(LmdbController.getGetGenreId(), txtCategoryName.getText().trim());
        appModel.updateCategory(category);
        
        Stage stage = (Stage) btnSaveCategory.getScene().getWindow();
        stage.close();
        
        
    }

    /**
     * Closes the stage without updating
     * @param event the event that runs the code
     */
    @FXML
    private void cancelEditCategory(ActionEvent event)
    {
        Stage stage = (Stage) btnCancelCategory.getScene().getWindow();
        stage.close();
    }
    
}
