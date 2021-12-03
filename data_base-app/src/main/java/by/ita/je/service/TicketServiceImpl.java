package by.ita.je.service;

import by.ita.je.dao.TicketDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Ticket;
import by.ita.je.service.api.TicketService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;

    @Override
    public Ticket readById(Long id) throws NotFoundData{
        final Ticket ticket= ticketDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Ticket"));
        return ticket;
    }

    @Override
    public Ticket save(Ticket ticket) {
        if(StringUtils.isEmpty(ticket.getFirstNamePassenger())) throw new NotCorrectData("Ticket");
        if(StringUtils.isEmpty(ticket.getSecondNamePassenger())) throw new NotCorrectData("Ticket");
        if(StringUtils.isEmpty(ticket.getPassportNumberPassenger())) throw new NotCorrectData("Ticket");
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
