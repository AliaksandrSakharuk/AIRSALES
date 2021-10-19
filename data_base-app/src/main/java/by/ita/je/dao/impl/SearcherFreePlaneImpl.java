package by.ita.je.dao.impl;

import by.ita.je.dao.SearcherFrePlane;
import by.ita.je.model.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.time.ZonedDateTime;
import java.util.List;

@Repository
public class SearcherFreePlaneImpl implements SearcherFrePlane {
    @PersistenceUnit
    private EntityManagerFactory emf;
    private EntityManager entityManager;

//    @Override
//    public Plane findFreePlane(ZonedDateTime fromDateTime, ZonedDateTime toDateTime) {
//
//        entityManager=emf.createEntityManager();
//        Query query= entityManager.createNamedQuery("FindPlane", Plane.class);
//        query.setParameter("from_date", fromDateTime);
//        query.setParameter("to_date",toDateTime);
//        List<Plane> list=query.getResultList();
//        Plane plane=list.get(0);
//        return plane;
//    }
}
