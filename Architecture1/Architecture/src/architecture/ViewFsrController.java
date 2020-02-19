package architecture;

import Services.DBInfoFournisseur;
import entities.Fournisseur;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ViewFsrController implements Initializable {

    @FXML
    private TableView<Fournisseur> table;
    @FXML
    private TableColumn<Fournisseur, Integer> id;
    @FXML
    private TableColumn<Fournisseur, Integer> cin;
    @FXML
    private TableColumn<Fournisseur, String> email;
    @FXML
    private TableColumn<Fournisseur, String> nomSociete;
    @FXML
    private TableColumn<Fournisseur, String> adresse;
    @FXML
    private TableColumn<Fournisseur, Integer> telephone;
    @FXML
    private TableColumn<Fournisseur, Integer> fax;

    public ObservableList<Fournisseur> data = FXCollections.observableArrayList(); //on utilise ObservableList pcq on a un tableView
    DBInfoFournisseur f=new DBInfoFournisseur();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            data.addAll(f.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(ViewFsrController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        nomSociete.setCellValueFactory(new PropertyValueFactory<>("nomSociete"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        fax.setCellValueFactory(new PropertyValueFactory<>("fax"));
        
        table.setItems(data);
    }

}
