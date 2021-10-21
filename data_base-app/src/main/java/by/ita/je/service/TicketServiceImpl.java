package by.ita.je.service;

import by.ita.je.dao.TicketDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Ticket;
import by.ita.je.service.api.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private final TicketDao ticketDao;

    @Override
    public Ticket readById(Long id) throws NotFoundData{
        final Ticket ticket= ticketDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Ticket"));
        return ticket;

    }

    @Override
    public Ticket save(Ticket ticket) {
        if(ticket.getFirstNamePassenger()=="" || ticket.getFirstNamePassenger()==null) throw new NotCorrectData("Ticket");
        if(ticket.getSecondNamePassenger()=="" || ticket.getSecondNamePassenger()==null) throw new NotCorrectData("Ticket");
        if(ticket.getPassportNumberPassenger()=="" || ticket.getPassportNumberPassenger()==null) throw new NotCorrectData("Ticket");
        ticket.setBookedDateTime(LocalDateTime.now());
        return ticketDao.save(ticket);
    }

    @Override
    public Ticket update(Long id, Ticket ticketNew) throws NotFoundData {
        Ticket ticket = ticketDao.findById(id)
                .orElseThrow(() -> new NotFoundData( "Ticket"));
        if(ticketNew.getBookedDateTime()!=null) ticket.setBookedDateTime(ticket.getBookedDateTime());
        return ticketDao.save(ticket);
    }

    @Override
    public void deleteById(Long id) throws NotFoundData {
        try {
            ticketDao.deleteById(id);
        }catch (Exception e){
            throw new NotFoundData("Ticket");
        }
    }
}
