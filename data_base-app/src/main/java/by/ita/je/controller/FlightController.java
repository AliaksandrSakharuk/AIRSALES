package by.ita.je.controller;


import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.dto.FlightDto;
import by.ita.je.model.Flight;
import by.ita.je.service.api.FlightService;
import by.ita.je.service.api.SearcherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class FlightController {

    private final ObjectMapper objectMapper;
    private final FlightService flightService;
    private final SearcherService searcherService;


    @GetMapping("/flight/{id}")
    public FlightDto findById(@PathVariable("id") String id){
        final Flight flight=flightService.readById(Long.valueOf(id));
        return objectMapper.convertValue(flight, FlightDto.class);
    }

    @DeleteMapping("/flight/{id}")
    public void delleteById(@PathVariable("id") String id){
        flightService.deleteById(Long.valueOf(id));
    }





}
