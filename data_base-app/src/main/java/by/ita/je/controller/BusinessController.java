package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.model.*;
import by.ita.je.service.api.*;
import by.ita.je.util.ObjectMapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(value = "Resources for business tasks this application")
public class BusinessController {

    private final BusinessService businessService;
    private final SearcherService searcherService;
    private final ClientService clientService;
    private final ObjectMapper objectMapper;

    @PostMapping("/client")
    @ApiOperation(value = "Add in the dataBase new Client fot application", response = ClientDto.class)
    public ClientDto createNewClient(@Valid @RequestBody ClientDto clientDto){
        Client clientNew= objectMapper.convertValue(clientDto, Client.class);
        final Client client=clientService.save(clientNew);
        return objectMapper.convertValue(client, ClientDto.class);
    }

    @GetMapping("/client/{id}")
    @ApiOperation(value = "Find the Client in DATA BASE", response = ClientDto.class)
    public ClientDto finedClient(@PathVariable("id") long id) {
        final Client client = clientService.readById(id);
        return ObjectMapperUtil.convertEToD(client, ClientDto.class);
    }

    @PostMapping("/client/{client_id}")
    @ApiOperation(value = "Update information of Client in DATA BASE", response = ClientDto.class)
        public ClientDto updateClient(@Valid @PathVariable("client_id") long id, @RequestBody ClientDto clientDto){
        final Client clientNew=objectMapper.convertValue(clientDto, Client.class);
        final Client client=clientService.update(id,clientNew);
        return objectMapper.convertValue(client, ClientDto.class);
    }

    @PostMapping("/sales/flight")
    @ApiOperation(value = "Add new flight into the schedule", response = FlightDto.class)
    public FlightDto createNewFlight(@Valid @RequestBody FlightDto flightDto){
        final Flight flightNew = objectMapper.convertValue(flightDto, Flight.class);
        final Flight flight = businessService.createNewFlight(flightNew);
        return objectMapper.convertValue(flight, FlightDto.class);
    }


    @GetMapping("/sales/flight/{id}/seat")
    @ApiOperation(value = "Find all free seats in the selected plane by id_plane in the data base")
    public List<SeatDto> findFreeSeatByIdPlane(@PathVariable("id") long id) {
        final List<Seat> seats = searcherService.findFreeSeat(id);
        return ObjectMapperUtil.convertEToD(seats, SeatDto.class);
    }

    @GetMapping("/sales/flight/list")
    @ApiOperation(value = "View all flight after current time")
    public List<FlightDto> findAllFlight() {
        List<Flight> list = searcherService.findFlightAfterCurrentTime();
        return ObjectMapperUtil.convertEToD(list, FlightDto.class);
    }

    @PostMapping("/sales/flight/list/conditions")
    @ApiOperation(value = "Searcher flight by condition with 'FieldSearcherDto' ")
    public List<FlightDto> findFlightByCondition(@RequestBody FieldSearcherDto fieldSearcherDto) {
        List<Flight> list = searcherService.findFlightByConditions(fieldSearcherDto);
        return ObjectMapperUtil.convertEToD(list, FlightDto.class);
    }

    @PostMapping("/company")
    @ApiOperation(value = "Add new AirCompany into the data base")
    public AirCompanyDto createNewAirCompany(@Valid @RequestBody AirCompanyDto companyDto){
        final AirCompany companyNew = objectMapper.convertValue(companyDto, AirCompany.class);
        final AirCompany company=businessService.createNewAirCompany(companyNew);
        return objectMapper.convertValue(company, AirCompanyDto.class);
    }

    @GetMapping("/company/list")
    @ApiOperation(value = "View all AirCompany from data base")
    public List<AirCompanyDto> findAllAirCompany() {
        List<AirCompany> list = businessService.getAllAirCompany();
        return ObjectMapperUtil.convertEToD(list, AirCompanyDto.class);
    }

    @PostMapping("/sales/ticket/book")
    @ApiOperation(value = "Book ticket for the selected flight")
    public TicketDto bookTicket(@Valid @RequestBody TicketDto ticketDto){
        final Ticket ticketNew= objectMapper.convertValue(ticketDto, Ticket.class);
        final Ticket ticket=businessService.bookTicket(ticketNew);
        return objectMapper.convertValue(ticket, TicketDto.class);
    }

    @DeleteMapping("/sales/ticket/book/{id}")
    @ApiOperation(value = "Cancel book ticket")
    public void cancelTicket(@PathVariable("id") long id) {
        businessService.cancelBookedTicket(id);
    }

    @GetMapping("/sales/ticket/list/{client_id}")
    @ApiOperation(value = "View all tickets of selected client by client_id")
    public List<TicketDto> findAllTicketsForClient(@PathVariable("client_id") long id) {
        List<Ticket> list = searcherService.findTicketForClient(id);
        return ObjectMapperUtil.convertEToD(list, TicketDto.class);
    }
}
