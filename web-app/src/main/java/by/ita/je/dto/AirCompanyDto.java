package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirCompanyDto {
    private Long id;
    @Size(min=2, message = "ENTER CORRECT NAME COMPANY")
    private String nameCompany;
    private Long phoneNumber;
    private List<PlaneDto> planes;
}
