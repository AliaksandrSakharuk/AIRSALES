package service;

import by.ita.je.dao.PlaneDao;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Plane;
import by.ita.je.service.PlaneServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestPlaneServiceImpl {
    @Mock
    private PlaneDao planeDao;
    @InjectMocks
    private PlaneServiceImpl planeService;

    @Test
    public void whenFindById_returnPlane(){
        Mockito.when(planeDao.findById(1L)).thenReturn(Optional.ofNullable(new Plane()));
        final Plane actual=planeService.readById(1L);
        final Plane expected=getPlane();
        Assertions.assertEquals(expected,actual);
        Mockito.verify(planeDao, Mockito.times(1)).findById(1L);
    }

    @Test
    public void whenFindById_throwsNotFoundData(){
        Long id=2L;
        Mockito.when(planeDao.findById(id)).thenReturn(Optional.empty());
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> planeService.readById(id));
        Assertions.assertEquals(notFoundData.getMessage(), "Такой записи для Plane в базе данных не существует");
        Mockito.verify(planeDao, Mockito.times(1)).findById(id);
    }


    private Plane getPlane(){ return new Plane();}
}
