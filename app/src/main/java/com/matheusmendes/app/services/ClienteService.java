package com.matheusmendes.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.matheusmendes.app.dto.ClienteDTO;
import com.matheusmendes.app.entities.Client;
import com.matheusmendes.app.repositories.ClientRepostory;

@Service
public class ClienteService {
    @Autowired
    private ClientRepostory repostory;

    public Page<ClienteDTO> findAll(Pageable p) {
        Page<Client> clients = repostory.findAll(p);
        return clients.map(c -> new ClienteDTO(c.getId(), c.getName(), c.getCpf(), c.getIncome(),c.getBirthDate(), c.getChildren()));
    }
}