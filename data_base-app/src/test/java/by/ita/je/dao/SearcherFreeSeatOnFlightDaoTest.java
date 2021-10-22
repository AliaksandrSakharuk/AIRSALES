package by.ita.je.dao;

import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Seat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class SearcherFreeSeatOnFlightDaoTest {

    @Autowired
    private SearcherFreeSeatOnFlightDao searcherFreeSeatOnFlightDao;

    @Test
    void findFreeSeatOnFlight_whenAllSeatsFree() {
        List<Seat> listSeat=searcherFreeSeatOnFlightDao.findFreeSeatOnFlight(1L);
        Assertions.assertNotNull(listSeat);
        Assertions.assertEquals(48,listSeat.size());
    }

    @Test
    void findFreeSeatOnFlight_whenAllSeatsBusy() {
        List<Seat> listSeat=searcherFreeSeatOnFlightDao.findFreeSeatOnFlight(2L);
        Assertions.assertEquals(0,listSeat.size());
    }
}