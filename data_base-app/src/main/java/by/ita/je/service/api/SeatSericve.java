package by.ita.je.service.api;

import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Seat;


public interface SeatSericve {

    Seat save(Seat seat) throws NotCorrectData;

    Seat update(Long id, Seat seatNew) throws NotFoundData;

    Seat readById(Long id) throws NotFoundData;

    void deleteById(Long id) throws NotFoundData;
}
