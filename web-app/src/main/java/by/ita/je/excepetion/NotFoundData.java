package by.ita.je.excepetion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundData  extends RuntimeException {

    public NotFoundData(String name) {

        super("Такой записи для " + name + " в базе данных не существует");
        log.error("Not found data");
    }
}
