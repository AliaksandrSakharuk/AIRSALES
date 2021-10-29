package by.ita.je.service;

import by.ita.je.dao.*;
import by.ita.je.dao.impl.SearcherFlightByConditionDaoImpl;
import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Flight;
import by.ita.je.model.Passenger;
import by.ita.je.model.Seat;
import by.ita.je.model.Ticket;
import by.ita.je.service.api.SearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearcherServiceImpl implements SearcherService {

    private final FlightSearcherAfterCurrentTimeDao flightAfterCurrentTimeDao;
    private final SearcherFreeSeatOnFlightDao seatOnFlightDao;
    private final SearcherTicketsForClientDao ticketForClient;
    private final SearcherFlightByConditionDaoImpl searcherFlightByConditionDao;
    private final SearcherPassengerByNumberPassport searcherPassengerByNumberPassport;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor=Exception.class)
    public List<Ticket> findTicketForClient(long client_id){
        return ticketForClient.findTicketForClient(client_id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor=Exception.class)
    public List<Flight> findFlightAfterCurrentTime() {
        return flightAfterCurrentTimeDao.findAllAfterCurrentTime();
    }


    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor=Exception.class)
    public List<Seat> findFreeSeat(long flight_id) throws NotFoundData {
        List<Seat> seats=seatOnFlightDao.findFreeSeatOnFlight(flight_id);
        if(seats.isEmpty()) throw new NotFoundData("Flight");
        return seats;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor=Exception.class)
    public List<Flight> findFlightByConditions(FieldSearcherDto searcherDto){
        return searcherFlightByConditionDao.findFlight(searcherDto);
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public List<Passenger> findPassengerByPassport(long client_id, String passportNumber){
        return searcherPassengerByNumberPassport.findPassengerByPassport(client_id, passportNumber);
    }
}
