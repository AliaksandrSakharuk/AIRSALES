package by.ita.je.dao;

import by.ita.je.model.Passenger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerDao  extends CrudRepository<Passenger, Long> {
}
