package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.model.User;
import by.ita.je.service.UserDetailsServiceImpl;
import by.ita.je.service.api.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {

    @Autowired
    private final ApiService apiService;
    @Autowired
    private final UserDetailsServiceImpl userDetailsService;
    @Autowired
    private final ObjectMapper objectMapper;


    @GetMapping(value = "/admin")
    public String getAdmin(Model model) {
        model.addAttribute("companies",apiService.getAllAirCompany());
        return "admin";
    }

    @GetMapping(value = "/admin/flight")
    public String getFormFlight(Model model) {
        FlightDto flightDto = new FlightDto();
        PlaneDto plane = new PlaneDto();
        flightDto.setPlane(plane);
        model.addAttribute("flightDto", flightDto);
        return "flightForm";
    }

    @PostMapping(value = "/admin/flight/new")
    public String createFlight( @ModelAttribute @Valid FlightDto flightDtoNew, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "flightForm";
        }
        else {
            apiService.createFlight(flightDtoNew);
            return "redirect:/";
        }
    }

    @GetMapping(value = "/admin/aircompany/form")
    public String getFormForNewAirCompany(Model model){
        AirCompanyDto airCompanyDto=new AirCompanyDto();
        model.addAttribute("airCompanyDto", airCompanyDto);
        return "form_company";
    }

    @PostMapping(value = "/admin/aircompany/save")
    public String saveNewAirCompany(@Valid @ModelAttribute AirCompanyDto airCompanyDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            return "form_company";
        }
        else {
            apiService.saveNewAirCompany(airCompanyDto);
            return "redirect:/admin";
        }
    }

    @GetMapping(value = "/admin/user/list")
    public String getUsersList(Model model){
        List<User> users=userDetailsService.findAllUsers();
        List<UserDto> listUserDto=new ArrayList<UserDto>();
        for(User user: users){
            ClientDto clientDto=apiService.findByIdClient(user.getClientId());
            UserDto userDto=objectMapper.convertValue(user, UserDto.class);
            userDto.setClient(clientDto);
            listUserDto.add(userDto);
        }
        model.addAttribute("users", listUserDto);
        return "list_users";
    }

    @GetMapping (value = "/admin/user/enabled/{id}")
    public String blockUnBlockUser(@PathVariable("id") long id){
    userDetailsService.userBlockAndUnBlockedEnabled(id);
        return "redirect:/admin/user/list";
    }
}
