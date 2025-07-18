package com.matheusmendes.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheusmendes.app.dto.ClienteDTO;
import com.matheusmendes.app.services.ClienteService;

import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "clients")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> findAll(Pageable p) {
        Page<ClienteDTO> result = service.findAll(p);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findAll(@PathVariable Long id) {
        ClienteDTO result = service.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping()
    public ResponseEntity<ClienteDTO> save(@Valid @RequestBody ClienteDTO entity) {
        ClienteDTO result = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO entity) {
        ClienteDTO result = service.update(id,entity);
        return ResponseEntity.ok().body(result);
    }

}
