package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;
    private String firstName;
    private String secondName;
    private Long phoneNumber;
    private Long userId;
    private List<PassengerDto> passengers;
}
