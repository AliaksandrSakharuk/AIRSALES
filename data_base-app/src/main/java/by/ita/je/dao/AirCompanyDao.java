package by.ita.je.dao;

import by.ita.je.model.AirCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirCompanyDao extends JpaRepository<AirCompany, Long> {
}
