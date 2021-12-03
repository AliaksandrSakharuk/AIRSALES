package by.ita.je.service;

import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotCorrectSeat;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.*;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.PlaneService;
import by.ita.je.service.api.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BusinessServiceImplTest {

    @Autowired
    private BusinessService businessService;
    @Autowired
    private TicketService ticketService;

    @Test
    void createNewFlight_returnNewFlight() {
        Flight expected=getFlight();
        Flight actual=businessService.createNewFlight(expected);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getDepartureDateTime(),actual.getDepartureDateTime());
        Assertions.assertEquals(expected.getArriveDateTime(),actual.getArriveDateTime());
        Assertions.assertEquals(expected.getDepartureCity(),actual.getDepartureCity());
        Assertions.assertTrue(actual.getSeats().size()==48);
        Assertions.assertTrue(actual.getPlane().getId()==1);
    }

    @Test
    void createNewAirCompany_returnAirCompany_whenNoRelationShip() {
        AirCompany expected=getAirCompany();
        AirCompany actual=businessService.createNewAirCompany(expected);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getNameCompany(),actual.getNameCompany());
        Assertions.assertTrue(expected.getPhoneNumber()==actual.getPhoneNumber());
        Assertions.assertTrue(actual.getPlanes().size()>0);
        actual.getPlanes().forEach(plane -> Assertions.assertTrue(plane.getId()>0));
    }

    @Test
    void createNewAirCompany_returnAirCompany_whenRelationShip() {
        AirCompany expected=getAirCompany();
        expected.setPlanes(List.of(createJak3(),createJak40(), createJak3()));

        AirCompany actual=businessService.createNewAirCompany(expected);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getNameCompany(),actual.getNameCompany());
        Assertions.assertTrue(expected.getPhoneNumber()==actual.getPhoneNumber());
        Assertions.assertTrue(actual.getPlanes().size()==3);
        actual.getPlanes().forEach(plane -> Assertions.assertTrue(plane.getId()>0));
    }

    @Test
    void createNewAirCompany_returnNotCorrectData() {
        final AirCompany airCompany=new AirCompany();
        NotCorrectData notCorrectData=Assertions.assertThrows(NotCorrectData.class, () -> businessService.createNewAirCompany(airCompany));
        Assertions.assertEquals( "Введены некорректные данные для AirCompany", notCorrectData.getMessage());
    }

    @Test
    void getAllAirCompanyAfterCurrentTime() {
        final List<AirCompany> actualList=businessService.getAllAirCompany();
        Assertions.assertNotNull(actualList);
        actualList.forEach(airCompany -> Assertions.assertTrue(airCompany.getId()>0));
    }

    @Test
    void bookTicket_returnTicket() {
        Ticket expected=getTicket();
        Seat seat=getSeat();
        seat.setId(100L);
        expected.setSeat(seat);
        expected.setClient(getClient());
        final Ticket actual=businessService.bookTicket(expected);
        expected.setBookedDateTime(actual.getBookedDateTime());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getPassportNumberPassenger(),actual.getPassportNumberPassenger());
        Assertions.assertTrue(actual.getSeat().isBooked()==true);
        Assertions.assertTrue(actual.getSeat().getId()==expected.getSeat().getId());
    }

    @Test
    void bookTicket_returnNotCorrectSeat() {
        Ticket expected=getTicket();
        Seat seat=getSeat();
        seat.setId(97L);
        seat.setNumberSeat("1A");
        expected.setSeat(seat);
        expected.setClient(getClient());
        NotCorrectSeat notCorrectSeat=Assertions.assertThrows(NotCorrectSeat.class, () -> businessService.bookTicket(expected));
        Assertions.assertEquals( "Место 1A уже забронировано", notCorrectSeat.getMessage());
    }

    @Test
    void cancelBookedTicket_ThenOk() {
        Long id=6L;
        businessService.cancelBookedTicket(id);
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> ticketService.readById(id));
        Assertions.assertEquals("Такой записи для Ticket в базе данных не существует", notFoundData.getMessage());
    }

    @Test
    void cancelBookedTicket_ThrowNotFondData() {
        Long id=60L;
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> businessService.cancelBookedTicket(id));
        Assertions.assertEquals("Такой записи для Ticket в базе данных не существует", notFoundData.getMessage());
    }

    private Flight getFlight(){
        Flight flight=new Flight();
        flight.setNumberFlight("FA777");
        flight.setDepartureCity("BREST");
        flight.setArriveCity("MINSK");
        flight.setDepartureDateTime(LocalDateTime.of(2021,11,02,11,20));
        flight.setArriveDateTime(LocalDateTime.of(2021,11,02,12,00));
        flight.setPlane(getPlane());
        return flight;
    }

    private Plane getPlane(){
        Plane plane=new Plane();
        plane.setId(1l);
        return plane;
    }

    private AirCompany getAirCompany(){
        AirCompany company=new AirCompany();
        company.setNameCompany("ZARA");
        company.setPhoneNumber(7771100L);
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
        ticket.setPhoneNumberPassenger(292001211L);
        return ticket;
    }

    private Seat getSeat(){
        return new Seat();
    }

    private Client getClient(){
        Client client=Client.builder()
                .id(4l)
                .firstName("roma")
                .secondName("salapura")
                .phoneNumber(297229238L)
                .build();
        return client;
    }
}