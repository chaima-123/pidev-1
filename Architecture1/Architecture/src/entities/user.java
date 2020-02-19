/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Asus
 */
public class user {
    
    protected int id;
    protected int cin;
    protected String email;
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected int telephone;
    protected String gender;
    protected String role;
    private String username;
    private String password;
    

    public user() {
        super();
    }

    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    

    public user(int cin, String email, String nom, String prenom, String adresse, int telephone, String gender, String role, String username, String password) {
        this.cin = cin;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.gender = gender;
        this.role=role;
        this.username = username;
        this.password = password;
    }

    public user(int id, int cin, String email, String nom, String prenom, String adresse, int telephone, String gender, String role, String username, String password) {
        
        this.id=id;
        this.cin = cin;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.gender = gender;
        this.role=role;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", cin=" + cin + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", telephone=" + telephone + ", gender=" + gender + ", role=" + role + ", username=" + username + ", password=" + password + '}';
    }
    
}
