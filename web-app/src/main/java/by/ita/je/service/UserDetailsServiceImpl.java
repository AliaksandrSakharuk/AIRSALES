package by.ita.je.service;

import by.ita.je.configuration.UserDetail;
import by.ita.je.dao.UserDao;
import by.ita.je.dto.FieldUserDto;

import by.ita.je.excepetion.NotFoundData;
import by.ita.je.model.Role;
import by.ita.je.model.User;
import by.ita.je.service.api.MessageService;
import by.ita.je.service.api.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserService {

@Autowired
    private  UserDao userDao;
@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
    private MessageService messageService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user=userDao.findByLogin(login);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("The login: " + login + "not exist");
        }
        return new UserDetail(user);
    }

    @Override
    public boolean saveUser(User user) {
        User userFromDB = userDao.findByLogin(user.getLogin());
        if (userFromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role("READER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userDao.save(user);
        return true;
    }

    @Override
    public boolean renewalPassword(FieldUserDto fieldUserDto){
        User userFromDB = userDao.findByLogin(fieldUserDto.getLogin());
        if (userFromDB != null && userFromDB.getEmail().equals(fieldUserDto.getEmail())) {
            final String password =getTemporaryPassword();
            userFromDB.setPassword(bCryptPasswordEncoder.encode(password));
            userDao.save(userFromDB);
            messageService.sendMessage(password, fieldUserDto.getEmail());
            return true;
        }
        else {
            return false;
        }
    }

    public void userBlockAndUnBlockedEnabled(long id){
        User user=userDao.findById(id)
                .orElseThrow(() -> new NotFoundData("User"));
        if(user.isEnabled()) {
            user.setEnabled(false);
        }
        else{
            user.setEnabled(true);
        }
        userDao.save(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userDao.findByLogin(auth.getName());
    }

    @Override
    public User updateUser(Long id, User userNew) {
        User userFromDB = userDao.findById(id)
                .orElseThrow(() -> new NotFoundData( "User"));
        if(!userNew.getLogin().equals(userFromDB.getLogin())){
            userFromDB.setLogin(userNew.getLogin());
        }
       if (userNew.getPassword()!="") userFromDB.setPassword(bCryptPasswordEncoder.encode(userNew.getPassword()));

            return userDao.save(userFromDB);

    }




    private String getTemporaryPassword(){
        String[] elements={"dgd","ett","dgd","dff","d7g","4f2","3fd",
                "3rf","3fg","44f","d45","d80","34d","fd3","ef2",};
        StringBuilder password=new StringBuilder("777");
        for (int num=1;num<=3;num++){
            password.append(elements[(int)Math.random()*15]);
        }
        return password.toString();
    }

}
