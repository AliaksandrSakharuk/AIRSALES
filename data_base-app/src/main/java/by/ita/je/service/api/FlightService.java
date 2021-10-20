package by.ita.je.service.api;

import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Flight;

import java.util.List;

public interface FlightService {

    Flight save(Flight flight) throws NotCorrectData;

//    Flight update(Long id, Flight flightNew);

    Flight readById(Long id) throws NotFoundData;

//    List<Flight> findAll();
//
//    void deleteById(Long id) throws NotFoundData;
}
