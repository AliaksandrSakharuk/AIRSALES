package by.ita.je.dao;

import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.model.Flight;

import java.util.List;

public interface SearacherFlightByConditionDao {
    public List<Flight> findFlight(FieldSearcherDto searcherDto);
}
