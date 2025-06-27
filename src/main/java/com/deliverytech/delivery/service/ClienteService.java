package com.deliverytech.delivery.service;

import org.springframework.stereotype.Service;

import com.deliverytech.delivery.entity.ClienteEntity;
import com.deliverytech.delivery.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteEntity cadastrar(ClienteEntity cliente) {

        return clienteRepository.save(cliente);
    }

}