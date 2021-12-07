package by.ita.je.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Component
public class KafkaConsumer {

    @KafkaListener(topics="msg", groupId = "foo", containerFactory = "kafkaListenerContainerFactory")
    public void msgListener(String msg){
        System.out.println(msg);
    }
}
