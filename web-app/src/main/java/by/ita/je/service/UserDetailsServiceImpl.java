package by.ita.je.service;

import by.ita.je.configuration.UserDetail;
import by.ita.je.dao.UserDao;
import by.ita.je.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user=userDao.findByLogin(login);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("The login: " + login + "not exist");
        }
        return new UserDetail(user);
    }
}
