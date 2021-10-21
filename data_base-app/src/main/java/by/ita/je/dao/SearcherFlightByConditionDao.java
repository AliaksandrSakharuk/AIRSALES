package by.ita.je.dao;

import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.model.Flight;

import java.util.List;

public interface SearcherFlightByConditionDao {
    public List<Flight> findFlight(FieldSearcherDto searcherDto);
}
