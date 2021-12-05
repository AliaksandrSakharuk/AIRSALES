package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "class for Flight")
public class FlightDto {
    private Long id;
    @ApiModelProperty(value = "number Of Flight", example = "FA721" , dataType = "String", required = true)
    @Size(min=5,  message = "THIS FIELD MUST BE MORE THEN 5 SYMBOLS")
    private String numberFlight;
    @ApiModelProperty(value = "city is start your travel", example = "BREST" , dataType = "String", required = true)
    @NotBlank(message = "ENTER THIS FIELD")
    @NotNull
    private String departureCity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME )
    @ApiModelProperty(value = "start your travel", example = "2021-11-02T15:00" , dataType = "date-time", required = true)
    @FutureOrPresent
    private LocalDateTime departureDateTime;
    @ApiModelProperty(value = "city is finish your travel", example = "MINSK" , dataType = "String", required = true)
    @NotBlank(message = "ENTER THIS FIELD")
    @NotNull
    private String arriveCity;
    private int durationFlight;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME )
    @ApiModelProperty(value = "finish your travel", example = "2021-11-02T16:00" , dataType = "date-time", required = true)
    @FutureOrPresent
    private LocalDateTime arriveDateTime;
    private PlaneDto plane;
    private List<SeatDto> seats;
}
