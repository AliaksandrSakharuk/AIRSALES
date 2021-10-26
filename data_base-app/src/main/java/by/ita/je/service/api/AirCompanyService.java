package by.ita.je.service.api;

import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.AirCompany;
import by.ita.je.model.Passenger;

import java.util.List;
public interface AirCompanyService {

   public AirCompany save(AirCompany company) throws NotCorrectData;

    public AirCompany readById(Long id) throws NotFoundData;

    public List<AirCompany> readAll();
}
