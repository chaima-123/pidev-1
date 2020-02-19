/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.user;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import architecture.UtilisateurController;

/**
 *
 * @author Asus
 */
public class DBInfoUtilisateur {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/pi";
            String login = "root";
            String pwd = "";
            con = DriverManager.getConnection(url, login, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return con;
    }

    public static int insert(user t) {
        int st = 0;

        try {
            String requeteInsert = "INSERT INTO `pi`.`utilisateur` (`cin`, `email`, `nom`, `prenom`,`adresse`, `telephone`, `gender`, `role`,`username`,`password`) VALUES ( ?,?,?,?,?,?,?,?,?,?);";
            Connection con = DBInfoUtilisateur.getConnection();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(requeteInsert);
            pre.setInt(1, t.getCin());
            pre.setString(2, t.getEmail());
            pre.setString(3, t.getNom());
            pre.setString(4, t.getPrenom());
            pre.setString(5, t.getAdresse());
            pre.setInt(6, t.getTelephone());
            pre.setString(7, t.getGender());
            pre.setString(8, t.getRole());
            pre.setString(9, t.getUsername());
            pre.setString(10, t.getPassword());

            st = pre.executeUpdate();
            con.close();
            System.out.println("utilisateur ajouté avec succés");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static int update(user p, int id) {
        int st = 0;
        try {
            String req = "UPDATE `utilisateur` SET `cin`='" + p.getCin() + "', `email`='" + p.getEmail() + "', `nom`='" + p.getNom() + "', `prenom`='" + p.getPrenom() + "', `adresse`='" + p.getAdresse() + "', `telephone`='" + p.getTelephone() + "', `gender`='" + p.getGender() + "', `role`='" + p.getRole() + "', `username`='" + p.getUsername() + "', `password`='" + p.getPassword() + "' WHERE `id` ='" + id + "'";
            Connection con = DBInfoUtilisateur.getConnection();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static int delete(int id) {
        int st = 0;
        try {
            String req = "delete from utilisateur where id='" + id + "'";//+id;
            Connection con = DBInfoUtilisateur.getConnection();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            st = pre.executeUpdate(req);
            System.out.println("utilisateur supprimé avec succés");
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static user getUser(int id) throws SQLException {
        String req = "SELECT * from utilisateur where id='" + id + "'";//+id;
        Connection con = DBInfoUtilisateur.getConnection();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
        user u = new user();
        ResultSet rs = pre.executeQuery(req);
        // System.out.println(rs);

        while (rs.next()) {

            int cin = rs.getInt("cin");
            String emalil = rs.getString("email");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String adresse = rs.getString("adresse");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String gendre = rs.getString("gender");
            String role = rs.getString("role");
            int tel = rs.getInt("telephone");
            user uu = new user(id, cin, emalil, nom, prenom, adresse, tel, gendre, role, username, password);

            u = uu;
        }
        return u;
    }

    public static List<user> readAll() throws SQLException {
        List<user> arr = new ArrayList<user>();
        String req = "SELECT* from user where 1";//+id;
        Connection con = DBInfoUtilisateur.getConnection();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            user u = new user();
            u.setId(rs.getInt(1));
            u.setCin(rs.getInt(2));
            u.setEmail(rs.getString(3));
            u.setNom(rs.getString(4));
            u.setPrenom(rs.getString(5));
            u.setAdresse(rs.getString(6));
            u.setTelephone(rs.getInt(7));
            u.setGender(rs.getString(8));
            u.setRole(rs.getString(9));
            u.setUsername(rs.getString(10));
            u.setPassword(rs.getString(11));
            arr.add(u);

        }
        return arr;
    }

    public static user getPasswordFromEmail(String email) {
        user u = null;
        try {
            PreparedStatement preparedStatement = DBInfoUtilisateur.getConnection().prepareStatement(
                    "SELECT * from utilisateur WHERE email ='" + email + "'"); //"SELECT * from utilisateur WHERE email = ?")
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                u = getUser(resultSet.getInt(1));
            }

            //return utilisateur;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;

    }
}
