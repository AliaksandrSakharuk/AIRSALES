package by.ita.je.controller;
import by.ita.je.dto.*;
import by.ita.je.model.*;
import by.ita.je.service.api.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class BusinessController {

    private final ObjectMapper objectMapper;
    private final BusinessService businessService;
    private final SearcherService searcherService;
    private final ClientService clientService;

    @PostMapping("/client")
    public ClientDto createNewClient(@RequestBody ClientDto clientDto){
        Client clientNew= objectMapper.convertValue(clientDto, Client.class);
        final Client client=clientService.save(clientNew);
        return objectMapper.convertValue(client, ClientDto.class);
    }

    @GetMapping("/client/{id}")
    public ClientDto finedClient(@PathVariable("id") String id){
        final Client client=clientService.readById(Long.valueOf(id));
        return objectMapper.convertValue(client, ClientDto.class);
    }

    @PostMapping("/client/{client_id}")
    public ClientDto updateClient(@PathVariable("client_id") String id, @RequestBody ClientDto clientDto){
        final Client clientNew=objectMapper.convertValue(clientDto, Client.class);
        final Client client=clientService.update(Long.valueOf(id),clientNew);
        return objectMapper.convertValue(client, ClientDto.class);
    }

    @PostMapping("/sales/flight")
    public FlightDto createNewFlight(@RequestBody FlightDto flightDto){
        System.out.println(flightDto);
        final Flight flightNew = objectMapper.convertValue(flightDto, Flight.class);
        final Flight flight=businessService.createNewFlight(flightNew);
        return objectMapper.convertValue(flight, FlightDto.class);
    }


    @GetMapping("/sales/flight/{id}/seat")
    public List<SeatDto> findFreeSeatByIdPlane(@PathVariable("id") long id){
        final List<Seat> seats=searcherService.findFreeSeat(id);
        return seats.stream()
                .map(seat -> objectMapper.convertValue(seat, SeatDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/sales/flight/list")
    public  List<FlightDto> findAllFlight(){
        List <Flight> list=searcherService.findFlightAfterCurrentTime();
        List<FlightDto> flights=list.stream()
                .map(flight -> objectMapper.convertValue(flight, FlightDto.class))
                .collect(Collectors.toList());
        return flights;
    }

    @PostMapping("/sales/flight/list/conditions")
    public List<FlightDto> findFlightByCondition(@RequestBody FieldSearcherDto fieldSearcherDto){
        System.out.println(fieldSearcherDto);
        List <Flight> list=searcherService.findFlightByConditions(fieldSearcherDto);
        List<FlightDto> flights=list.stream()
                .map(flight -> objectMapper.convertValue(flight, FlightDto.class))
                .collect(Collectors.toList());
        System.out.println(flights);
        return flights;
    }

    @PostMapping("/company")
    public AirCompanyDto createNewAirCompany(@RequestBody AirCompanyDto companyDto){
        final AirCompany companyNew = objectMapper.convertValue(companyDto, AirCompany.class);
        final AirCompany company=businessService.createNewAirCompany(companyNew);
        return objectMapper.convertValue(company, AirCompanyDto.class);
    }

    @GetMapping("/company/list")
    public  List<AirCompanyDto> findAllAirCompany(){
        List <AirCompany> list=businessService.getAllAirCompany();
        List<AirCompanyDto> listCompany=list.stream()
                .map(company -> objectMapper.convertValue(company, AirCompanyDto.class))
                .collect(Collectors.toList());
        return listCompany;
    }

    @PostMapping("/sales/ticket/book")
    public TicketDto bookTicket(@RequestBody TicketDto ticketDto){
        final Ticket ticketNew= objectMapper.convertValue(ticketDto, Ticket.class);
        final Ticket ticket=businessService.bookTicket(ticketNew);
        return objectMapper.convertValue(ticket, TicketDto.class);
    }

    @DeleteMapping("/sales/ticket/book/{id}")
    public void cancelTicket(@PathVariable("id") String id){
        businessService.cancelBookedTicket(Long.valueOf(id));
    }

    @GetMapping("/sales/ticket/list/{client_id}")
    public  List<TicketDto> findAllTicketsForClient(@PathVariable("client_id") long id){
        List <Ticket> list=searcherService.findTicketForClient(id);
        List<TicketDto> tickets=list.stream()
                .map(ticket -> objectMapper.convertValue(ticket, TicketDto.class))
                .collect(Collectors.toList());
        return tickets;
    }
}
