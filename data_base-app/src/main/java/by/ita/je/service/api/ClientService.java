package by.ita.je.service.api;

import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Client;
import by.ita.je.model.Flight;

import java.util.List;

public interface ClientService {
    Client save(Client client) throws NotCorrectData;

    Client update(Long id, Client clientNew);

    Client readById(Long id) throws NotFoundData;

//    List <Client> readAll();
//
//    void deleteById(Long id) throws NotFoundData;
}
