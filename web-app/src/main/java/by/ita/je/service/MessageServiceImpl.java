package by.ita.je.service;

import by.ita.je.configuration.MailAppProperties;
import by.ita.je.service.api.MessageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MailAppProperties mailAppProperties;

    @Override
    public void sendMessage(String password, String mail) {
        Properties prop = new Properties();
        prop.putAll(mailAppProperties.getProps());
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailAppProperties.getEmail(), mailAppProperties.getPassword());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailAppProperties.getEmail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setSubject("Reconstruction Password");
            message.setText("Your new password: " + password + " Please login and change it!");
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


