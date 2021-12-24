package by.ita.je.service;

import by.ita.je.dao.TicketDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class TestTicketServiceImpl {
    @Mock
    private TicketDao ticketDao;
    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    public void whenReadById_returnTicket(){
        Mockito.when(ticketDao.findById(1L)).thenReturn(Optional.ofNullable(new Ticket()));
        final Ticket actual=ticketService.readById(1L);
        final Ticket expected=new Ticket();
        Assertions.assertEquals(expected,actual);
        Mockito.verify(ticketDao, Mockito.times(1)).findById(1L);
    }

    @Test
    public void whenReadById_throwsNotFoundData(){
        Long id=2L;
        Mockito.when(ticketDao.findById(id)).thenReturn(Optional.empty());
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> ticketService.readById(id));
        Assertions.assertEquals(notFoundData.getMessage(), "Такой записи для Ticket в базе данных не существует");
        Mockito.verify(ticketDao, Mockito.times(1)).findById(id);
    }

    @Test
    public void whenSave_returnTicket() {
        Ticket ticket=new Ticket();
        ticket.setFirstNamePassenger("vova");
        ticket.setSecondNamePassenger("stromec");
        ticket.setPassportNumberPassenger("AB0001100");
        Mockito.when(ticketDao.save(ticket)).thenReturn(ticket);
        final Ticket actual=ticketService.save(ticket);
        Ticket expected=new Ticket();
        expected.setFirstNamePassenger("vova");
        expected.setSecondNamePassenger("stromec");
        expected.setPassportNumberPassenger("AB0001100");
        expected.setBookedDateTime(actual.getBookedDateTime());
        Assertions.assertEquals(expected ,actual);
        Mockito.verify(ticketDao, Mockito.times(1)).save(ticket);
    }

    @Test
    public void whenUpdate_returnTicket(){
        Ticket ticket=new Ticket();
        Mockito.when(ticketDao.findById(1L)).thenReturn(Optional.ofNullable(new Ticket()));
        Mockito.when(ticketDao.save(ticket)).thenReturn(ticket);
        Ticket actual=ticketService.update(1L, ticket);
        Ticket expected=new Ticket();
        Assertions.assertEquals(expected, actual);
        Mockito.verify(ticketDao, Mockito.times(1)).findById(1L);
        Mockito.verify(ticketDao, Mockito.times(1)).save(ticket);
    }

    @Test
    public  void whenUpdate_throwNotFoundData(){
        Long id=2L;
        Mockito.when(ticketDao.findById(id)).thenReturn(Optional.empty());
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> ticketService.readById(id));
        Assertions.assertEquals(notFoundData.getMessage(), "Такой записи для Ticket в базе данных не существует");
        Mockito.verify(ticketDao, Mockito.times(1)).findById(id);
    }

    @Test
    void deleteById() {
        ticketService.deleteById(1L);
        Mockito.verify(ticketDao, Mockito.times(1)).deleteById(1L);

    }
    @Test
    void deleteById_throwException() {
        Long id=2L;
        doThrow(new NotFoundData("Такой записи для Ticket в базе данных не существует")).when(ticketDao).deleteById(id);
        NotFoundData notFoundData = Assertions.assertThrows(NotFoundData.class,()->ticketService.deleteById(id));
        Assertions.assertEquals(notFoundData.getMessage(),"Такой записи для Ticket в базе данных не существует");
    }

}
