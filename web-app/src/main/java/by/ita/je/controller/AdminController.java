package by.ita.je.controller;



import by.ita.je.dto.AirCompanyDto;
import by.ita.je.dto.ClientDto;
import by.ita.je.dto.FlightDto;
import by.ita.je.dto.UserDto;
import by.ita.je.model.User;
import by.ita.je.service.UserDetailsServiceImpl;
import by.ita.je.service.api.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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
    public String getFormFlight(@RequestParam(value = "date_from", required = false) String dateFrom
            ,@RequestParam(value = "date_to", required = false) String dateTo
            , Model model) {
        FlightDto flightDto=new FlightDto();
        flightDto.setDepartureDateTime(LocalDateTime.parse(dateFrom));
        flightDto.setArriveDateTime(LocalDateTime.parse(dateTo));
        model.addAttribute("flights", flightDto);
        return "flightForm";
    }

    @PostMapping(value = "/admin/flight/new")
    public String createFlight(@ModelAttribute FlightDto flightDtoNew, Model model) {
        FlightDto flightDto=apiService.createFlight(flightDtoNew);
        return "redirect:/";
    }

    @GetMapping(value = "/admin/aircompany/form")
    public String getFormForNewAirCompany(Model model){
        model.addAttribute("company", new AirCompanyDto());
        return "form_company";
    }

    @PostMapping(value = "/admin/aircompany/save")
    public String saveNewAirCompany(@ModelAttribute AirCompanyDto companyDto, Model model){
        apiService.saveNewAirCompany(companyDto);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/user/list")
    public String getUsersList(Model model){
        System.out.println(userDetailsService.findAllUsers());
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
