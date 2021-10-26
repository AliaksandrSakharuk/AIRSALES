package by.ita.je.dao;

import by.ita.je.model.Passenger;
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
class SearcherPassengerByNumberPassportTest {

    @Autowired
    private SearcherPassengerByNumberPassport searcherPassengerByNumberPassport;

    @Test
    void findPassengerByPassport_ThenReturn() {
        final List<Passenger> list=searcherPassengerByNumberPassport.findPassengerByPassport(4l, "AB5349591");
        Assertions.assertNotNull(list);
        Assertions.assertEquals(1,list.size());
        Assertions.assertTrue(list.get(0).getId()>0);
        Assertions.assertEquals((list.get(0)).getFirstName(),"Igor");
    }
}