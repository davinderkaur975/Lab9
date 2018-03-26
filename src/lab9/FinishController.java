/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Davinder Kaur
 */
public class FinishController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private ImageView imageView;
    private File imageFile;
    
    /**
     * This method takes the user back to the table view when the user pree the back to table button
     * @param event
     * @throws IOException 
     */
    public void backToTableButtonPushed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "TableView.fxml", "Finish");
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // set the default image 
        try{
            imageFile = new File("./src/images/finish.jpg");
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
        }
        catch(IOException e){
            System.err.println(e.getMessage());
            
        }
    }

    
    
}
