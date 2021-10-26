package by.ita.je.dao;

import by.ita.je.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FlightSearcherAfterCurrentTimeDao extends JpaRepository<Flight, Long> {
    @Query(value="select * FROM Flight WHERE flight.departure_date_time >= CURRENT_TIMESTAMP ORDER BY departure_date_time"
            , nativeQuery = true)
    public List<Flight> findAllAfterCurrentTime();
}