package by.ita.je.service;

import by.ita.je.dao.ClientDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Client;
import by.ita.je.service.api.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Override
    public Client save(Client client) throws NotCorrectData {
        if(client.getFirstName()=="" || client.getFirstName()==null) throw new NotCorrectData("Client");
        if(client.getSecondName()=="" || client.getSecondName()==null) throw new NotCorrectData("Client");
        return clientDao.save(client);
    }

    @Override
    public Client update(Long id, Client clientNew) {
        Client client = clientDao.findById(id)
                .orElseThrow(() -> new NotFoundData( "Client"));
        System.out.println(clientNew);
        if(clientNew.getFirstName()!="") client.setFirstName(clientNew.getFirstName());
        if(clientNew.getSecondName()!="") client.setSecondName(clientNew.getSecondName());
        if(clientNew.getPhoneNumber()!=0) client.setPhoneNumber(clientNew.getPhoneNumber());
        return clientDao.save(client);
    }

    @Override
    public Client readById(Long id) throws NotFoundData {
        final Client client=clientDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Client"));
        return client;
    }

//    @Override
//    public List<Client> readAll() {
//        final Spliterator<Client> result = clientDao.findAll().spliterator();
//        return StreamSupport
//                .stream(result, false)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteById(Long id) throws NotFoundData {
//        try {
//            clientDao.deleteById(id);
//        }catch (Exception e){
//            throw new NotFoundData("Client");
//        }
//    }
}
