package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.model.User;
import by.ita.je.service.api.ApiService;
import by.ita.je.service.api.UserService;
import by.ita.je.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final ApiService apiService;
    private final UserService userDetailsService;

    @GetMapping()
    public String getAdmin(Model model) {
        model.addAttribute("companies",apiService.getAllAirCompany());
        return "admin";
    }

    @GetMapping(value = "/flight")
    public String getFormFlight(@ModelAttribute("flightDto") FlightDto flightDto, Model model) {
        model.addAttribute("flightDto", flightDto);
        return "flightForm";
    }

    @PostMapping(value = "/flight/new")
    public String createFlight( @ModelAttribute @Valid FlightDto flightDtoNew, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "flightForm";
        } else {
            apiService.createFlight(flightDtoNew);
            return "redirect:/";
        }
    }

    @GetMapping(value = "/aircompany/form")
    public String getFormForNewAirCompany(Model model){
        model.addAttribute("airCompanyDto", new AirCompanyDto());
        return "form_company";
    }

    @PostMapping(value = "/aircompany/save")
    public String saveNewAirCompany(@Valid @ModelAttribute AirCompanyDto airCompanyDto, BindingResult bindingResult
                                    , Model model){
        if(bindingResult.hasErrors()) {
            return "form_company";
        }else {
            apiService.saveNewAirCompany(airCompanyDto);
            return "redirect:/admin";
        }
    }

    @GetMapping(value = "/user/list")
    public String getUsersList(Model model){
        List<User> users=userDetailsService.findAllUsers();
        List<UserDto> listUserDto = ObjectMapperUtil.convertEToD(users, UserDto.class);
        listUserDto.stream()
        .forEach(userDto -> userDto.setClient(apiService.findByIdClient(userDto.getClientId())));
        model.addAttribute("users", listUserDto);
        return "list_users";
    }

    @GetMapping (value = "/user/enabled/{id}")
    public String blockUnBlockUser(@PathVariable("id") long id){
    userDetailsService.userBlockAndUnBlockedEnabled(id);
        return "redirect:/admin/user/list";
    }

    @ModelAttribute("flightDto")
    private FlightDto getFlight(){
        FlightDto flightDto = new FlightDto();
        flightDto.setPlane(new PlaneDto());
        return flightDto;
    }
}
