package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "fields for searcher's flights by conditions")
public class FieldSearcherDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat( pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE_TIME )
    @ApiModelProperty(value = "day of trip", example = "2021-11-01" , dataType = "date-time", required = true)
    private LocalDate startData;
    @ApiModelProperty(value = "city of start trip", example = "BREST" , dataType = "String", required = true)
    private String departureCity;
    @ApiModelProperty(value = "city of finish trip", example = "MINSK" , dataType = "String", required = true)
    private String arriveCity;
    @ApiModelProperty(value = "owner of plane", example = "BELAVIA" , dataType = "String", required = true)
    private String nameCompany;
}
