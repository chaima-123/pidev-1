/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Fournisseur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class DBInfoFournisseur {

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

    public static int insert(Fournisseur t) {
        int st = 0;
        
        //if (verifChamps() & verifNum() & verifEmail()){
        try {
            String requeteInsert = "INSERT INTO `pi`.`fournisseur` (`cin`, `email`, `nomSociete`,`adresse`, `telephone`, `fax`) VALUES ( ?,?,?,?,?,?);";
            Connection con = DBInfoFournisseur.getConnection();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(requeteInsert);
            pre.setInt(1, t.getCin());
            pre.setString(2, t.getEmail());
            pre.setString(3, t.getNomSociete());
            pre.setString(4, t.getAdresse());
            pre.setInt(5, t.getTelephone());
            pre.setInt(6, t.getFax());

            st = pre.executeUpdate();
            con.close();
            System.out.println("fournisseur ajouté avec succés");
            //clearFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //}
        return st;
    }

    public static int update(Fournisseur p,int id) {
        int st = 0;
        try {
            String req = "UPDATE `fournisseur` SET `cin`='"+p.getCin()+"', `email`='"+p.getEmail()+"', `nomSociete`='"+p.getNomSociete()+"', `adresse`='"+p.getAdresse()+"', `telephone`='"+p.getTelephone()+"', `fax`='"+p.getFax()+"' WHERE `id` ='"+id+"'";
            Connection con = DBInfoFournisseur.getConnection();
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
            String req = "delete from fournisseur where id='" + id + "'";//+id;
            Connection con = DBInfoFournisseur.getConnection();
            PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
            st = pre.executeUpdate(req);
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    public static Fournisseur getFournisseur(int id) throws SQLException {
        String req = "SELECT * from fournisseur where id='"+id+"'";

        Connection con = DBInfoFournisseur.getConnection();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);
        Fournisseur u = new Fournisseur();
        ResultSet rs = pre.executeQuery(req);

        while (rs.next()) {

            int cin = rs.getInt("cin");
            String emalil = rs.getString("email");
            String nomSociete = rs.getString("nomSOciete");
            String adresse = rs.getString("adresse");
            int tel = rs.getInt("telephone");
            int fax = rs.getInt("fax");
            Fournisseur uu = new Fournisseur(id, cin, emalil, nomSociete, adresse, tel, fax);
            u = uu;
        }
        return u;

    }

    public  List<Fournisseur> readAll() throws SQLException {
        List<Fournisseur> arr = new ArrayList<Fournisseur>();
        String req = "SELECT* from fournisseur ";//+id;
        Connection con = DBInfoFournisseur.getConnection();
        PreparedStatement pre = (PreparedStatement) con.prepareStatement(req);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            Fournisseur u = new Fournisseur();
            u.setId(rs.getInt(1));
            u.setCin(rs.getInt(2));
            u.setEmail(rs.getString(3));
            u.setAdresse(rs.getString(4));
            u.setTelephone(rs.getInt(5));
            u.setFax(rs.getInt(6));
            u.setNomSociete(rs.getString(7));

            arr.add(u);

        }
        return arr;
    }
    

}
