package by.ita.je.dao;

import by.ita.je.model.Role;
import by.ita.je.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findByRoleName (String roleName);
}
