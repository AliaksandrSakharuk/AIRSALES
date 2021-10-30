package by.ita.je.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "class for Plane")
public class PlaneDto {
    @ApiModelProperty(value = "ID in the DATA BASE Of Plane", example = "1", dataType = "String", required = true)
    private Long id;
    @ApiModelProperty(value = "invertor number Of Plane", example = "234567", dataType = "long")
    private Long invertorNumber;
    @ApiModelProperty(value = "model of Plane", example = "JAMES BOND", dataType = "String", required = true)
    private String namePlane;
    @ApiModelProperty(value = "name's pilot Of Plane", example = "AH 24", dataType = "String")
    private String namePilot;
    @ApiModelProperty(value = "total seats in this Plane", example = "487", dataType = "int")
    private int quantitySeats;
    @ApiModelProperty(value = "total seats by line in this Plane", example = "4", dataType = "int", required = true)
    private int seatsInLine;
    @ApiModelProperty(value = "total lines in this Plane", example = "12", dataType = "int", required = true)
    private int quantityLines;
}

