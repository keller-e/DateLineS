package com.software.DateLineS.controller;

import com.software.DateLineS.data.Atendimento;
import com.software.DateLineS.service.AtendimentoService;
import com.software.DateLineS.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listar(@CookieValue(name = "tema", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("atendimentos", atendimentoService.listar());
        model.addAttribute("tema", tema);
        return "listaAgendamento";
    }

    @GetMapping("/novo")
    public String novo(@CookieValue(name = "tema", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("atendimento", new Atendimento());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("tema", tema);
        return "novoAgendamento";
    }

    @PostMapping("/gravar")
    public String gravar(@ModelAttribute Atendimento atendimento, @RequestParam(required = false) Integer clienteId) {
        if (clienteId == null && atendimento.getCliente() != null) {
            clienteId = atendimento.getCliente().getId();
        }
        if (clienteId == null) {
            throw new RuntimeException("Cliente não informado");
        }
        atendimentoService.salvar(atendimento, clienteId);
        return "redirect:/atendimentos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@CookieValue(name = "tema", defaultValue = "claro") String tema,
            @PathVariable Integer id, Model model) {
        Atendimento atendimento = atendimentoService.buscar(id);
        model.addAttribute("atendimento", atendimento);
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("tema", tema);
        return "editarAtendimento";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Integer id) {
        atendimentoService.excluir(id);
        return "redirect:/atendimentos";
    }
}
