package by.ita.je.service;

import by.ita.je.dao.AirCompanyDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.AirCompany;
import by.ita.je.service.api.AirCompanyService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AirCompanyServiceImpl implements AirCompanyService {

    private final AirCompanyDao companyServiceDao;

    @Override
    public AirCompany save(AirCompany company) throws NotCorrectData {
        if (StringUtils.isEmpty(company.getNameCompany())) throw new NotCorrectData("AirCompany");
        return companyServiceDao.save(company);
    }

    @Override
    public AirCompany readById(Long id) throws NotFoundData {
        final AirCompany company = companyServiceDao.findById(id)
                .orElseThrow(() -> new NotFoundData("AirCompany"));
        return company;
    }

    @Override
    public List<AirCompany> readAll() {
        return companyServiceDao.findAll();
    }
}
