package by.ita.je.dao;

import by.ita.je.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SearcherFreeSeatOnFlightDao extends JpaRepository<Seat, Long> {
    @Query(value = "SELECT * FROM SEAT seat INNER JOIN FLIGHT flight ON seat.flight_id=flight.id " +
            "WHERE seat.booked='false' AND flight.id=:id"
            , nativeQuery = true)
    List<Seat> findFreeSeatOnFlight(@Param("id") long flight_id);
}
