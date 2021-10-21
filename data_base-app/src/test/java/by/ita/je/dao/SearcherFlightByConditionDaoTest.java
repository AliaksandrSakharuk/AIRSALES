package by.ita.je.dao;

import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.model.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class SearcherFlightByConditionDaoTest {

    @Autowired
    private SearcherFlightByConditionDao searcherFlightByConditionDao;

    @Test
    void findFlight_withoutNameCompany() {
        FieldSearcherDto fieldDto=new FieldSearcherDto();
        fieldDto.setStartData(LocalDate.parse("2021-11-01"));
        fieldDto.setDepartureCity("BREST");
        fieldDto.setArriveCity("MINSK");
        List<Flight> listFlight=searcherFlightByConditionDao.findFlight(fieldDto);
        Assertions.assertNotNull(listFlight);
        Assertions.assertEquals(2,listFlight.size());
    }

    @Test
    void findFlight_withNameCompany() {
        FieldSearcherDto fieldDto=new FieldSearcherDto();
        fieldDto.setStartData(LocalDate.parse("2021-11-01"));
        fieldDto.setDepartureCity("BREST");
        fieldDto.setArriveCity("MOSCOW");
        fieldDto.setNameCompany("AEROFLOT");
        List<Flight> listFlight=searcherFlightByConditionDao.findFlight(fieldDto);
        Assertions.assertNotNull(listFlight);
        Assertions.assertEquals(1,listFlight.size());
    }
}