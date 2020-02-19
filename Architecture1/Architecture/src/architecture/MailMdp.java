/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package architecture;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 *
 * @author Asus
 */
public class MailMdp {
    
    private static final String HOST = "smtp.mail.com";
    private static final String FROM = "hanenennine7@gmail.com";
    private static final String LOGIN = "hanenennine7@gmail.com";
    private static final String PASS = "gmailgmailgmail1";

    public static void sendMail(String recipient, String title, String content) {
        Thread mailThread = new Thread(() -> {
            Transport transport = null;
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", HOST);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", "587");
            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(LOGIN, PASS);
                        }
                    });
            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(FROM));

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

                message.setSubject(title);

                message.setText(content);

                System.out.println("Tentative de connexion");

                transport = session.getTransport("smtp");
                transport.connect(HOST, LOGIN, PASS);

                System.out.println("Envoi de mail...");

                transport.sendMessage(message, message.getAllRecipients());

                System.out.println("Mail enovyé avecc succés.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mailThread.start();
    }
}
