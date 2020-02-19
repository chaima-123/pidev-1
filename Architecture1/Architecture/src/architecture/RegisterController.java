/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture;

import Services.DBInfoUtilisateur;
import entities.user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField txCin;
    @FXML
    private TextField txEmail;
    @FXML
    private TextField txNom;
    @FXML
    private TextField txPrenom;
    @FXML
    private TextField txAdresse;
    @FXML
    private TextField txTel;
    @FXML
    private TextField txGender;
    @FXML
    private TextField txRole;
    @FXML
    private TextField txUsername;
    @FXML
    private TextField txPassword;
    @FXML
    private Button btnAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAjout(MouseEvent event) {
        String Nom = txNom.getText();
        String Prenom = txPrenom.getText();
        String Adresse = txAdresse.getText();
        String Username = txUsername.getText();
        String Password = txPassword.getText();
        String Email = txEmail.getText();
        String CIN = txCin.getText();
        String tel = txTel.getText();
        String Gender = txGender.getText();
        String Role = txRole.getText();
        int Cin = Integer.parseInt(CIN);
        int Tel = Integer.parseInt(tel);
        System.out.println(Nom);
        System.out.println(Prenom);
        System.out.println(Adresse);
        System.out.println(Username);
        System.out.println(Password);
        System.out.println(Email);
        System.out.println(tel);
        System.out.println(Gender);

        user u = new user(Cin, Email, Nom, Prenom, Adresse, Tel, Gender, Role, Username, Password);
        System.out.println("contructeur" + u);
        int status = DBInfoUtilisateur.insert(u);
       // int status = DBInfoUtilisateur.insert(u);

        if (status > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajouter utilisateur");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("Ajout avec succés");

            alert.showAndWait();

            System.out.println("Hamdoulah ya rabi");
            
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajouter utilisateur");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();
        }
    }
    
}
