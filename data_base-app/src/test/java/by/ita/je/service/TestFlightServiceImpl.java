package by.ita.je.service;

import by.ita.je.dao.FlightDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Flight;
import by.ita.je.service.FlightServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestFlightServiceImpl {
    @Mock
    private FlightDao flightDao;
    @InjectMocks
    private FlightServiceImpl flightService;

    @Test
    public void whenSave_returnFlight() {
        Flight flight=new Flight();
        flight.setNumberFlight("FA777");
        flight.setDepartureCity("BREST");
        flight.setDepartureDateTime(LocalDateTime.now());
        flight.setArriveCity("MINSK");
        flight.setArriveDateTime(flight.getDepartureDateTime().plusMinutes(45));
        Mockito.when(flightDao.save(flight)).thenReturn(flight);
        final Flight actual=flightDao.save(flight);
        Flight expected=getFlight();
        expected.setNumberFlight("FA777");
        expected.setDepartureCity("BREST");
        expected.setArriveCity("MINSK");
        expected.setDepartureDateTime(flight.getDepartureDateTime());
        expected.setArriveDateTime(flight.getArriveDateTime());
        Assertions.assertEquals(expected ,actual);
        Mockito.verify(flightDao, Mockito.times(1)).save(flight);
    }

    @Test
    public void whenFindById_returnFlight(){
        Mockito.when(flightDao.findById(1L)).thenReturn(Optional.ofNullable(new Flight()));
        final Flight actual=flightService.readById(1L);
        final Flight expected=getFlight();
        Assertions.assertEquals(expected,actual);
        Mockito.verify(flightDao, Mockito.times(1)).findById(1L);
    }

    @Test
    public void whenFindById_throwsNotFoundData(){
        Long id=2L;
        Mockito.when(flightDao.findById(id)).thenReturn(Optional.empty());
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> flightService.readById(id));
        Assertions.assertEquals(notFoundData.getMessage(), "Такой записи для Flight в базе данных не существует");
        Mockito.verify(flightDao, Mockito.times(1)).findById(id);
    }

    private Flight getFlight(){ return new Flight();}
}
