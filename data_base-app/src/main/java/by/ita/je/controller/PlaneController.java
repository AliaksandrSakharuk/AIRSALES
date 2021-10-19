package by.ita.je.controller;

import by.ita.je.dto.PlaneDto;
import by.ita.je.model.Plane;
import by.ita.je.service.api.PlaneService;
import by.ita.je.service.api.SearcherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class PlaneController {

    private final ObjectMapper objectMapper;
    private final SearcherService searcherService;
    private  final PlaneService planeService;

//    @PostMapping("/plane/free")
//    public PlaneDto findFreePlane(@RequestBody DateForFlightDto fieldDto){
//        final Plane freePlane=searcherService.findFreePlane(fieldDto);
//        return objectMapper.convertValue(freePlane, PlaneDto.class);
//    }

    @GetMapping("/plane/{id}")
    public PlaneDto findById(@PathVariable("id") String id){
        final Plane plane=planeService.readById(Long.valueOf(id));
        return objectMapper.convertValue(plane, PlaneDto.class);
    }

    @PutMapping(value = "/plane")
    public PlaneDto update(@RequestParam(value = "id", required = true) String id, @RequestBody PlaneDto planeDtoNew){
        final Plane plane=objectMapper.convertValue(planeDtoNew, Plane.class);
        Plane planeUpdate=planeService.update(Long.valueOf(id), plane);
        return objectMapper.convertValue(planeUpdate, PlaneDto.class);
    }


//
//
//    @GetMapping("/flight/{id}")
//    public FlightDto findFlightById(@PathVariable("id") String id){
//        Flight flight=flightDao.findById(Long.valueOf(id))
//                .orElseThrow(() -> new NotFoundData("Flight"));;
//        return objectMapper.convertValue(flight, FlightDto.class);
//    }
}
