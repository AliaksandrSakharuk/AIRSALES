package by.ita.je.service.api;

import by.ita.je.dto.FieldUserDto;
import by.ita.je.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    public boolean saveUser(User user);

    public boolean renewalPassword(FieldUserDto fieldUserDto);

    public void userBlockAndUnBlockedEnabled(long id);

    public User getCurrentUser();

    public User updateUser(Long id, User userNew);

    public List<User> findAllUsers();
}
