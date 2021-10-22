package by.ita.je.excepetion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotCorrectData extends RuntimeException {
    public NotCorrectData(String mesege){
        super("Введены некорректные данные для " + mesege);
        log.error("Entered data is not correct");
    }
}
