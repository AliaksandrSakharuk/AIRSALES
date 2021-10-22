package by.ita.je.service.api;

import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Plane;

public interface PlaneService {

    Plane readById(Long id) throws NotFoundData;
}
