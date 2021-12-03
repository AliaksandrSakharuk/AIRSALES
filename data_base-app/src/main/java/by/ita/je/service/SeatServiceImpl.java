package by.ita.je.service;

import by.ita.je.dao.SeatDao;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Seat;
import by.ita.je.service.api.SeatSericve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatSericve {

    private final SeatDao seatDao;

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
}
