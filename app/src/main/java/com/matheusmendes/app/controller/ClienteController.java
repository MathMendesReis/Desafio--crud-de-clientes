package com.matheusmendes.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheusmendes.app.dto.ClienteDTO;
import com.matheusmendes.app.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
