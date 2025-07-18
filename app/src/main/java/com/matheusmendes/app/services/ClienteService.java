package com.matheusmendes.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.matheusmendes.app.dto.ClienteDTO;
import com.matheusmendes.app.entities.Client;
import com.matheusmendes.app.exceptions.ResourceNotFoundException;
import com.matheusmendes.app.repositories.ClientRepostory;

@Service
public class ClienteService {
    @Autowired
    private ClientRepostory repostory;

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAll(Pageable p) {
        Page<Client> clients = repostory.findAll(p);
        return clients.map(c -> new ClienteDTO(c.getId(), c.getName(), c.getCpf(), c.getIncome(), c.getBirthDate(),
                c.getChildren()));
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Client client = repostory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
        ClienteDTO dto = new ClienteDTO(client.getId(), client.getName(), client.getCpf(), client.getIncome(),
                client.getBirthDate(), client.getChildren());

        return dto;
    }
}