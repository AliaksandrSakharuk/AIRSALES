package by.ita.je;

import by.ita.je.service.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApp {
    public static void main(String [] args) {
        SpringApplication.run(WebApp.class, args);
        KafkaConsumer kafkaConsumer=new KafkaConsumer();
        kafkaConsumer.msgListener("dsfddsrertytryryr");
    }
}
