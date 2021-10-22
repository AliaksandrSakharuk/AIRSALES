package by.ita.je.service;

import by.ita.je.dao.*;
import by.ita.je.dao.impl.SearcherFlightByConditionDaoImpl;
import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Flight;
import by.ita.je.model.Seat;
import by.ita.je.model.Ticket;
import by.ita.je.service.api.SearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearcherServiceImpl implements SearcherService {

    private final FlightSearcherAfterCurrentTimeDao flightAfterCurrentTimeDao;
    private final SearcherFreeSeatOnFlightDao seatOnFlightDao;
    private final SearcherTicketsForClientDao ticketForClient;
    private final SearcherFlightByConditionDaoImpl searcherFlightByConditionDao;

    @Override
    public List<Ticket> findTicketForClient(long client_id){
        return ticketForClient.findTicketForClient(client_id);
    }

    @Override
    public List<Flight> findFlightAfterCurrentTime() {
        return flightAfterCurrentTimeDao.findAllAfterCurrentTime();
    }


    @Override
    public List<Seat> findFreeSeat(long flight_id) throws NotFoundData {
        List<Seat> seats=seatOnFlightDao.findFreeSeatOnFlight(flight_id);
        if(seats.isEmpty()) throw new NotFoundData("Flight");
        return seats;
    }

    @Override
    public List<Flight> findFlightByConditions(FieldSearcherDto searcherDto){
        return searcherFlightByConditionDao.findFlight(searcherDto);
    }
}
