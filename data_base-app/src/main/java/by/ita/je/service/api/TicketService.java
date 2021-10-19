package by.ita.je.service.api;

import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Ticket;
public interface TicketService {

    Ticket readById(Long id) throws NotFoundData;

    Ticket save(Ticket ticket);

    Ticket update(Long id, Ticket ticketNew) throws NotFoundData;

    void deleteById(Long id) throws NotFoundData;

}
