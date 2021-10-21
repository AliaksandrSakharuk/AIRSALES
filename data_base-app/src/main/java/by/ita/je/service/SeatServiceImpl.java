package by.ita.je.service;

import by.ita.je.dao.SeatDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Seat;
import by.ita.je.service.api.SeatSericve;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SeatServiceImpl implements SeatSericve {

    @Autowired
    private final SeatDao seatDao;

//    @Override
//    public Seat save(Seat seat) throws NotCorrectData {
//        if(seat.getNumberSeat()=="") throw new NotCorrectData("Seat");
//        return seatDao.save(seat);
//    }

    @Override
    public Seat update(Long id, Seat seatNew) throws NotFoundData {
        Seat seat = seatDao.findById(id)
                .orElseThrow(() -> new NotFoundData( "Seat"));
        if(seat.isBooked()!=seatNew.isBooked()) seat.setBooked(seatNew.isBooked());
        return seatDao.save(seat);
    }

    @Override
    public Seat readById(Long id) throws NotFoundData {
        final Seat seat=seatDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Seat"));
        return seat;
    }

//    @Override
//    public void deleteById(Long id) throws NotFoundData {
//        try {
//            seatDao.deleteById(id);
//        }catch (Exception e){
//            throw new NotFoundData("Seat");
//        }
//    }
}
