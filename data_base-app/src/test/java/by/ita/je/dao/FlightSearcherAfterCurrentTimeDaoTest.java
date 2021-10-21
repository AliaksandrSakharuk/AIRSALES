package by.ita.je.dao;

import by.ita.je.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FlightSearcherAfterCurrentTimeDaoTest {

    @Autowired
    private FlightSearcherAfterCurrentTimeDao searcherFlightAfterCurrentTimeDao;

    @Test
    void findAllAfterCurrentTime() {
        List<Flight> listFlight=searcherFlightAfterCurrentTimeDao.findAllAfterCurrentTime();
        Assertions.assertNotNull(listFlight);
        Assertions.assertEquals(6,listFlight.size());
    }
}