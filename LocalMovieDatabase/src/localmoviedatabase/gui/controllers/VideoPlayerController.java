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
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author math2
 */
public class VideoPlayerController extends LmdbController implements Initializable {

    private MediaPlayer mp;
    private boolean isPaused;
    
    @FXML
    private MediaView mediaPlayer;
    @FXML
    private Button playBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isPaused = false;
    }    

    /**
     * Plays the movie/clip
     * @param event 
     */
    @FXML
    private void playPause(ActionEvent event) {
         mp = new MediaPlayer(getMediaToPlay());
        System.out.println(getMediaToPlay().getSource());
        mediaPlayer.setMediaPlayer(mp);
        mp.play();
    }
}
