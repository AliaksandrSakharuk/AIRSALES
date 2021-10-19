package by.ita.je.controller;



import by.ita.je.dto.FlightDto;
import by.ita.je.service.api.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final RestTemplate restTemplate;
    private final ApiService apiService;


    @GetMapping(value = "/admin")
    public String getAdmin() {
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

}
