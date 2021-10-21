package service;

import by.ita.je.dao.AirCompanyDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.AirCompany;
import by.ita.je.service.AirCompanyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class TestAirCompanyServiceImpl {

    @Mock
    private AirCompanyDao airCompanyDao;
    @InjectMocks
    private AirCompanyServiceImpl airCompanyService;

    @Test
    public void whenCreate_returnAirCompany() {
        AirCompany airCompany=new AirCompany();
        airCompany.setNameCompany("BELAVIA");
        airCompany.setPhoneNumber(292001211);
        Mockito.when(airCompanyDao.save(airCompany)).thenReturn(airCompany);
        final AirCompany actual=airCompanyService.save(airCompany);
        AirCompany expected=getAirCompany();
        expected.setNameCompany("BELAVIA");
        expected.setPhoneNumber(292001211);
        Assertions.assertEquals(expected ,actual);
        Mockito.verify(airCompanyDao, Mockito.times(1)).save(airCompany);
    }

    @Test
    public void whenCreate_returnNotCorrectData(){
        AirCompany airCompany=new AirCompany();
        airCompany.setNameCompany("");
        NotCorrectData notCorrectData=Assertions.assertThrows(NotCorrectData.class, () -> airCompanyService.save(airCompany));
        Assertions.assertEquals(notCorrectData.getMessage(), "Введены некорректные данные для AirCompany");
        Mockito.verify(airCompanyDao, Mockito.times(0)).save(airCompany);
    }

    @Test
    public void whenREadById_returnAirCompany(){
        Mockito.when(airCompanyDao.findById(1L)).thenReturn(Optional.ofNullable(new AirCompany()));
        final AirCompany actual=airCompanyService.readById(1L);
        final AirCompany expected=getAirCompany();
        Assertions.assertEquals(expected,actual);
        Mockito.verify(airCompanyDao, Mockito.times(1)).findById(1L);
    }

    @Test
    public void whenReadById_throwsNotFoundData(){
        Long id=2L;
        Mockito.when(airCompanyDao.findById(id)).thenReturn(Optional.empty());
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> airCompanyService.readById(id));
        Assertions.assertEquals(notFoundData.getMessage(), "Такой записи для AirCompany в базе данных не существует");
        Mockito.verify(airCompanyDao, Mockito.times(1)).findById(id);
    }

    @Test
    public void whenGetAll_emptyList(){
        final List<AirCompany> actual=airCompanyService.readAll();
        final List<AirCompany> expected= Collections.emptyList();
        Assertions.assertEquals(expected ,actual);
    }

    @Test
    public  void whenGetAll_returnListAirCompany(){
        final List<AirCompany> givenCarsList=new ArrayList<AirCompany>();
        givenCarsList.add(new AirCompany());
        givenCarsList.add(new AirCompany());
        givenCarsList.add(new AirCompany());
        Mockito.when(airCompanyDao.findAll()).thenReturn(givenCarsList);
        final List<AirCompany> actual=airCompanyService.readAll();
        final  List<AirCompany> expected=new ArrayList<AirCompany>();
        expected.add(new AirCompany());
        expected.add(new AirCompany());
        expected.add(new AirCompany());
        Assertions.assertEquals(expected ,actual);
        Mockito.verify(airCompanyDao, Mockito.times(1)).findAll();
    }

    private AirCompany getAirCompany(){ return new AirCompany();}
}
