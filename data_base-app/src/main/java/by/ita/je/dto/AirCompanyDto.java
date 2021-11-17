package by.ita.je.dto;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "name of AirCompany", example = "LUFHANSA" , dataType = "String", required = true)
    private String nameCompany;
    @ApiModelProperty(value = "phone number of AirCompany", example = "292020202" , dataType = "long")
    private long phoneNumber;
    private List<PlaneDto> planes;
}
