package com.software.DateLineS.api;

import com.software.DateLineS.data.Atendimento;
import com.software.DateLineS.service.AtendimentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoApi {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping
    public List<Atendimento> listar() {
        return atendimentoService.listar();
    }

    @PostMapping("/{clienteId}")
    public Atendimento salvar(@PathVariable Integer clienteId, @RequestBody Atendimento atendimento) {
        return atendimentoService.salvar(atendimento, clienteId);
    }

    @GetMapping("/{id}")
    public Atendimento buscar(@PathVariable Integer id) {
        return atendimentoService.buscar(id);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        atendimentoService.excluir(id);
    }
}
