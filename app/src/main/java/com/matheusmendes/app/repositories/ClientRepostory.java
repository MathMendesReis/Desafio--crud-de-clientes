package com.matheusmendes.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheusmendes.app.entities.Client;

public interface ClientRepostory extends JpaRepository<Client,Long>{
    
}
