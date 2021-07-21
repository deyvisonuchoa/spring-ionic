package br.com.project.resources;


import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailResource {
    
    @GetMapping
    public ResponseEntity<String> enviaEmail(){
        
        try {
            Session session = null;

            Properties props = new Properties();
             props.put("mail.smtp.user", "mestradata@gmail.com");
             props.put("mail.smtp.password", "mestradata@123");
            
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
             
            if ("true".equals(props.getProperty("mail.smtp.auth"))) {
                session = Session.getInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
                     }});
            } else {
                session = Session.getInstance(props, null);
            }

            MimeMessage message = new MimeMessage(session);
            session.setDebug(true);    
            
            message.setFrom(new InternetAddress("mestradata@gmail.com"));

            Address[] toUser = InternetAddress
                    .parse("uchoadeyvison@gmail.com, deyvisonuchoa@hotmail.com");

             message.setRecipients(Message.RecipientType.TO, toUser);
             message.setSubject("Enviando email com JavaMail");//Assunto
             message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");

            Transport.send(message);

        } catch (MessagingException e) {
        }
        
        return ResponseEntity.ok().body("teste");
    }

}
