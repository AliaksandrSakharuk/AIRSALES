package by.ita.je.service;

import by.ita.je.dao.PlaneDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Plane;
import by.ita.je.service.api.FlightService;
import by.ita.je.service.api.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    PlaneDao planeDao;
    @Autowired
    FlightService flightService;

    @Override
    public Plane save(Plane plane) throws NotCorrectData{
        if(plane.getInvertorNumber()==0) throw new NotCorrectData("Plane");
        if(plane.getNamePlane()=="") throw new NotCorrectData("Plane");
        if(plane.getQuantityLines()* plane.getSeatsInLine()!= plane.getQuantitySeats()) throw new NotCorrectData("Plane");
        return planeDao.save(plane);
    }

    @Override
    public Plane update(Long id, Plane planeNew) throws NotFoundData{
        Plane plane = planeDao.findById(id)
                .orElseThrow(() -> new NotFoundData( "Plane"));
        if(planeNew.getNamePlane()!="") plane.setNamePlane(planeNew.getNamePlane());
        if(planeNew.getInvertorNumber()!=0) plane.setInvertorNumber(planeNew.getInvertorNumber());
        if(planeNew.getNamePilot()!="") plane.setNamePilot(planeNew.getNamePilot());
        if(planeNew.getQuantitySeats()!=0) plane.setQuantitySeats(planeNew.getQuantitySeats());
        if(planeNew.getQuantityLines()!=0) plane.setQuantityLines(planeNew.getQuantityLines());
        if(planeNew.getSeatsInLine()!=0) plane.setSeatsInLine(planeNew.getSeatsInLine());
        return planeDao.save(plane);

    }

    @Override
    public Plane readById(Long id) throws NotFoundData{
        final Plane plane=planeDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Plane"));

        return plane;
    }

    @Override
    public void deleteById(Long id) throws NotFoundData{
        try {
            planeDao.deleteById(id);
        }catch (Exception e){
            throw new NotFoundData("Plane");
        }
    }

    @Override
    public Plane findPlaneByPlaneName(String namePlane) {
        return planeDao.findPlaneByNamePlane(namePlane);
    }
}
