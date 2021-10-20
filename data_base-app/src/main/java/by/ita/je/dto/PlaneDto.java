package by.ita.je.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaneDto {
    private Long id;
    private Long invertorNumber;
    private String namePlane;
    private String namePilot;
    private  int quantitySeats;
    private int seatsInLine;
    private int quantityLines;
//    private List<FlightDto> flights;
}
