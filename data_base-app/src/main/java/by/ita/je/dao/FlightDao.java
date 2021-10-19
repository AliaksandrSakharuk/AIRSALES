package by.ita.je.dao;

import by.ita.je.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightDao extends CrudRepository<Flight, Long> {

}
