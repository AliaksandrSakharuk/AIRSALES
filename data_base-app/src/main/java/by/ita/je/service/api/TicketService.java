package by.ita.je.service.api;

import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Ticket;

public interface TicketService {

   public Ticket readById(Long id) throws NotFoundData;

    public Ticket save(Ticket ticket);

    public Ticket update(Long id, Ticket ticketNew) throws NotFoundData;

    public void deleteById(Long id) throws NotFoundData;
}
