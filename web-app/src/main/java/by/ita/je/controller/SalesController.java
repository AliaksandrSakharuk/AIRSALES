package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.model.User;
import by.ita.je.service.api.ApiService;
import by.ita.je.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class SalesController {

    private final UserService userDetailsService;
    private final ApiService apiService;
    private final ObjectMapper objectMapper;

    @GetMapping(value = "/")
    public String home(Model model) {
        List<FlightDto> flights=apiService.getListFlight();
        model.addAttribute("flights", flights);
        model.addAttribute("field", new FieldSearcherDto());
        return "index";
    }

    @GetMapping(value = "/profile")
    public String getProfile(Model model) {
    User user=userDetailsService.getCurrentUser();
    ClientDto clientDto=apiService.findByIdClient(user.getClientId());
    UserDto userDto = objectMapper.convertValue(user, UserDto.class);
    userDto.setClient(clientDto);
    model.addAttribute("user", userDto);
        return "profile";
    }

    @GetMapping(value = "/profile/passengers")
    public String getPassengers(Model model) {
        final User user=userDetailsService.getCurrentUser();
        ClientDto clientDto=apiService.findByIdClient(user.getClientId());
        model.addAttribute("passengers", clientDto.getPassengers());
        return "passengers";
    }

    @GetMapping(value = "/profile/form")
    public String getFormUpdate(Model model){
        final User user=userDetailsService.getCurrentUser();
        ClientDto clientDto=apiService.findByIdClient(user.getClientId());
        UserDto userDto = objectMapper.convertValue(user, UserDto.class);
        userDto.setPassword("");
        userDto.setClient(clientDto);
        model.addAttribute("userDto", userDto);
        return "form_update";
    }

    @PostMapping(value = "/profile/update")
    public String resultUpdateUser(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "form_update";
        }
        else{
            ClientDto clientDto=apiService.updateClient(userDto.getClient());
            User user = objectMapper.convertValue(userDto, User.class);
            User userNew=userDetailsService.updateUser(user.getId(), user);
            UserDto userDtoNew = objectMapper.convertValue(user, UserDto.class);
            userDtoNew.setClient(clientDto);
            return "redirect:/profile";
        }
    }

    @GetMapping(value ="/flight/{id}")
    public String loadFlight(@PathVariable("id") long id, Model model){
        model.addAttribute("seats", apiService.getFreeSeatFlight(id));
        return "seat";
    }

    @PostMapping(value ="/flight/list")
    public String loadFlightsByConditions(@ModelAttribute FieldSearcherDto fieldDto, Model model){
        model.addAttribute("flights", apiService.getListFlightByConditions(fieldDto));
        return "flight_list";
    }

    @GetMapping(value ="/ticket/{id}")
    public String formTicket(@PathVariable("id") long id, Model model){
        TicketDto ticketDto=getTicket(id);
        model.addAttribute("ticketDto", ticketDto);
        return "ticket";
    }

    @PostMapping(value ="/ticket/book")
    public String bookTicket(@Valid  @ModelAttribute TicketDto ticketDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "ticket";
        }
        else{
            apiService.bookTicket(ticketDto);
            return "redirect:/ticket/list";
        }
    }

    @GetMapping(value ="/ticket/list")
    public String getTicketList(Model model){
        final List<TicketDto> tickets=apiService.listTicketForClient(userDetailsService.getCurrentUser().getClientId());
        model.addAttribute("tickets", tickets);
        return "ticket_list";
    }

    @GetMapping(value ="/ticket/cancel/{id}")
    public String cancelBookedTicket(@PathVariable("id") long id){
        apiService.cancelBookTicket(id);
        return "redirect:/ticket/list";
    }

    private TicketDto getTicket(long id){
        SeatDto seatDto=new SeatDto();
        seatDto.setId(id);
        User user=userDetailsService.getCurrentUser();
        TicketDto ticketDto=new TicketDto();
        ticketDto.setSeat(seatDto);
        ticketDto.setClient(apiService.findByIdClient(user.getClientId()));
        return ticketDto;
    }
}
