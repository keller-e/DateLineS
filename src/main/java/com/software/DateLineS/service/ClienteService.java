package com.software.DateLineS.service;

import com.software.DateLineS.data.Cliente;
import com.software.DateLineS.data.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        return repo.save(cliente);
    }

    public Cliente buscar(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void excluir(Integer id) {
        repo.deleteById(id);
    }
}
