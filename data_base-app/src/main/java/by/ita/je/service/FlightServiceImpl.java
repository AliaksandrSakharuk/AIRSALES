package by.ita.je.service;

import by.ita.je.dao.FlightDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Flight;
import by.ita.je.service.api.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDao flightDao;

    @Override
    public Flight save(Flight flight) throws NotCorrectData {
        if(flight.getNumberFlight()==null || flight.getNumberFlight()=="") throw new NotCorrectData("Flight");
        if(flight.getDepartureCity()==null || flight.getDepartureCity()=="") throw new NotCorrectData("Flight");
        if(flight.getArriveCity()==null || flight.getArriveCity()=="") throw new NotCorrectData("Flight");
        if(flight.getDepartureDateTime()==null) throw new NotCorrectData("Flight");
        if(flight.getArriveDateTime()==null) throw new NotCorrectData("Flight");
        flight.setDurationFlight((int) ChronoUnit.MINUTES.between(flight.getDepartureDateTime(), flight.getArriveDateTime()));
        return flightDao.save(flight);
    }

    @Override
    public Flight readById(Long id) throws NotFoundData{
        final Flight flight=flightDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Flight"));
        return flight;
    }

}
