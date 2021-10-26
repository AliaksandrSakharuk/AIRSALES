package by.ita.je.dao;

import by.ita.je.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SearcherPassengerByNumberPassport extends JpaRepository<Passenger, Long> {
    @Query(value = "select * from Passenger passenger LEFT JOIN Client client on passenger.client_id=client.id " +
            "WHERE passport_number=:passport AND client.id=:client_id"
            , nativeQuery = true)
    List<Passenger> findPassengerByPassport(@Param("client_id") long client_id
            , @Param("passport") String passportNumber);
}
