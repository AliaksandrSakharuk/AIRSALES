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
@ApiModel(value = "class AirCompany")
public class AirCompanyDto {
    @ApiModelProperty(value = "number of Company in the data base", example = "1")
    private Long id;
    @Size(min=2, message = "ENTER CORRECT NAME COMPANY")
    @ApiModelProperty(value = "name of Company", example = "BELAVIA")
    private String nameCompany;
    @ApiModelProperty(value = "number phone of Company", example = "292022201")
    private long phoneNumber;
    private List<PlaneDto> planes;
}
