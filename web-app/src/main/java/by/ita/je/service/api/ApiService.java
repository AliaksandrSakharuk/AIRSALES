package by.ita.je.service.api;

import by.ita.je.dto.*;

import java.util.List;

public interface ApiService {

    public ClientDto saveClient(ClientDto clientDto);

    public ClientDto findByIdClient(long id);

    public ClientDto updateClient(ClientDto clientDto);

    public FlightDto createFlight(FlightDto flightDto);

    public List<FlightDto> getListFlight();

    public List<SeatDto> getFreeSeatFlight(long id);

    public void bookTicket(TicketDto ticketDto);

    public void cancelBookTicket(String ticket_id);

    public List<TicketDto> listTicketForClient(long client_id);

    public void saveNewAirCompany(AirCompanyDto companyDto);

    public List<AirCompanyDto> getAllAirCompany();
}
