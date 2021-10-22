package by.ita.je.service;

import by.ita.je.dao.PlaneDao;

import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Plane;
import by.ita.je.service.api.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    private final PlaneDao planeDao;

    @Override
    public Plane readById(Long id) throws NotFoundData{
        final Plane plane=planeDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Plane"));
        return plane;
    }
}
