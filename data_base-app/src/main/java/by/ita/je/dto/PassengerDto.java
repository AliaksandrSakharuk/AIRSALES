package by.ita.je.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "class for passengers of clients who bought tickets")
public class PassengerDto {
    @ApiModelProperty(value = "id Passenger in the data base", example = "5", dataType = "long", required = true)
    private Long id;
    @ApiModelProperty(value = "name of passenger", example = "Igor", dataType = "String", required = true)
    private String firstName;
    @ApiModelProperty(value = "surname of passenger", example = "Olechovich", dataType = "String", required = true)
    private String secondName;
    @ApiModelProperty(value = "phone number of passenger", example = "296446666", dataType = "long")
    private long phoneNumber;
    @ApiModelProperty(value = "number passport of passenger", example = "AB5349591", dataType = "String", required = true)
    private String passportNumber;
}
