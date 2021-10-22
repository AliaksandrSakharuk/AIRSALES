package by.ita.je.service.api;

import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Flight;

public interface FlightService {

    Flight save(Flight flight) throws NotCorrectData;

    Flight readById(Long id) throws NotFoundData;

}
