package com.software.DateLineS.service;

import com.software.DateLineS.data.Atendimento;
import com.software.DateLineS.data.AtendimentoRepository;
import com.software.DateLineS.data.Cliente;
import com.software.DateLineS.data.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    public List<Atendimento> listar() {
        return atendimentoRepo.findAll();
    }

    public Atendimento salvar(Atendimento atendimento, Integer clienteId) {
        Cliente cliente = clienteRepo.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        atendimento.setCliente(cliente);
        return atendimentoRepo.save(atendimento);
    }

    public Atendimento buscar(Integer id) {
        return atendimentoRepo.findById(id).orElse(null);
    }

    public void excluir(Integer id) {
        atendimentoRepo.deleteById(id);
    }
}
