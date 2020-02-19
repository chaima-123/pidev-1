package architecture;

import Services.DBInfoUtilisateur;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UtilisateurController implements Initializable {

    @FXML
    TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField adresse;
    @FXML
    private TextField telephone;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField gender;
    @FXML
    private TextField role;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button rechercher;
    @FXML
    private Button supprimer;

    @FXML
    private void afficher(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("viewUsers.fxml"));
        Scene scene = new Scene(root);
        //primaryStage.hide();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Affichage des utilisateurs");
        primaryStage.show();
    }

//        ComboBox Gender = (ComboBox) gender.getValue();
//        ComboBox Rol = (ComboBox) role.getValue();
//        String Genre= Gender.toString();
//        String Role= Rol.toString();
    public ObservableList<user> data = FXCollections.observableArrayList(); //on utilise ObservableList pcq on a un tableView

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        try {
            String sql = "SELECT*  FROM utilisateur WHERE 1";
            Connection con = DBInfoUtilisateur.getConnection();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                data.add(new user(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ajouter(MouseEvent event) throws IOException {
        if (verifChamps() && verifNum() && verifNom() && verifEmail()) {
            System.out.println("aaaaaaaaaaaaaa");
            String Nom = nom.getText();
            String Prenom = prenom.getText();
            String Adresse = adresse.getText();
            String Username = username.getText();
            String Password = password.getText();
            String Email = email.getText();
            String CIN = cin.getText();
            String tel = telephone.getText();
            String Gender = gender.getText();
            String Role = role.getText();
            int Cin = Integer.parseInt(CIN);
            int Tel = Integer.parseInt(tel);
            System.out.println(nom.getText());
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
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Ajouter utilisateur");
                alert.setHeaderText("Dialogue information");
                alert.setContentText("Ajout avec succés");

                alert.showAndWait();

                System.out.println("Hamdoulah ya rabi");
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLHomePage.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = new Stage(StageStyle.DECORATED);
//        stage.setScene(scene);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.show();

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ajouter utilisateur");
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

        user u = DBInfoUtilisateur.getUser(idE);

        nom.setText((u.getNom()));
        prenom.setText((u.getPrenom()));
        cin.setText(String.valueOf(u.getCin()));
        adresse.setText(u.getAdresse());
        telephone.setText(String.valueOf(u.getTelephone()));
        email.setText(u.getEmail());
        role.setText(u.getRole());
        gender.setText(u.getGender());
        username.setText(u.getUsername());
        password.setText(u.getPassword());

    }

    @FXML
    private void supprimer(MouseEvent event) {
        String sid = id.getText();
        int id = Integer.parseInt(sid);

        int status = DBInfoUtilisateur.delete(id);
        if (status > 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Supprimer utilisateur");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("SUPPRESSION avec succés");

            alert.showAndWait();

            System.out.println("Hamdoulah ya rabi");
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("SUPPRIMER UTILISATEUR");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();
        }
    }

    @FXML
    private void modifier(MouseEvent event) {
        String sid = id.getText();
        int id = Integer.parseInt(sid);

        String Nom = nom.getText();
        String Prenom = prenom.getText();
        String Adresse = adresse.getText();
        String Username = username.getText();
        String Password = password.getText();
        String Email = email.getText();
        String CIN = cin.getText();
        String tel = telephone.getText();
        String Gender = gender.getText();
        String Role = role.getText();
        int Cin = Integer.parseInt(CIN);
        int Tel = Integer.parseInt(tel);

        user u = new user(Cin, Email, Nom, Prenom, Adresse, Tel, Gender, Role, Username, Password);

        int status = DBInfoUtilisateur.update(u, id);
        System.out.println(status);
        if (status == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Modifier utilisateur");
            alert.setHeaderText("Dialogue information");
            alert.setContentText("Modification avec succés");

            alert.showAndWait();

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Modifier utilisateur");
            alert.setHeaderText("Dialogue ERREUR");
            alert.setContentText("Un probléme est survenu");

            alert.showAndWait();
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

    public boolean verifNum() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(telephone.getText());
        Matcher m2 = p.matcher(cin.getText());
        if (m.find() && m.group().equals(telephone.getText()) && m2.find() && m2.group().equals(cin.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validation ");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le num ou cin saisi");
            alert.showAndWait();
            return false;
        }
    }

    public boolean verifMdp() {
        Pattern p = Pattern.compile("((?=.*[a-z])(?=.*[A-Z]).{6,15})");
        Matcher m = p.matcher(password.getText());
        if (m.matches()) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le mot de passe \n il doit contenir au moins une lettre en minuscule, une en majuscule et de longeur entre 6 à 15 caractéres");
            alert.showAndWait();
            return false;
        }
    }

    public boolean verifNom() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(nom.getText());
        if (m.find() && m.group().equals(nom.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le nom ");
            alert.showAndWait();
            return false;
        }
    }

    public boolean verifChamps() {
        if (cin.getText().isEmpty() | adresse.getText().isEmpty() | email.getText().isEmpty() | telephone.getText().isEmpty() | username.getText().isEmpty() | password.getText().isEmpty() | gender.getText().isEmpty() | prenom.getText().isEmpty() | nom.getText().isEmpty() | role.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validation des champs");
            alert.setHeaderText(null);
            alert.setContentText("Il faut saisir dans tous les champs");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
