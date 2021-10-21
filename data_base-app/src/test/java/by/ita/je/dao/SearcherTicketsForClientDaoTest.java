package by.ita.je.dao;

import by.ita.je.model.Flight;
import by.ita.je.model.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SearcherTicketsForClientDaoTest {
    @Autowired
    private SearcherTicketsForClientDao searcherTicketsForClient;

    @Test
    void findTicketForClient() {
        List<Ticket> listTicket=searcherTicketsForClient.findTicketForClient(4L);
        Assertions.assertNotNull(listTicket);
        Assertions.assertEquals(3,listTicket.size());
    }
}