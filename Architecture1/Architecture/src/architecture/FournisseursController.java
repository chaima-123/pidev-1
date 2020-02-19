/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture;

import Services.DBInfoFournisseur;
import entities.Fournisseur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FournisseursController implements Initializable {

    @FXML
    private Button ajouterF;
    @FXML
    private Button modifierF;
    @FXML
    private Button rechercherF;
    @FXML
    private Button supprimerF;
    @FXML
    private Button afficherF;
    @FXML
    private TextField nomSociete;
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private TextField telephone;
    @FXML
    private TextField fax;
    @FXML
    private TextField id;

    public void clearFields() {
        cin.clear();
        email.clear();
        adresse.clear();
        telephone.clear();
        fax.clear();
        id.clear();
        nomSociete.clear();
    }

    public ObservableList<Fournisseur> data = FXCollections.observableArrayList(); //on utilise ObservableList pcq on a un tableView
    @FXML
    private Button mailF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void ajouterF(MouseEvent event) {
        if (verifChamps() && verifNum() && verifNom() && verifEmail()) {
            String CIN = cin.getText();
            String NomSociete = nomSociete.getText();
            String Adresse = adresse.getText();
            String Email = email.getText();
            String tel = telephone.getText();
            String Fax = fax.getText();
            int Cin = Integer.parseInt(CIN);
            int Tel = Integer.parseInt(tel);
            int faxx = Integer.parseInt(Fax);
            System.out.println(NomSociete);
            System.out.println(Adresse);
            System.out.println(Email);
            System.out.println(tel);
            System.out.println(faxx);

            Fournisseur u = new Fournisseur(Cin, Email, NomSociete, Adresse, Tel, Tel);
            int status = DBInfoFournisseur.insert(u);

            if (status > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ajouter fournisseur");
                alert.setHeaderText("Dialogue information");
                alert.setContentText("Ajout avec succés");

                alert.showAndWait();

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ajouter fournisseur");
                alert.setHeaderText("Dialogue ERREUR");
                alert.setContentText("Un probléme est survenu");

                alert.showAndWait();
            }
        }
    }

    @FXML
    private void rechercher(MouseEvent event) throws SQLException {
        String sid = id.getText();
        int idE = Integer.parseInt(sid);

        Fournisseur u = DBInfoFournisseur.getFournisseur(idE);

        nomSociete.setText((u.getNomSociete()));
        cin.setText(String.valueOf(u.getCin()));
        adresse.setText(u.getAdresse());
        telephone.setText(String.valueOf(u.getTelephone()));
        email.setText(u.getEmail());
        fax.setText(String.valueOf(u.getFax()));
    }

    @FXML
    private void supprimerF(MouseEvent event) {
        String sid = id.getText();
        int id = Integer.parseInt(sid);

        int status = DBInfoFournisseur.delete(id);
        if (status > 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Supprimer fournisseur");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("SUPPRESSION avec succés");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("SUPPRIMER FOURNISSEUR");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();
        }
    }

    @FXML
    private void afficherF(MouseEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("viewFsr.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Affichage des fournisseurs");
        primaryStage.show();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/architecture/FXMLHomePage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(scene);
        //stage.setTitle("Bienvenue");
        stage.show();
    }

    @FXML
    private void modifier1(ActionEvent event) {
        String sid = id.getText();
        int id = Integer.parseInt(sid);

        String Nom = nomSociete.getText();
        String Adresse = adresse.getText();
        String Email = email.getText();
        String CIN = cin.getText();
        String tel = telephone.getText();
        String faxx = fax.getText();
        int Cin = Integer.parseInt(CIN);
        int Tel = Integer.parseInt(tel);
        int fax = Integer.parseInt(faxx);

        Fournisseur u = new Fournisseur(Cin, Email, Nom, Adresse, Tel, fax);

        int status = DBInfoFournisseur.update(u, id);
        System.out.println(status);

        if (status == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Modifier fournisseur");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("Modification avec succés");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Modifier FOURNISSEUR");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();
        }

    }

    @FXML
    private void mailF(MouseEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("maile.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Envoyer un mail au fournisseur");
        primaryStage.show();

    }

    private boolean verifNum() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(telephone.getText());
        Matcher m2 = p.matcher(cin.getText());
        Matcher m1 = p.matcher(fax.getText());
        if (m.find() && m.group().equals(telephone.getText()) && m1.find() && m1.group().equals(fax.getText()) && m2.find() && m2.group().equals(cin.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validation de cin/tel/fax");
            alert.setHeaderText(null);
            alert.setContentText("Verifier les champs saisis");
            alert.showAndWait();
            return false;
        }
    }

    private boolean verifNom() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(nomSociete.getText());
        if (m.find() && m.group().equals(nomSociete.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le nom de la societe");
            alert.showAndWait();
            return false;
        }
    }

    private boolean verifEmail() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email.getText());
        if (m.find() && m.group().equals(email.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le mail");
            alert.showAndWait();
            return false;
        }
    }

    private boolean verifChamps() {
        if (cin.getText().isEmpty() | adresse.getText().isEmpty() | email.getText().isEmpty() | telephone.getText().isEmpty() | fax.getText().isEmpty() | nomSociete.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Il faut saisir dans tous les champs");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    
    

//    public void handleSure() throws IllegalStateException {
//        // display the showOptionDialog
//        Object[] options = {"OK", "Cancel"};
//        int choice = JOptionPane.showOptionDialog(null,
//                "Etes vous sure?",
//                "Sure?",
//                JOptionPane.YES_NO_OPTION,
//                JOptionPane.QUESTION_MESSAGE,
//                null,
//                options,
//                options[0]);
//
//        // interpret the user's choice
//        if (choice == JOptionPane.YES_OPTION) {
//            System.exit(0);
//        }
//    }
}
