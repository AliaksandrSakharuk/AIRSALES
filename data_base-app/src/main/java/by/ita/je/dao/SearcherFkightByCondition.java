package by.ita.je.dao;

import by.ita.je.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SearcherFkightByCondition extends JpaRepository<Flight, Long> {
    @Query(value="select * FROM flight WHERE departure_city=:from_city AND arrive_city=:to_city " +
            "AND departure_date_time BETWEEN :from AND :to"
            , nativeQuery = true)
    public List<Flight> findFlightByCondition(@Param("from_city") String fromCity
            , @Param("to_city") String toCity
            , @Param("from") LocalDateTime fromDate
            , @Param("to") LocalDateTime toDate);
}
