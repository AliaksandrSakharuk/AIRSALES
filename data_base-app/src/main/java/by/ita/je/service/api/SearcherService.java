package by.ita.je.service.api;

import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public interface SearcherService {

    public List<Flight> findFlightAfterCurrentTime();

    public List<Ticket> findTicketForClient(long client_id);

    List<Seat> findFreeSeat(long flight_id) throws NotFoundData;

    public List<Flight> findFlightByConditions(FieldSearcherDto searcherDto);


    public List<Passenger> findPassengerByPassport(long client_id, String passportNumber);
}
