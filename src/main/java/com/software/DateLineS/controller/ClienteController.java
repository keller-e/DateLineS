package com.software.DateLineS.controller;

import com.software.DateLineS.data.Cliente;
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

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listar(@CookieValue(name = "tema", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("tema", tema);
        return "clientes"; // seu template
    }

    @GetMapping("/novo")
    public String novo(@CookieValue(name = "tema", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("tema", tema);
        return "cadastrarCliente"; // seu template
    }

    @PostMapping("/gravar")
    public String gravar(@ModelAttribute Cliente cliente) {
        clienteService.salvar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@CookieValue(name = "tema", defaultValue = "claro") String tema,
            @PathVariable Integer id,
            Model model) {

        Cliente cliente = clienteService.buscar(id);

        model.addAttribute("cliente", cliente);
        model.addAttribute("tema", tema);

        return "editarCliente"; // reutilizando form
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Integer id) {
        clienteService.excluir(id);
        return "redirect:/clientes";
    }
}
