package by.ita.je.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotCorrectData extends RuntimeException {
    public NotCorrectData(String mesege){
        super(mesege);
        log.error("Entered data is not correct");
    }
}
