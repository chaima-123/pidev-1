/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


public class Fournisseur{
    
    private String nomSociete;
    protected int id;
    protected int cin;
    protected String email;
    protected int fax;
    protected String adresse;
    protected int telephone;
    
    public Fournisseur() {
        super();
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "nomSociete=" + nomSociete + ", id=" + id + ", cin=" + cin + ", email=" + email + ", fax=" + fax + ", adresse=" + adresse + ", telephone=" + telephone + '}';
    }

    public Fournisseur(int id, int cin, String email, String nomSociete, String adresse, int telephone, int fax) {
        this.nomSociete = nomSociete;
        this.id = id;
        this.cin = cin;
        this.email = email;
        this.fax = fax;
        this.adresse = adresse;
        this.telephone = telephone;
    }
    
    public Fournisseur(int cin, String email, String nomSociete, String adresse, int telephone, int fax) {
        this.nomSociete = nomSociete;
        this.cin = cin;
        this.email = email;
        this.fax = fax;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
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

    public int getFax() {
        return fax;
    }

    public void setFax(int fax) {
        this.fax = fax;
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
    
    
}
