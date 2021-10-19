package by.ita.je.service;

import by.ita.je.service.api.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    @Autowired
    @Qualifier("sendEmail")
    private final Properties prop;

    @Override
    public void sendMessage(String password, String mail) {
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("airsales777@gmail.com","77sales77");
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("airsales777@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject("Reconstruction Password");
            message.setText("Your new password: " + password + " Please login and change it!");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


