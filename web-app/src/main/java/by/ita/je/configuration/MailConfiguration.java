package by.ita.je.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "mail")
    public MailAppProperties getMail(){
        return new MailAppProperties();
    }
}
