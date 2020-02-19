/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class MaileController implements Initializable {

    @FXML
    private Button envoyer;
    @FXML
    private TextField mailFsr;
    @FXML
    private TextArea message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer(MouseEvent event) {
        String mail = mailFsr.getText();
        String msg = message.getText();
        EnvoiMail e=new EnvoiMail();
        e.envoyer(mail, msg);
        
    }
    
}
