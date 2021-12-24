package by.ita.je.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "class for Client")
public class ClientDto {
    @ApiModelProperty(value = "id number of Client in the DATA BASE", example = "4" , dataType = "long")
    private Long id;
    @ApiModelProperty(value = "name of Client", example = "Roma" , dataType = "String", required = true)
    @NotNull
    @NotBlank
    private String firstName;
    @ApiModelProperty(value = "surname of Client", example = "Salapura", dataType = "String", required = true)
    @NotNull
    @NotBlank
    private String secondName;
    @ApiModelProperty(value = "phoneNumber of Client", example = "297209238", dataType = "Long")
    private Long phoneNumber;
    private List<PassengerDto> passengers;

}
