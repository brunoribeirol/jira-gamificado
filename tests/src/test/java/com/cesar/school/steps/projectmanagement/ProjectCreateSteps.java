package com.cesar.school.steps.projectmanagement;

import static org.junit.jupiter.api.Assertions.*;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TeamId;
import io.cucumber.java.pt.*;

import java.util.List;

public class ProjectCreateSteps {

    private String nome;
    private String descricao;
    private Project projetoCriado;
    private Exception erro;
    private final List<String> colunasEsperadas = List.of("Backlog", "Pronto", "Em Progresso", "Revisão", "Concluído");

    @Dado("estou autenticado como líder da equipe {string}")
    public void estouAutenticadoComoLider(String equipe) {
        // Simulação simples de autenticação fictícia
        assertTrue(equipe != null && !equipe.isBlank(), "Nome da equipe deve ser informado");
    }

    @Quando("clico em “Novo Projeto” e preencho o nome {string} e a descrição {string}")
    public void preenchoDadosDoProjeto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        try {
            projetoCriado = new Project(new ProjectId(100), nome, descricao, new TeamId(200));
        } catch (Exception e) {
            erro = e;
        }
    }

    @Então("o sistema cria o projeto com as colunas padrão: {string}, {string}, {string}, {string}, {string}")
    public void sistemaCriaProjetoComColunasPadrao(String c1, String c2, String c3, String c4, String c5) {
        assertNotNull(projetoCriado, "Projeto não foi criado");
        assertEquals(List.of(c1, c2, c3, c4, c5), projetoCriado.getKanbanColumns(), "As colunas padrão não correspondem");
    }

    @E("o projeto aparece na lista de projetos da equipe")
    public void projetoApareceNaLista() {
        assertNotNull(projetoCriado, "Projeto não visível para a equipe");
        assertEquals(nome, projetoCriado.getName());
    }

    @Quando("tento criar um projeto sem preencher o campo de nome")
    public void criarProjetoComNomeVazio() {
        try {
            new Project(new ProjectId(101), "", "Qualquer coisa", new TeamId(201));
        } catch (Exception e) {
            this.erro = e;
        }
    }

    @Então("mensagem de erro ao criar projeto: {string}")
    public void sistemaExibeMensagemDeErro(String mensagemEsperada) {
        assertNotNull(erro, "Esperava uma exceção, mas nenhuma foi lançada");
        assertEquals(mensagemEsperada, erro.getMessage());
    }

    @E("não permite concluir o cadastro")
    public void naoPermiteCriarProjeto() {
        assertNotNull(erro, "Cadastro foi concluído indevidamente");
    }
}
