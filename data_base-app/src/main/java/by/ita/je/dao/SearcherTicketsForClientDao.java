package by.ita.je.dao;


import by.ita.je.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface SearcherTicketsForClientDao extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT * FROM TICKET ticket WHERE ticket.CLIENT_ID=:id"
            , nativeQuery = true)
    List<Ticket> findTicketForClient(@Param("id") long client_id);

}
