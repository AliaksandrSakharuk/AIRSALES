package by.ita.je.service;

import by.ita.je.dao.*;
import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.model.Flight;
import by.ita.je.model.Plane;
import by.ita.je.model.Seat;
import by.ita.je.model.Ticket;
import by.ita.je.service.api.SearcherService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class SearcherServiceImpl implements SearcherService {

    @Autowired
    private final SearcherFlightAfterCurrentTimeDao flightAfterCurrentTimeDao;
    private final SearcherFreeSeatOnFlightDao seatOnFlightDao;
    private final SearcherTicketForClient ticketForClient;

    @Override
    public List<Ticket> findTicketForClient(long id){
        return ticketForClient.findTicketForClient(id);
    }

    @Override
    public List<Flight> findFlightAfterCurrentTime() {
        return flightAfterCurrentTimeDao.findAllAfterCurrentTime();
    }


    @Override
    public List<Seat> findFreeSeat(long id) {
        return seatOnFlightDao.findFreeSeatOnFlight(id);
    }
//    private final SearcherFlightByAirCompanyDao searcherFlightByAirCompanyDao;
//    private final SearcherFlightByDurationDao searcherFlightByDurationDao;
//    private final SearcherFlightWithPlaneChangeDao searcherFlightWithPlaneChangeDao;
//    private final SearcherSeatForCancelBookedTicket searcherSeatForCancelBookedTicket;
//
//    @Override
//    public Flight findFlightBySeat(long id) {
//        return searcherFlightBySeat.findFlightBySeat(id);
//    }

//    @Override
//    public Seat findSeatForCancelBookedTicket(String numberFlight, String numberSeat) {
//        return searcherSeatForCancelBookedTicket.findSeatForCancelBookedTicket(numberFlight, numberSeat);
//    }






//

//
//    @Override
//    public List<Flight> findFlightByAirCompany(String nameCompany) {
//        return searcherFlightByAirCompanyDao.findFlightByAirCompany(nameCompany);
//    }
//
//    @Override
//    public List<Flight> findFlightByDuration(int duration) {
//
//        return searcherFlightByDurationDao.findFlightByDuration(duration);
//    }
//
//    @Override
//    public Set<Flight> findFlightWithPlaneChange() {
//
//        return searcherFlightWithPlaneChangeDao.findFlightByChangePlane("BREST", "MOSCOW");
//    }

}
