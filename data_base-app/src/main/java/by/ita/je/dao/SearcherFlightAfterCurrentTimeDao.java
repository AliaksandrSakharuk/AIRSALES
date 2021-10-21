package by.ita.je.dao;

import by.ita.je.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@org.springframework.stereotype.Repository
public interface SearcherFlightAfterCurrentTimeDao extends JpaRepository<Flight, Long> {
    @Query(value="select * FROM Flight WHERE flight.departure_date_time >= CURRENT_TIMESTAMP"
            , nativeQuery = true)
    public List<Flight> findAllAfterCurrentTime();
}