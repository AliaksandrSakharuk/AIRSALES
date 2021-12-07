package by.ita.je;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class DataBase {
    public static void main(String [] args) {
        SpringApplication.run(DataBase.class, args);
    }
}
