package by.ita.je.exception;

import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

@Slf4j
public class NotFoundFreePlane extends RuntimeException {
    public NotFoundFreePlane(ZonedDateTime dateTime) {
        super("Свободного самолета на время : " + dateTime + "нет!");
        log.error("Not found data");
    }
}

