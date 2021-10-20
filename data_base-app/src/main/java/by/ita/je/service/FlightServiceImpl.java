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
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDao flightDao;

    @Override
    public Flight save(Flight flight) throws NotCorrectData {
        if(flight.getNumberFlight()=="") throw new NotCorrectData("Flight");
        if(flight.getDepartureCity()=="") throw new NotCorrectData("Flight");
        if(flight.getArriveCity()=="") throw new NotCorrectData("Flight");
        if(flight.getDepartureDateTime()==null) throw new NotCorrectData("Flight");
        if(flight.getArriveDateTime()==null) throw new NotCorrectData("Flight");
        flight.setDurationFlight((int) ChronoUnit.MINUTES.between(flight.getDepartureDateTime(), flight.getArriveDateTime()));
        return flightDao.save(flight);
    }

    @Override
    public Flight update(Long id, Flight flightNew) {
        Flight flight = flightDao.findById(id)
                .orElseThrow(() -> new NotFoundData( "AirCompany"));
        if(flightNew.getNumberFlight()!="") flight.setNumberFlight(flightNew.getNumberFlight());
        if(flightNew.getDepartureCity()!="") flight.setDepartureCity(flightNew.getDepartureCity());
        if(flightNew.getArriveCity()!="") flight.setArriveCity(flightNew.getArriveCity());
        if(flightNew.getDepartureDateTime()!=null) flight.setDepartureDateTime(flightNew.getDepartureDateTime());
        if(flightNew.getArriveDateTime()!=null) flight.setArriveDateTime(flightNew.getArriveDateTime());
        return flightDao.save(flight);
    }

    @Override
    public Flight readById(Long id) throws NotFoundData{
        final Flight flight=flightDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Flight"));
        return flight;
    }

    @Override
    public List<Flight> findAll() {
        final Spliterator<Flight> result = flightDao.findAll().spliterator();
        return StreamSupport
                .stream(result, false)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) throws NotFoundData{
        try {
            flightDao.deleteById(id);
        }catch (Exception e){
            throw new NotFoundData("Flight");
        }
    }

}
