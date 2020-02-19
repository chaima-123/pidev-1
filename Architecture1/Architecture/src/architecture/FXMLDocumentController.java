/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture;

import java.io.IOException;
import static java.lang.System.load;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Services.DBInfoUtilisateur;
import entities.user;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Asus
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label etat;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button button;
    @FXML
    private Button registrer;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button mdp;

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        //System.out.println("You clicked me!");
        Connection con = DBInfoUtilisateur.getConnection();
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT* FROM utilisateur where username=? AND password=?";
        try {
            stat = con.prepareStatement(sql);
            stat.setString(1, username.getText().toString());
            stat.setString(2, password.getText().toString());
            rs = stat.executeQuery();
            if (rs.next()) {
                etat.setText("Connecté");

                Parent HomePage_parent = FXMLLoader.load(getClass().getResource("FXMLHomePage.fxml"));
                Scene HomePage_scene = new Scene(HomePage_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(HomePage_scene);
                app_stage.show();

            } else {
                etat.setText("Identifiant ou mdp incorrect!!");
                showDialog("ERROR", null, "Identifiant/Mot de passe saisi est incorrect ! Veuillez réessayer");

            }
        } catch (Exception e) {

        }
    }

    public void showDialog(String info, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(info);
        alert.setHeaderText(header);
        alert.setContentText(title);

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //anchorPane.setCenter(root);
    }

    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void registrer(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle("Création d'un nouveau compte");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void mdp(MouseEvent event) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle("Recuperer Mot de passe");
        textInputDialog.setHeaderText("Mot de passe");
        textInputDialog.setContentText("Entrer votre Email");

        Optional<String> email = textInputDialog.showAndWait();

        email.ifPresent(userEmail -> {
            user user = DBInfoUtilisateur.getPasswordFromEmail(userEmail);

            if (user != null) {
                MailMdp.sendMail(user.getEmail(), "Mot de passe oublie", "cher client " + user.getNom() + " voila votre mot de passe " + user.getPassword());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Mot de passe envoye! Verifier votre boite mail");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("email non trouve");
                alert.showAndWait();
            }
        });
    }

}
