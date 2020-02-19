package architecture;

import Services.DBInfoUtilisateur;
import entities.user;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class viewUsers implements Initializable {

    @FXML
    private TableView<user> table;
    @FXML
    private TableColumn<user, Integer> id;
    @FXML
    private TableColumn<user, Integer> cin;
    @FXML
    private TableColumn<user, String> email;
    @FXML
    private TableColumn<user, String> nom;
    @FXML
    private TableColumn<user, String> prenom;
    @FXML
    private TableColumn<user, String> adresse;
    @FXML
    private TableColumn<user, Integer> telephone;
    @FXML
    private TableColumn<user, String> gender;
    @FXML
    private TableColumn<user, String> role;
    @FXML
    private TableColumn<user, String> username;
    @FXML
    private TableColumn<user, String> password;

    public ObservableList<user> data = FXCollections.observableArrayList(); //on utilise ObservableList pcq on a un tableView

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        table.setItems(data);
    }

}
