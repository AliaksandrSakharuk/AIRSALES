package by.ita.je.service.api;

import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.AirCompany;
import java.util.List;
public interface AirCompanyService {

    AirCompany save(AirCompany company) throws NotCorrectData;

    AirCompany update(Long id, AirCompany company) throws NotFoundData;

    AirCompany readById(Long id) throws NotFoundData;

    List<AirCompany> readAll();

    void deleteById(Long id) throws NotFoundData;


}
