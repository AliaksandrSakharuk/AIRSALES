package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirCompanyDto {
    private Long id;
    private String nameCompany;
    private long phoneNumber;
    private List<PlaneDto> planes;
}
