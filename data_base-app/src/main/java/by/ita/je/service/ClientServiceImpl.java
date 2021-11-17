package by.ita.je.service;

import by.ita.je.dao.ClientDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Client;
import by.ita.je.service.api.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Override
    public Client save(Client client) throws NotCorrectData {
        if(client.getFirstName()==null || client.getFirstName()=="") throw new NotCorrectData("Client");
        if(client.getSecondName()==null || client.getSecondName()=="") throw new NotCorrectData("Client");

        return clientDao.save(client);
    }

    @Override
    public Client update(Long id, Client clientNew) {
        Client client = clientDao.findById(id)
                .orElseThrow(() -> new NotFoundData( "Client"));
        if(clientNew.getFirstName()!=null || clientNew.getFirstName()!="") client.setFirstName(clientNew.getFirstName());
        if(clientNew.getSecondName()!=null || clientNew.getSecondName()!="") client.setSecondName(clientNew.getSecondName());
        if(clientNew.getPhoneNumber()!=0) client.setPhoneNumber(clientNew.getPhoneNumber());
        return clientDao.save(client);
    }

    @Override
    public Client readById(Long id) throws NotFoundData {
        final Client client=clientDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Client"));
        return client;
    }
}
