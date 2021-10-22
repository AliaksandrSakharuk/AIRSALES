package by.ita.je.controller;


import by.ita.je.dto.*;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.*;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.SearcherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BusinessControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SearcherService searcherService;

    @Autowired
    private BusinessService businessService;

    @Test
    void createNewClient_thenOk() throws Exception{
        Client client=new Client();
        client.setFirstName("Petia");
        client.setSecondName("Bobr");
        mockMvc.perform(post("/client")
                        .content(objectMapper.writeValueAsString(client))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Petia"));
    }

    @Test
    void createNewClient_thenThrowException() throws Exception{
        Client client=new Client();
        mockMvc.perform(post("/client")
                        .content(objectMapper.writeValueAsString(client))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotCorrectData))
                .andExpect(result -> assertEquals("Введены некорректные данные для Client"
                        , result.getResolvedException().getMessage()));
    }

    @Test
    void finedClient_thenOk() throws Exception{
        long id=4l;
        mockMvc.perform(get("/client/{id}", id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("roma"));
    }

    @Test
    void finedClient_ThrowException() throws Exception{
        long id=40l;
        mockMvc.perform(get("/client/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundData))
                .andExpect(result -> assertEquals("Такой записи для Client в базе данных не существует"
                        , result.getResolvedException().getMessage()));
    }


    @Test
    void updateClient_thenOk()  throws Exception{
        long id=5l;
        Client client=new Client();
        client.setFirstName("GLEB");
        client.setSecondName("OREL");
        mockMvc.perform(
                        post("/client/{id}", id)
                                .content(objectMapper.writeValueAsString(client))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("GLEB"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("297229238"));
    }

    @Test
    void updateClient_thenNotFoundData()  throws Exception{
        long id=40l;
        Client client=new Client();
        mockMvc.perform(
                        post("/client/{id}", id)
                                .content(objectMapper.writeValueAsString(client))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundData))
                .andExpect(result -> assertEquals("Такой записи для Client в базе данных не существует"
                        , result.getResolvedException().getMessage()));
    }

    @Test
    void createNewFlight_thenOk()  throws Exception{
        Flight flight=getFlight();
        Plane plane=new Plane();
        plane.setId(1l);
        flight.setPlane(plane);
        mockMvc.perform(post("/sales/flight")
                        .content(objectMapper.writeValueAsString(flight))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberFlight").value("FA777"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.plane.id").isNumber());
    }

    @Test
    void createNewFlight_thenNotFoundDataFlight()  throws Exception{
        Flight flight=getFlight();
        Plane plane=new Plane();
        plane.setId(11l);
        flight.setPlane(plane);
        mockMvc.perform(post("/sales/flight")
                        .content(objectMapper.writeValueAsString(flight))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundData))
                .andExpect(result -> assertEquals("Такой записи для Plane в базе данных не существует"
                        , result.getResolvedException().getMessage()));
    }

    @Test
    void findFreeSeatByIdPlane_ThenOk() throws Exception{
        final long id=1l;
        List<Seat> seats=searcherService.findFreeSeat(id);
        List<SeatDto> list=seats.stream()
                .map(seat -> objectMapper.convertValue(seat, SeatDto.class))
                .collect(Collectors.toList());
        mockMvc.perform(get("/sales/flight/{id}/seat", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void findFreeSeatByIdPlane_thenNotFoundDataFlight() throws Exception{
        final long id=110l;
        mockMvc.perform(get("/sales/flight/{id}/seat", id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundData))
                .andExpect(result -> assertEquals("Такой записи для Flight в базе данных не существует"
                        , result.getResolvedException().getMessage()));
    }

    @Test
    void findAllFlight_ThenOk()  throws Exception{
        List<Flight> flights=searcherService.findFlightAfterCurrentTime();
        List<FlightDto> list=flights.stream()
                .map(flight -> objectMapper.convertValue(flight, FlightDto.class))
                .collect(Collectors.toList());
        mockMvc.perform(get("/sales/flight/list"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void findFlightByCondition()   throws Exception{
        final FieldSearcherDto fieldDto=getFieldDto();
        List<Flight> flights=searcherService.findFlightByConditions(fieldDto);
        List<FlightDto> list=flights.stream()
                .map(flight -> objectMapper.convertValue(flight, FlightDto.class))
                .collect(Collectors.toList());
        mockMvc.perform(post("/sales/flight/list/conditions")
                        .content(objectMapper.writeValueAsString(fieldDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void findFlightByCondition_WithNameCompany()   throws Exception{
        final FieldSearcherDto fieldDto=getFieldDto();
        fieldDto.setNameCompany("AEROFLOT");
        List<Flight> flights=searcherService.findFlightByConditions(fieldDto);
        List<FlightDto> list=flights.stream()
                .map(flight -> objectMapper.convertValue(flight, FlightDto.class))
                .collect(Collectors.toList());
        mockMvc.perform(post("/sales/flight/list/conditions")
                        .content(objectMapper.writeValueAsString(fieldDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void createNewAirCompany_ThenOk() throws Exception{
        AirCompany company=getAirCompany();
        company.setPlanes(List.of(createJak3(),createJak40()));
        mockMvc.perform(post("/company")
                        .content(objectMapper.writeValueAsString(company))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameCompany").value("ZARA"));
    }

    @Test
    void createNewAirCompany_ThrowNotCorrectData() throws Exception{
        AirCompany company=new AirCompany();
        mockMvc.perform(post("/company")
                        .content(objectMapper.writeValueAsString(company))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotCorrectData))
                .andExpect(result -> assertEquals("Введены некорректные данные для AirCompany"
                        , result.getResolvedException().getMessage()));
    }

    @Test
    void findAllAirCompany() throws Exception{
        List<AirCompany> companies=businessService.getAllAirCompany();
        List<AirCompanyDto> list=companies.stream()
                .map(company -> objectMapper.convertValue(company, AirCompanyDto.class))
                .collect(Collectors.toList());
        mockMvc.perform(get("/company/list"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void bookTicket() throws Exception{
        Ticket ticket=getTicket();
        Seat seat=getSeat();
        seat.setId(98L);
        ticket.setSeat(seat);
        mockMvc.perform(post("/sales/ticket/book")
                        .content(objectMapper.writeValueAsString(ticket))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstNamePassenger").value("Roma"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seat.id").isNumber());
    }

    @Test
    void cancelTicket_ThenOk() throws Exception{
        mockMvc.perform(
                        delete("/sales/ticket/book/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void cancelTicket_ThrowNotFoundData() throws Exception{
        mockMvc.perform(
                        delete("/sales/ticket/book/{id}", 111L))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundData))
                .andExpect(result -> assertEquals("Такой записи для Ticket в базе данных не существует"
                        , result.getResolvedException().getMessage()));
    }

    @Test
    void findAllTicketsForClient() throws Exception{
        final long id=4l;
        List<Ticket> tickets=searcherService.findTicketForClient(4L);
        List<TicketDto> list=tickets.stream()
                .map(ticket -> objectMapper.convertValue(ticket, TicketDto.class))
                .collect(Collectors.toList());
        mockMvc.perform(get("/sales/ticket/list/{client_id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));

    }

    private Flight getFlight(){
        Flight flight=new Flight();
        flight.setNumberFlight("FA777");
        flight.setDepartureCity("BREST");
        flight.setDepartureDateTime(LocalDateTime.now());
        flight.setArriveCity("MINSK");
        flight.setArriveDateTime(flight.getDepartureDateTime().plusMinutes(45));
        return flight;
    }

    private FieldSearcherDto getFieldDto(){
        FieldSearcherDto fieldDto=new FieldSearcherDto();
        fieldDto.setStartData(LocalDate.parse("2021-11-01"));
        fieldDto.setDepartureCity("BREST");
        fieldDto.setArriveCity("MINSK");
        return  fieldDto;
    }

    private AirCompany getAirCompany(){
        AirCompany company=new AirCompany();
        company.setNameCompany("ZARA");
        company.setPhoneNumber(7771100);
        return company;
    }

    private Plane createJak40(){
        return Plane.builder()
                .namePlane("ЯК-40")
                .namePilot("VASIA")
                .quantitySeats(4)
                .invertorNumber(1L)
                .build();
    }

    private Plane createJak3(){
        return Plane.builder()
                .namePlane("ЯК-3")
                .namePilot("PETIA")
                .quantitySeats(6)
                .invertorNumber(2L)
                .build();
    }

    private Ticket getTicket(){
        Ticket ticket=new Ticket();
        ticket.setPassportNumberPassenger("AB1112233");
        ticket.setFirstNamePassenger("Roma");
        ticket.setSecondNamePassenger("Golosko");
        return ticket;
    }

    private Seat getSeat(){
        return new Seat();
    }
}