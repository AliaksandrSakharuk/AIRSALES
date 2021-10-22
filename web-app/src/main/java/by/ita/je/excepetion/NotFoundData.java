package by.ita.je.excepetion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundData  extends RuntimeException {
    public NotFoundData(String name) {
        super("Такой записи для " + name + " в базе данных не существует");
        log.error("Not found data");
    }
}
