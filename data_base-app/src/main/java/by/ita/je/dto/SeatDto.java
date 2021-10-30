package by.ita.je.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "class for seats of flight")
public class SeatDto {
    @ApiModelProperty(value = "id seat in the data base", example = "2", dataType = "long")
    private Long id;
    @ApiModelProperty(value = "number seat in the plane", example = "1B", dataType = "String")
    private String numberSeat;
    @ApiModelProperty(value = "status this seat: free or booked", example = "1B", dataType = "boolean")
    private boolean booked;
    private FlightDto flight;
}
