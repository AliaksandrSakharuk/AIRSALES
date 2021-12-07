package by.ita.je.service;

import by.ita.je.dao.RoleDao;
import by.ita.je.dao.UserDao;
import by.ita.je.dto.FieldUserDto;
import by.ita.je.excepetion.NotFoundData;
import by.ita.je.model.User;
import by.ita.je.service.api.MessageService;
import by.ita.je.service.api.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MessageService messageService;
    private RoleDao roleDao;
    private UserDao userDao;
    private KafkaProducer kafkaProducer;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor=Exception.class)
    public boolean saveUser(User user) {
        User userFromDB = userDao.findByLogin(user.getLogin().trim());
        if (userFromDB != null) {
            return false;
        }   else{
            user.setLogin(user.getLogin().trim());
            user.setPassword(user.getPassword().trim());
            user.setRoles(List.of(roleDao.findByRoleName("READER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            userDao.save(user);

            kafkaProducer.send("msg", "You created user");

            return true;
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor=Exception.class)
    public boolean renewalPassword(FieldUserDto fieldUserDto){
        User userFromDB = userDao.findByLogin(fieldUserDto.getLogin());
        if (userFromDB != null && userFromDB.getEmail().equals(fieldUserDto.getEmail())) {
            final String password =getTemporaryPassword();
            userFromDB.setPassword(bCryptPasswordEncoder.encode(password));
            userDao.save(userFromDB);
            messageService.sendMessage(password, fieldUserDto.getEmail());
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void userBlockAndUnBlockedEnabled(long id){
        User user=userDao.findById(id)
                .orElseThrow(() -> new NotFoundData("User"));
        if(!user.getRoles().contains(roleDao.findByRoleName("ADMIN"))) {
            if(user.getRoles().contains(roleDao.findByRoleName("READER"))) {
                user.getRoles().add(roleDao.findByRoleName("BLOCKED"));
                user.getRoles().remove(roleDao.findByRoleName("READER"));
            }else{
                user.getRoles().add(roleDao.findByRoleName("READER"));
                user.getRoles().remove(roleDao.findByRoleName("BLOCKED"));
            }
            userDao.save(user);
        }
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public List<User> findAllUsers(){
        return userDao.findAll();
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDao.findByLogin(auth.getName());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor=Exception.class)
    public User updateUser(Long id, User userNew) {
        User userFromDB = userDao.findById(id)
                .orElseThrow(() -> new NotFoundData("User"));
        if (!userNew.getLogin().equals(userFromDB.getLogin())) {
            userFromDB.setLogin(userNew.getLogin());
        }
        if (!userNew.getEmail().equals(userFromDB.getEmail())) {
            userFromDB.setEmail(userNew.getEmail());
        }
        if (userNew.getPassword() != "") userFromDB.setPassword(bCryptPasswordEncoder.encode(userNew.getPassword()));

        return userDao.save(userFromDB);
    }

    private String getTemporaryPassword(){
        StringBuilder password=new StringBuilder();
        new Random().ints(8, 1,10)
                .forEach(el -> password.append(el));
        return password.toString();
    }
}
