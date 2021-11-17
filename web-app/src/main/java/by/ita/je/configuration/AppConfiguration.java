package by.ita.je.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import java.util.Properties;

@Configuration
public class AppConfiguration {

    @Primary
    public Properties objectPropertiesEmail(){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "25");
        return prop;
    }

    @Bean
    public RestTemplate objectRestTemplate(){
        return new RestTemplate();
    }
}
