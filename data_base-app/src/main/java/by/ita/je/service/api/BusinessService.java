package by.ita.je.service.api;

import by.ita.je.exception.NotCorrectSeat;
import by.ita.je.model.AirCompany;
import by.ita.je.model.Flight;
import by.ita.je.model.Ticket;

import java.util.List;

public interface BusinessService {

    public Flight createNewFlight(Flight flight);

    public AirCompany createNewAirCompany(AirCompany company);

    public Ticket bookTicket(Ticket Ticket) throws NotCorrectSeat;

    public void cancelBookedTicket(long id);

    public List<AirCompany> getAllAirCompany();

}
