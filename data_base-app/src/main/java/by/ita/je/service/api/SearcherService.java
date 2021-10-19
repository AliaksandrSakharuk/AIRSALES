package by.ita.je.service.api;

import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.model.Flight;
import by.ita.je.model.Plane;
import by.ita.je.model.Seat;
import by.ita.je.model.Ticket;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public interface SearcherService {

//    Flight findFlightBySeat(long id);
//
//    Seat findSeatForCancelBookedTicket(String numberFlight, String numberSeat);

    public List<Flight> findFlightAfterCurrentTime();

    public List<Ticket> findTicketForClient(long id);

    List<Seat> findFreeSeat(long id);
//
//    List<Flight> findFlightByAirCompany(String nameCompany);
//
//    List<Flight> findFlightByDuration(int duration);
//
//    Set<Flight> findFlightWithPlaneChange();
//
//    List<Flight> findFlightByCondition(FieldSearcherDto fieldSearcherDto);

}
