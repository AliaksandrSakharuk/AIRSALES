package by.ita.je.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducerService {

    private static final Logger logger =
            LoggerFactory.getLogger(KafkaProducerService.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info(String.format("Message sent -> %s", message));
        this.kafkaTemplate.send("msg", message);
    }
}
