package com.matheusmendes.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.matheusmendes.app.dto.ClienteDTO;
import com.matheusmendes.app.entities.Client;
import com.matheusmendes.app.exceptions.DatabaseExceptions;
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

    @Transactional
    public ClienteDTO save(ClienteDTO dto) {
        Client entity = new Client();

        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        ;
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity = repostory.save(entity);

        return new ClienteDTO(entity.getId(), entity.getName(), entity.getCpf(), entity.getIncome(),
                entity.getBirthDate(), entity.getChildren());
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) {

        Client entity = repostory.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        ;
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity = repostory.save(entity);

        return new ClienteDTO(entity.getId(), entity.getName(), entity.getCpf(), entity.getIncome(),
                entity.getBirthDate(), entity.getChildren());
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repostory.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repostory.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseExceptions("Falha na integridade referencial.");
        }
    }
}