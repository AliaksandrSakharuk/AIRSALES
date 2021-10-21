package service;

import by.ita.je.dao.SeatDao;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Seat;
import by.ita.je.service.SeatServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestSeatServiceImpl {
    @Mock
    private SeatDao seatDao;
    @InjectMocks
    private SeatServiceImpl seatService;

    @Test
    public void whenUpdate_returnSeat(){
        Seat seat=new Seat();
        Mockito.when(seatDao.findById(1L)).thenReturn(Optional.ofNullable(new Seat()));
        Mockito.when(seatDao.save(seat)).thenReturn(seat);
        final  Seat actual=seatService.update(1L, seat);
        final Seat expected=new Seat();
        Assertions.assertEquals(expected, actual);
        Mockito.verify(seatDao, Mockito.times(1)).findById(1L);
        Mockito.verify(seatDao, Mockito.times(1)).save(seat);
    }

    @Test
    public void whenUpdate_returnSeat_OtherBookStatus(){
        Seat seat=new Seat();
        seat.setBooked(true);
        Mockito.when(seatDao.findById(1L)).thenReturn(Optional.ofNullable(new Seat()));
        Mockito.when(seatDao.save(seat)).thenReturn(seat);
        final  Seat actual=seatService.update(1L, seat);
        final Seat expected=new Seat();
        expected.setBooked(true);
        Assertions.assertEquals(expected, actual);
        Mockito.verify(seatDao, Mockito.times(1)).findById(1L);
        Mockito.verify(seatDao, Mockito.times(1)).save(seat);
    }

    @Test
    public  void whenUpdate_throwNotFoundData(){
        Long id=2L;
        Mockito.when(seatDao.findById(id)).thenReturn(Optional.empty());
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> seatService.readById(id));
        Assertions.assertEquals(notFoundData.getMessage(), "Такой записи для Seat в базе данных не существует");
        Mockito.verify(seatDao, Mockito.times(1)).findById(id);
    }

    @Test
    public void whenReadById_returnSeat(){
        Mockito.when(seatDao.findById(1L)).thenReturn(Optional.ofNullable(new Seat()));
        final Seat actual=seatService.readById(1L);
        final Seat expected=new Seat();
        Assertions.assertEquals(expected,actual);
        Mockito.verify(seatDao, Mockito.times(1)).findById(1L);
    }

    @Test
    public void whenReadById_throwsNotFoundData(){
        Long id=2L;
        Mockito.when(seatDao.findById(id)).thenReturn(Optional.empty());
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> seatService.readById(id));
        Assertions.assertEquals(notFoundData.getMessage(), "Такой записи для Seat в базе данных не существует");
        Mockito.verify(seatDao, Mockito.times(1)).findById(id);
    }
}
