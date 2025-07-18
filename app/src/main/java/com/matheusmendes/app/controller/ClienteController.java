package com.matheusmendes.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheusmendes.app.dto.ClienteDTO;
import com.matheusmendes.app.services.ClienteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "clients")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping()
    public Page<ClienteDTO> findAll(Pageable p) {
        Page<ClienteDTO> result = service.findAll(p);
        return result;
    }

}
