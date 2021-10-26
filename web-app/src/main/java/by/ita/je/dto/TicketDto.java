package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    @DateTimeFormat( pattern = "yyyy-MM-dd'T'HH:mm", iso = DateTimeFormat.ISO.DATE_TIME )
    private LocalDateTime bookedDateTime;
    @NotBlank(message = "ENTER THIS FIELD")
    private String firstNamePassenger;
    @NotBlank(message = "ENTER THIS FIELD")
    private String secondNamePassenger;
    private long phoneNumberPassenger;
    @NotBlank(message = "ENTER THIS FIELD")
    private String passportNumberPassenger;
    private SeatDto seat;
    private ClientDto client;
}
