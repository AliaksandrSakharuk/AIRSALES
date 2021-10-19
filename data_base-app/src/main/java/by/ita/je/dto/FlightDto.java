package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private Long id;
    private String numberFlight;
    private String departureCity;
    private LocalDateTime departureDateTime;
    private String arriveCity;
    private int durationFlight;
    private LocalDateTime arriveDateTime;
    private PlaneDto plane;
    private List<SeatDto> seats;

}
