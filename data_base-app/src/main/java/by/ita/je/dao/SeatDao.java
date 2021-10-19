package by.ita.je.dao;

import by.ita.je.model.Seat;
import by.ita.je.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDao extends CrudRepository<Seat, Long> {
}
