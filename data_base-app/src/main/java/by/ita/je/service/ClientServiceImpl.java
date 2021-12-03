package by.ita.je.service;

import by.ita.je.dao.ClientDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Client;
import by.ita.je.service.api.ClientService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Override
    public Client save(Client client) throws NotCorrectData {
        if(StringUtils.isEmpty(client.getFirstName())) throw new NotCorrectData("Client");
        if(StringUtils.isEmpty(client.getSecondName())) throw new NotCorrectData("Client");
        return clientDao.save(client);
    }

    @Override
    public Client update(Long id, Client clientNew) {
        Client client = clientDao.findById(id)
                .orElseThrow(() -> new NotFoundData( "Client"));
        if(StringUtils.isEmpty(client.getFirstName())) client.setFirstName(clientNew.getFirstName());
        if(StringUtils.isEmpty(client.getSecondName())) client.setSecondName(clientNew.getSecondName());
        if(clientNew.getPhoneNumber()!=null) client.setPhoneNumber(clientNew.getPhoneNumber());
        return clientDao.save(client);
    }

    @Override
    public Client readById(Long id) throws NotFoundData {
        return clientDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Client"));
    }
}
