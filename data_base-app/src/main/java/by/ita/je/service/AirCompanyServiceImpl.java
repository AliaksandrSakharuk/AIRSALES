package by.ita.je.service;

import by.ita.je.dao.AirCompanyDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.AirCompany;
import by.ita.je.model.Plane;
import by.ita.je.service.api.AirCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class AirCompanyServiceImpl implements AirCompanyService {

    @Autowired
    private AirCompanyDao companyServiceDao;

    @Override
    public AirCompany save(AirCompany company) throws NotCorrectData {
    if(company.getNameCompany()==null ||  company.getNameCompany()=="") throw new NotCorrectData("AirCompany");
        return companyServiceDao.save(company);
    }

//    @Override
//    public AirCompany update(Long id, AirCompany companyNew) throws NotFoundData {
//        AirCompany airCompany = companyServiceDao.findById(id)
//                .orElseThrow(() -> new NotFoundData( "AirCompany"));
//        if(companyNew.getNameCompany()!="") airCompany.setNameCompany(companyNew.getNameCompany());
//        if(companyNew.getPhoneNumber()!=0) airCompany.setPhoneNumber(companyNew.getPhoneNumber());
//        return companyServiceDao.save(airCompany);
//    }

    @Override
    public AirCompany readById(Long id) throws NotFoundData{
        final AirCompany company=companyServiceDao.findById(id)
                .orElseThrow(() -> new NotFoundData("AirCompany"));
        return company;
    }

    @Override
    public List<AirCompany> readAll() {
        final Spliterator<AirCompany> result = companyServiceDao.findAll().spliterator();
        return StreamSupport
                .stream(result, false)
                .collect(Collectors.toList());
    }

//    @Override
//    public void deleteById(Long id) throws NotFoundData{
//        try {
//            companyServiceDao.deleteById(id);
//        }catch (Exception e){
//            throw new NotFoundData("AirCompany");
//        }
//    }
}
