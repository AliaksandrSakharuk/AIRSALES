package by.ita.je.exception;

import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

@Slf4j
public class NotCorrectSeat extends RuntimeException {
    public NotCorrectSeat(String numberSeat) {
        super("Место " + numberSeat + " уже забронировано");
        log.error("Not correct data for booked seat");
    }
}

