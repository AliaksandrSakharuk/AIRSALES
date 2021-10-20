package by.ita.je.service.api;

import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Plane;

public interface PlaneService {

//    Plane save(Plane plane) throws NotCorrectData;
//
//    Plane update(Long id, Plane plane) throws NotFoundData;

    Plane readById(Long id) throws NotFoundData;

//    void deleteById(Long id) throws NotFoundData;
//
//    Plane findPlaneByPlaneName(String namePlane);
}
