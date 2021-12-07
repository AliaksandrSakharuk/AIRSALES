package by.ita.je.controller;
import by.ita.je.dto.*;
import by.ita.je.model.*;
import by.ita.je.service.api.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Api(value = "Resources for business tasks this application")
public class BusinessController {

    private final ObjectMapper objectMapper;
    private final BusinessService businessService;
    private final SearcherService searcherService;
    private final ClientService clientService;
//    private final KafkaConsumer kafkaConsumer;

    @PostMapping("/client")
    @ApiOperation(value = "Add in the dataBase new Client fot application", response = ClientDto.class)
    public ClientDto createNewClient(@RequestBody ClientDto clientDto){
        Client clientNew= objectMapper.convertValue(clientDto, Client.class);
        final Client client=clientService.save(clientNew);
        return objectMapper.convertValue(client, ClientDto.class);
    }

    @GetMapping("/client/{id}")
    @ApiOperation(value = "Find the Client in DATA BASE", response = ClientDto.class)
    public ClientDto finedClient(@PathVariable("id") long id){
        final Client client=clientService.readById(id);
        return objectMapper.convertValue(client, ClientDto.class);
    }

    @PostMapping("/client/{client_id}")
    @ApiOperation(value = "Update information of Client in DATA BASE", response = ClientDto.class)
    public ClientDto updateClient(@PathVariable("client_id") long id, @RequestBody ClientDto clientDto){
        final Client clientNew=objectMapper.convertValue(clientDto, Client.class);
        final Client client=clientService.update(id,clientNew);
        return objectMapper.convertValue(client, ClientDto.class);
    }

    @PostMapping("/sales/flight")
    @ApiOperation(value = "Add new flight into the schedule", response = FlightDto.class)
    public FlightDto createNewFlight(@RequestBody FlightDto flightDto){
        System.out.println(flightDto);
        final Flight flightNew = objectMapper.convertValue(flightDto, Flight.class);
        final Flight flight=businessService.createNewFlight(flightNew);
        return objectMapper.convertValue(flight, FlightDto.class);
    }


    @GetMapping("/sales/flight/{id}/seat")
    @ApiOperation(value = "Find all free seats in the selected plane by id_plane in the data base")
    public List<SeatDto> findFreeSeatByIdPlane(@PathVariable("id") long id){
        final List<Seat> seats=searcherService.findFreeSeat(id);
        return seats.stream()
                .map(seat -> objectMapper.convertValue(seat, SeatDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/sales/flight/list")
    @ApiOperation(value = "View all flight after current time")
    public  List<FlightDto> findAllFlight(){
        List <Flight> list=searcherService.findFlightAfterCurrentTime();
        List<FlightDto> flights=list.stream()
                .map(flight -> objectMapper.convertValue(flight, FlightDto.class))
                .collect(Collectors.toList());
        return flights;
    }

    @PostMapping("/sales/flight/list/conditions")
    @ApiOperation(value = "Searcher flight by condition with 'FieldSearcherDto' ")
    public List<FlightDto> findFlightByCondition(@RequestBody FieldSearcherDto fieldSearcherDto){
        List <Flight> list=searcherService.findFlightByConditions(fieldSearcherDto);
        List<FlightDto> flights=list.stream()
                .map(flight -> objectMapper.convertValue(flight, FlightDto.class))
                .collect(Collectors.toList());
        System.out.println(flights);
        return flights;
    }

    @PostMapping("/company")
    @ApiOperation(value = "Add new AirCompany into the data base")
    public AirCompanyDto createNewAirCompany(@RequestBody AirCompanyDto companyDto){
        final AirCompany companyNew = objectMapper.convertValue(companyDto, AirCompany.class);
        final AirCompany company=businessService.createNewAirCompany(companyNew);
        return objectMapper.convertValue(company, AirCompanyDto.class);
    }

    @GetMapping("/company/list")
    @ApiOperation(value = "View all AirCompany from data base")
    public  List<AirCompanyDto> findAllAirCompany(){
        List <AirCompany> list=businessService.getAllAirCompany();
        List<AirCompanyDto> listCompany=list.stream()
                .map(company -> objectMapper.convertValue(company, AirCompanyDto.class))
                .collect(Collectors.toList());
        return listCompany;
    }

    @PostMapping("/sales/ticket/book")
    @ApiOperation(value = "Book ticket for the selected flight")
    public TicketDto bookTicket(@RequestBody TicketDto ticketDto){
        final Ticket ticketNew= objectMapper.convertValue(ticketDto, Ticket.class);
        final Ticket ticket=businessService.bookTicket(ticketNew);
        return objectMapper.convertValue(ticket, TicketDto.class);
    }

    @DeleteMapping("/sales/ticket/book/{id}")
    @ApiOperation(value = "Cancel book ticket")
    public void cancelTicket(@PathVariable("id") long id){
        businessService.cancelBookedTicket(id);
    }

    @GetMapping("/sales/ticket/list/{client_id}")
    @ApiOperation(value = "View all tickets of selected client by client_id")
    public  List<TicketDto> findAllTicketsForClient(@PathVariable("client_id") long id){
        List <Ticket> list=searcherService.findTicketForClient(id);
        List<TicketDto> tickets=list.stream()
                .map(ticket -> objectMapper.convertValue(ticket, TicketDto.class))
                .collect(Collectors.toList());
        return tickets;
    }
}
