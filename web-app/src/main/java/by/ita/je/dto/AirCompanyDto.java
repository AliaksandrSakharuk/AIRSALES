package by.ita.je.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class AirCompanyDto {
    private Long id;
    @Size(min=2, message = "ENTER CORRECT NAME COMPANY")
    private String nameCompany;
    private long phoneNumber;
    private List<PlaneDto> planes;
}
