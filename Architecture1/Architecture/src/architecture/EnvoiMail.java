/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture;

import java.util.Properties;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import entities.Fournisseur;

public class EnvoiMail {

    private String username = "hanenennine7@gmail.com";
    private String password = "gmailgmailgmail1";

    public  void envoyer(String maill,String msg) {

// Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

// Etape 2 : Création de l'objet Message
Fournisseur ff = new Fournisseur ();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hanenennine7@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(maill));
            message.setSubject("Test email");
           // String html = "<h1> SMART TRACK APPLICATION </h1> <br/> <h2><b>Welcome to our application</b></h2>";
            message.setContent(msg, "text/html");
            //message.setText("Bonjour, ce message est un test ...");

// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye");
        } catch (MessagingException e) {
            System.err.println(e.getMessage());
        }
    }
}
