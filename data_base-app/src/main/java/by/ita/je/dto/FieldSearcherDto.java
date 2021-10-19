package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldSearcherDto {
    private ZonedDateTime startData;
    private String departureCity;
    private String arriveCity;
    private String nameCompany;
    private boolean stopOver;
}
