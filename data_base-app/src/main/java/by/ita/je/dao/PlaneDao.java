package by.ita.je.dao;

import by.ita.je.model.Plane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneDao extends CrudRepository<Plane, Long> {
}
