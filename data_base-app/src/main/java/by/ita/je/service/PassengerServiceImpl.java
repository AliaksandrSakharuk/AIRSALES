package by.ita.je.service;

import by.ita.je.dao.PassengerDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.model.Passenger;
import by.ita.je.service.api.PassengerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private final PassengerDao passengerDao;

    @Override
    public Passenger savePassenger(Passenger passenger) {
        if(passenger.getFirstName()==null || passenger.getFirstName()=="") throw new NotCorrectData("Passnger");
        if(passenger.getSecondName()==null || passenger.getSecondName()=="") throw new NotCorrectData("Passenger");
        if(passenger.getPhoneNumber()==0) throw new NotCorrectData("Passnger");
        if(passenger.getPassportNumber()==null || passenger.getPassportNumber()=="") throw new NotCorrectData("Passnger");
        return passengerDao.save(passenger);
    }
}
