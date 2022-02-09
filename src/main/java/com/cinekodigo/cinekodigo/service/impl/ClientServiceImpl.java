package com.cinekodigo.cinekodigo.service.impl;
import java.util.List;

import com.cinekodigo.cinekodigo.entity.Client;
import com.cinekodigo.cinekodigo.repository.ClientRepository;
import com.cinekodigo.cinekodigo.service.ClientService;
import org.springframework.stereotype.Service;




@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        super();
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }
}
