package by.ita.je;

import by.ita.je.service.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class DataBase {
    public static void main(String [] args) {
        SpringApplication.run(DataBase.class, args);
//
//        KafkaConsumer kafkaConsumer=new KafkaConsumer();
//        kafkaConsumer.msgListener("dsfddsrertytryryr");
    }
}
