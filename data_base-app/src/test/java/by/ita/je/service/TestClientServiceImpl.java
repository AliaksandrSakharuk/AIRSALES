package by.ita.je.service;

import by.ita.je.dao.ClientDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Client;
import by.ita.je.service.ClientServiceImpl;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestClientServiceImpl {
    @Mock
    private ClientDao clientDao;
    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void whenSave_returnClient() {
        Client client=new Client();
        client.setFirstName("Petia");
        client.setSecondName("Bobr");
        Mockito.when(clientDao.save(client)).thenReturn(client);
        final Client actual=clientService.save(client);
        Client expected=getClient();
        expected.setFirstName("Petia");
        expected.setSecondName("Bobr");
        Assertions.assertEquals(expected ,actual);
        Mockito.verify(clientDao, Mockito.times(1)).save(client);
    }

    @Test
    public void whenUpdate_returnClient(){
        Client client=new Client();
        Mockito.when(clientDao.findById(1L)).thenReturn(Optional.ofNullable(new Client()));
        Mockito.when(clientDao.save(client)).thenReturn(client);
        final  Client actual=clientService.update(1L, client);
        final Client expected=getClient();
        Assertions.assertEquals(expected, actual);
        Mockito.verify(clientDao, Mockito.times(1)).findById(1L);
        Mockito.verify(clientDao, Mockito.times(1)).save(client);
    }

    @Test
    public  void whenUpdate_throwNotFoundData(){
        Long id=2L;
        Mockito.when(clientDao.findById(id)).thenReturn(Optional.empty());
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> clientService.update(id, new Client()));
        Assertions.assertEquals(notFoundData.getMessage(), "Такой записи для Client в базе данных не существует");
        Mockito.verify(clientDao, Mockito.times(1)).findById(id);
    }

    @Test
    public void whenReadById_returnClient(){
        Mockito.when(clientDao.findById(1L)).thenReturn(Optional.ofNullable(new Client()));
        final Client actual=clientService.readById(1L);
        final Client expected=getClient();
        Assertions.assertEquals(expected,actual);
        Mockito.verify(clientDao, Mockito.times(1)).findById(1L);
    }

    @Test
    public void whenReadById_throwsNotFoundData(){
        Long id=2L;
        Mockito.when(clientDao.findById(id)).thenReturn(Optional.empty());
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> clientService.readById(id));
        Assertions.assertEquals(notFoundData.getMessage(), "Такой записи для Client в базе данных не существует");
        Mockito.verify(clientDao, Mockito.times(1)).findById(id);
    }

    private Client getClient(){ return new Client();}
}
