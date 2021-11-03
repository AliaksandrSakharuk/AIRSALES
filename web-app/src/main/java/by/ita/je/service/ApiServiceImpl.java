package by.ita.je.service;

import by.ita.je.dto.*;
import by.ita.je.excepetion.NotCorrectData;
import by.ita.je.service.api.ApiService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ApiServiceImpl implements ApiService {

    private final RestTemplate restTemplate;
    private final String url = "http://localhost:5001/data_base-app";
//    private final String url = "http://database-app:5001/data_base-app";

    @Override
    public ClientDto saveClient(ClientDto clientDto) {
        String urlBusiness = url + "/client";
        return restTemplate.postForObject(urlBusiness, clientDto, ClientDto.class);
    }

    @Override
    public ClientDto findByIdClient(long id) {
        String urlBusiness = url + "/client/" + id;
        ResponseEntity responseEntity = restTemplate.getForEntity(urlBusiness, ClientDto.class);
        return (ClientDto) responseEntity.getBody();
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        String urlBusiness = url + "/client/" + clientDto.getId();
        return restTemplate.postForObject(urlBusiness, clientDto, ClientDto.class);
    }

    @Override
    public FlightDto createFlight(FlightDto flightDto) {
        String urlBusiness = url + "/sales/flight";
        if (flightDto.getArriveDateTime().isBefore(flightDto.getDepartureDateTime())) throw new NotCorrectData("Flight");
        return restTemplate.postForObject(urlBusiness, flightDto, FlightDto.class);
    }

    @Override
    public List<FlightDto> getListFlight() {
        String urlBusiness = url + "/sales/flight/list";
        ResponseEntity<FlightDto[]> responseEntity = restTemplate.getForEntity(urlBusiness, FlightDto[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<FlightDto> getListFlightByConditions(FieldSearcherDto fieldDto) {
        String urlBusiness = url + "/sales/flight/list/conditions";
        fieldDto.setNameCompany(fieldDto.getNameCompany().trim().toUpperCase());
        fieldDto.setDepartureCity(fieldDto.getDepartureCity().trim().toUpperCase());
        fieldDto.setArriveCity(fieldDto.getArriveCity().toUpperCase());
        ResponseEntity<FlightDto[]> responseEntity = restTemplate.postForEntity(urlBusiness, fieldDto, FlightDto[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<SeatDto> getFreeSeatFlight(long id) {
        String urlBusiness = url + "/sales/flight/" + id + "/seat";
        ResponseEntity<SeatDto[]> responseEntity = restTemplate.getForEntity(urlBusiness, SeatDto[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public void bookTicket(TicketDto ticketDto) {
        String urlBusiness = url + "/sales/ticket/book";
        restTemplate.postForEntity(urlBusiness, ticketDto, TicketDto.class);
    }

    @Override
    public void cancelBookTicket(long ticket_id) {
        String urlBusiness = url + "/sales/ticket/book/" + ticket_id;
        restTemplate.delete(urlBusiness);
    }

    @Override
    public List<TicketDto> listTicketForClient(long client_id) {
        String urlBusiness = url + "/sales/ticket/list/" + client_id;
        ResponseEntity<TicketDto[]> responseEntity = restTemplate.getForEntity(urlBusiness, TicketDto[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public void saveNewAirCompany(AirCompanyDto companyDto) {
        String urlBusiness = url + "/company";
        restTemplate.postForObject(urlBusiness, companyDto, AirCompanyDto.class);
    }

    @Override
    public List<AirCompanyDto> getAllAirCompany() {
        String urlBusiness = url + "/company/list";
        ResponseEntity<AirCompanyDto[]> responseEntity = restTemplate.getForEntity(urlBusiness, AirCompanyDto[].class);
        return Arrays.asList(responseEntity.getBody());
    }
}
