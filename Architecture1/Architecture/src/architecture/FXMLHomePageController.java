/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FXMLHomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void utilisateur(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/architecture/usr.fxml"));
        Scene scene = new Scene (root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle("Gestion des utilisateurs");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void articles(ActionEvent event) {
    }

    @FXML
    private void commandes(ActionEvent event) {
    }

    @FXML
    private void sorties(ActionEvent event) {
    }

    @FXML
    private void entrees(ActionEvent event) {
    }

    @FXML
    private void fournisseurs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/architecture/fsr.fxml"));
        Scene scene = new Scene (root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle("Gestion des fournisseurs");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
}
