package by.ita.je.service.api;

import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Flight;
import by.ita.je.model.Plane;
import by.ita.je.model.Seat;
import by.ita.je.model.Ticket;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public interface SearcherService {

    public List<Flight> findFlightAfterCurrentTime();

    public List<Ticket> findTicketForClient(long client_id);

    List<Seat> findFreeSeat(long flight_id) throws NotFoundData;

    public List<Flight> findFlightByConditions(FieldSearcherDto searcherDto);
}
