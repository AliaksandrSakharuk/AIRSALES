package by.ita.je.excepetion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotCorrectSeat extends RuntimeException {
    public NotCorrectSeat(String numberSeat) {
        super("Место " + numberSeat + " уже забронировано");
        log.error("Not correct data for booked seat");
    }
}

