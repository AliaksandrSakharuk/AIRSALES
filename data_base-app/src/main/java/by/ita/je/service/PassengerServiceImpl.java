package by.ita.je.service;

import by.ita.je.dao.PassengerDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.model.Passenger;
import by.ita.je.service.api.PassengerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerDao passengerDao;

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerDao.save(passenger);
    }
}
