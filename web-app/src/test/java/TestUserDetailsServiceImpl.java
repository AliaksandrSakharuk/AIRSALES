import by.ita.je.dao.UserDao;
import by.ita.je.excepetion.NotFoundData;
import by.ita.je.model.User;
import by.ita.je.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestUserDetailsServiceImpl {
    @Mock
    private UserDao userDao;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userDetailsService;

    @Test
    public void whenGetAll_emptyList(){
        final List<User> actual=userDetailsService.findAllUsers();
        final List<User> expected= Collections.emptyList();
        Assertions.assertEquals(expected ,actual);
    }

    @Test
    public  void whenGetAll_returnListUsers(){
        final List<User> givenCarsList=new ArrayList<User>();
        givenCarsList.add(new User());
        givenCarsList.add(new User());
        givenCarsList.add(new User());
        Mockito.when(userDao.findAll()).thenReturn(givenCarsList);
        final List<User> actual=userDetailsService.findAllUsers();
        final  List<User> expected=new ArrayList<User>();
        expected.add(new User());
        expected.add(new User());
        expected.add(new User());
        Assertions.assertEquals(expected ,actual);
        Mockito.verify(userDao, Mockito.times(1)).findAll();
    }

    @Test
    public void whenUpdate_returnUser(){
        User user=new User();
        user.setLogin("vova");
        user.setEmail("vova@gmail.com");
        Mockito.when(userDao.findById(1L)).thenReturn(Optional.ofNullable(new User()));
        Mockito.when(userDao.save(user)).thenReturn(user);
        final  User actual=userDetailsService.updateUser(1L, user);
        final User expected=new User();
        expected.setLogin("vova");
        expected.setEmail("vova@gmail.com");
        Assertions.assertEquals(expected, actual);
        Mockito.verify(userDao, Mockito.times(1)).findById(1L);
        Mockito.verify(userDao, Mockito.times(1)).save(user);
    }

    @Test
    public  void whenUpdate_throwNotFoundData(){
        Long id=2L;
        Mockito.when(userDao.findById(id)).thenReturn(Optional.empty());
        NotFoundData notFoundData= Assertions.assertThrows(NotFoundData.class, () -> userDetailsService.updateUser(id, new User()));
        Assertions.assertEquals(notFoundData.getMessage(), "Такой записи для User в базе данных не существует");
        Mockito.verify(userDao, Mockito.times(1)).findById(id);
    }
}
