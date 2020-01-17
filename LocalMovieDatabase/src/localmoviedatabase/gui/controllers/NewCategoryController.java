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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import localmoviedatabase.gui.AppModel;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class NewCategoryController implements Initializable
{

    private AppModel appModel;
    
    @FXML
    private TextField txtCategoryName;
    @FXML
    private Button btnCancelNewCategory;
    @FXML
    private Button btnSaveNewCategory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    
    /**
     * Closes the stage without creating new category
     * @param event 
     */
    @FXML
    private void cancelNewCategory(ActionEvent event)
    {
        Stage stage = (Stage) btnCancelNewCategory.getScene().getWindow();
        stage.close();
    }

    /**
     * Creates a new category
     * @param event 
     */
    @FXML
    private void saveNewCategory(ActionEvent event)
    {
        appModel = new AppModel();
        appModel.createGenre(txtCategoryName.getText());
        
        Stage stage = (Stage) btnSaveNewCategory.getScene().getWindow();
        stage.close();
    }
    
}
