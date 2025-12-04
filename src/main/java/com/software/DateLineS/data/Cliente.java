package com.software.DateLineS.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String dataNascimento;  // Mantendo o nome como no banco
    private String genero;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;

    private String doencaCronica;
    private String usoMedimento;
    private String alergia;
    private String cirurgia;
    private String condicaoDermatologica;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atendimento> atendimentos;
}
