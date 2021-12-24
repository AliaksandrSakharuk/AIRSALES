package by.ita.je.service;

import by.ita.je.exception.NotCorrectSeat;
import by.ita.je.model.*;
import by.ita.je.service.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private final FlightService flightService;
    private final AirCompanyService companyService;
    private final SeatSericve seatSericve;
    private final TicketService ticketService;
    @Autowired
    private PlaneService planeService;
    private final PassengerService passengerService;
    private final SearcherService searcherService;
    private final ClientService clientService;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor=Exception.class)
    public Flight createNewFlight(Flight flight) {
        Plane plane=planeService.readById(flight.getPlane().getId());
        flight.setPlane(plane);
        createSeat(flight);
        return flightService.save(flight);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public AirCompany createNewAirCompany(AirCompany company){
        createIfNotRelationshipAirCompanyToPlanes(company);
        return companyService.save(company);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public List<AirCompany> getAllAirCompany(){
        return companyService.readAll();
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor=Exception.class)
    public Ticket bookTicket(Ticket ticket) throws NotCorrectSeat {
        Seat seat=seatSericve.readById(ticket.getSeat().getId());
        ticket.setClient(clientService.readById(ticket.getClient().getId()));
        if(seat.isBooked()==true) throw new NotCorrectSeat(seat.getNumberSeat());
        seat.setBooked(true);
        ticket.setSeat(seat);
        ticket.setBookedDateTime(LocalDateTime.now());
        seatSericve.update(ticket.getSeat().getId(),ticket.getSeat());

        if(searcherService.findPassengerByPassport(ticket.getClient().getId()
                , ticket.getPassportNumberPassenger().trim()).isEmpty()){
            passengerService.savePassenger(createPassenger(ticket));
        }
        return ticketService.save(ticket);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void cancelBookedTicket(long idTicket){
        Ticket ticket= ticketService.readById(idTicket);
        Seat seat=ticket.getSeat();
        seat.setBooked(false);
        seatSericve.update(seat.getId(),seat);
        ticketService.deleteById(idTicket);
    }

    private void createSeat(Flight flight){
        List<Seat> list=new ArrayList<Seat>();
        for(int numberLine=1; numberLine<=flight.getPlane().getQuantityLines();numberLine++){
            char num='A';
            for(int numSeatInLine=0; numSeatInLine<flight.getPlane().getSeatsInLine();numSeatInLine++){
                String numSeat=numberLine + Character.toString(num);
                list.add(Seat.builder().numberSeat(numSeat).build());
                num++;
            }
        }
        flight.setSeats(list);
    }

    private void createIfNotRelationshipAirCompanyToPlanes(AirCompany company){
        if (Objects.isNull(company.getPlanes()) || company.getPlanes().isEmpty()){
            Plane boeing=createBoeing737_500();
            Plane embrare=createEmbraer195();
            company.setPlanes(List.of(boeing, embrare));
        }
    }

    private Plane createBoeing737_500(){
        return Plane.builder()
                .namePlane("Boeing 737-500")
                .namePilot("James Bond")
                .quantitySeats(144)
                .seatsInLine(6)
                .quantityLines(24)
                .invertorNumber(123456432L)
                .build();
    }

    private Plane createEmbraer195(){
        return Plane.builder()
                .namePlane("Embraer 195")
                .namePilot("SCALA")
                .quantitySeats(114)
                .seatsInLine(6)
                .quantityLines(19)
                .invertorNumber(7777777L)
                .build();
    }

    private Passenger createPassenger(Ticket ticket){
        return  Passenger.builder()
                .firstName(ticket.getFirstNamePassenger().trim())
                .secondName(ticket.getSecondNamePassenger().trim())
                .passportNumber(ticket.getPassportNumberPassenger().trim())
                .phoneNumber(ticket.getPhoneNumberPassenger())
                .build();
    }
}
