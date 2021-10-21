package by.ita.je.dao.impl;

import by.ita.je.dao.SearcherFlightByConditionDao;
import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SearcherFlightByConditionDaoImpl implements SearcherFlightByConditionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Flight> findFlight(FieldSearcherDto searcherDto) {

        LocalDateTime fromDate=searcherDto.getStartData().atStartOfDay();
        LocalDateTime toDate=fromDate.plusHours(24);

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> query=criteriaBuilder.createQuery(Flight.class);
        Root<Flight> root=query.from(Flight.class);

        query.select(root);
        query.where(criteriaBuilder.between(root.get("departureDateTime"), fromDate, toDate)
        ,criteriaBuilder.equal(root.get("departureCity"), searcherDto.getDepartureCity())
        ,criteriaBuilder.equal(root.get("arriveCity"), searcherDto.getArriveCity()));

        if(searcherDto.getNameCompany()!=null && searcherDto.getNameCompany()!=""){
        Join<Flight, Plane> planeJoin=root.join(Flight_.PLANE);
        Join<Plane, AirCompany> companyJoin=planeJoin.join(Plane_.COMPANY);
        query.select(root);
        query.where(criteriaBuilder.equal(companyJoin.get("nameCompany"), searcherDto.getNameCompany())
        ,criteriaBuilder.between(root.get("departureDateTime"), fromDate, toDate)
                ,criteriaBuilder.equal(root.get("departureCity"), searcherDto.getDepartureCity())
                ,criteriaBuilder.equal(root.get("arriveCity"), searcherDto.getArriveCity()));
        }
        TypedQuery<Flight> typedQuery = entityManager.createQuery(query);
        List<Flight> list = typedQuery.getResultList();

        return list;
    }
}
