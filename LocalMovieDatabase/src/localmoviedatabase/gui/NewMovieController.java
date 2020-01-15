/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localmoviedatabase.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Rizvan
 */
public class NewMovieController implements Initializable
{
    
    private AppModel appModel;

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtGenre;
    @FXML
    private TextField txtRating;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtLength;
    @FXML
    private TextField txtPath;
    @FXML
    private Button btnAddMovie;
    @FXML
    private Button btnCancelMovie;
    @FXML
    private Button browsePath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void addMovie(ActionEvent event)
    {
        appModel = new AppModel();
        
        int rating = Integer.parseInt(txtRating.getText());
        int relDate = Integer.parseInt(txtYear.getText());
        
        if (rating > 0 && rating < 11)
        {
        appModel.addMovie(txtGenre.getText().trim(), txtTitle.getText().trim(), txtLength.getText().trim(), rating, relDate, txtPath.getText().trim());
        }
        else
        {
            System.out.println("Select a rating from 1 to 10");
        }
        Stage stage = (Stage) btnAddMovie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelMovie(ActionEvent event)
    {
        Stage stage = (Stage) btnCancelMovie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void browsePath(ActionEvent event)
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
    
}
