package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "class for ticket of clients")
public class TicketDto {
    @ApiModelProperty(value = "id ticket in the data base", example = "7", dataType = "long")
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME )
    @ApiModelProperty(value = "date time when booked this ticket", example = "2021-10-29T15:00" , dataType = "date-time", required = true)
    private LocalDateTime bookedDateTime;
    @ApiModelProperty(value = "name of passenger", example = "Igor", dataType = "String", required = true)
    private String firstNamePassenger;
    @ApiModelProperty(value = "surname of passenger", example = "Olechovich", dataType = "String", required = true)
    private String secondNamePassenger;
    @ApiModelProperty(value = "phone number of passenger", example = "296446666", dataType = "long", required = true)
    private Long phoneNumberPassenger;
    @ApiModelProperty(value = "number passport of passenger", example = "AB5349591", dataType = "String", required = true)
    private String passportNumberPassenger;
    private SeatDto seat;
    private ClientDto client;
}
