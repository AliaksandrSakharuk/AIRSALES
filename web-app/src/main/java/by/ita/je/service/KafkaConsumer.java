package by.ita.je.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics="msg", groupId = "foo")
    public void msgListener(String msg){
        System.out.println(msg);
    }
}
