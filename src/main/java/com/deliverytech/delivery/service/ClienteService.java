package com.deliverytech.delivery.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deliverytech.delivery.entity.ClienteEntity;
import com.deliverytech.delivery.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteEntity cadastrar(ClienteEntity cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + cliente.getEmail());
        }
        validarDadosCliente(cliente);

        cliente.setAtivo(true);

        return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public Optional<ClienteEntity> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<ClienteEntity> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    /**
     * Listar todos os clientes ativos
     */
    @Transactional(readOnly = true)
    public List<ClienteEntity> listarAtivos() {
        return clienteRepository.findByAtivoTrue();
    }

    /**
     * Atualizar dados do cliente
     */
    public ClienteEntity atualizar(Long id, ClienteEntity clienteAtualizado) {
        ClienteEntity cliente = buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        // Verificar se email não está sendo usado por outro cliente
        if (!cliente.getEmail().equals(clienteAtualizado.getEmail()) &&
            clienteRepository.existsByEmail(clienteAtualizado.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + clienteAtualizado.getEmail());
        }

        // Atualizar campos
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        cliente.setEndereco(clienteAtualizado.getEndereco());

        return clienteRepository.save(cliente);
    }

    /**
     * Inativar cliente (soft delete)
     */
    public void inativar(Long id) {
        ClienteEntity cliente = buscarPorId(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado: " + id));

        cliente.inativar();
        clienteRepository.save(cliente);
    }

    /**
     * Buscar clientes por nome
     */
    @Transactional(readOnly = true)
    public List<ClienteEntity> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

     private void validarDadosCliente(ClienteEntity cliente) {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }

        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }

        if (cliente.getNome().length() < 2) {
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
    }

}