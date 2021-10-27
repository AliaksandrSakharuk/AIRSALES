package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.model.User;
import by.ita.je.service.UserDetailsServiceImpl;
import by.ita.je.service.api.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Api(tags = "Controller Business API for ticket-sales")
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
    @ApiIgnore
    public String getFormFlight(@RequestParam(value = "date_from") String dateFrom
            ,@RequestParam(value = "date_to") String dateTo
            , Model model) {
        FlightDto flightDto=new FlightDto();
        PlaneDto plane=new PlaneDto();
        flightDto.setPlane(plane);
        flightDto.setDepartureDateTime(LocalDateTime.parse(dateFrom));
        flightDto.setArriveDateTime(LocalDateTime.parse(dateTo));
        model.addAttribute("flightDto", flightDto);
        return "flightForm";
    }

    @PostMapping(value = "/admin/flight/new")
    @ApiOperation(value = "Create new Flight")
    public String createFlight( @ModelAttribute @Valid FlightDto flightDtoNew, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "flightForm";
        }
        else {
            FlightDto flightDto=apiService.createFlight(flightDtoNew);
            return "redirect:/";
        }
    }

    @GetMapping(value = "/admin/aircompany/form")
    @ApiOperation(value = "Enter form for new AirCompany")
    @ApiImplicitParams({@ApiImplicitParam(name = "nameCompany", value = "NAME OF COMPANY", required = true,
                    dataType = "String", example = "LUFHANSA"),
            @ApiImplicitParam (name = "phoneNumber", value = "NUMBER PHONE. MUST BE MORE THEN 9 FIGURE"
                    , required = true, dataType = "long", example = "297210000")})
    public String getFormForNewAirCompany(Model model){
        AirCompanyDto airCompanyDto=new AirCompanyDto();
        model.addAttribute("airCompanyDto", airCompanyDto);
        return "form_company";
    }

    @PostMapping(value = "/admin/aircompany/save")
    @ApiOperation(value = "Add in the dataBase new AirCompany")
    @ApiImplicitParams({@ApiImplicitParam(name = "nameCompany", value = "NAME OF COMPANY", required = true,
            dataType = "String", example = "LUFHANSA"),
            @ApiImplicitParam (name = "phoneNumber", value = "NUMBER PHONE. MUST BE MORE THEN 9 FIGURE"
                    , required = true, dataType = "long", example = "297210000")})
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
    @ApiOperation(value = "Get List of Users.")
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
    @ApiOperation(value = "Block and unblock User")
    public String blockUnBlockUser(@PathVariable("id") long id){
    userDetailsService.userBlockAndUnBlockedEnabled(id);
        return "redirect:/admin/user/list";
    }
}
