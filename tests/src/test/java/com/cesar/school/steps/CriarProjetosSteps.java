package com.cesar.school.steps;

import static org.junit.jupiter.api.Assertions.*;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TeamId;

import io.cucumber.java.pt.*;

import java.util.List;

public class CriarProjetosSteps {

    private String nome;
    private String descricao;
    private Project projetoCriado;
    private Exception erro;
    private final List<String> colunasEsperadas = List.of("Backlog", "Pronto", "Em Progresso", "Revisão", "Concluído");

    @Dado("estou autenticado como líder da equipe {string}")
    public void estouAutenticadoComoLider(String equipe) {
        // Simula autenticação
    }

    @Quando("clico em “Novo Projeto” e preencho o nome {string} e a descrição {string}")
    public void preenchoDadosDoProjeto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        projetoCriado = new Project(new ProjectId(1), nome, descricao, new TeamId(100));
    }

    @Entao("o sistema cria o projeto com as colunas padrão: {string}, {string}, {string}, {string}, {string}")
    public void sistemaCriaProjetoComColunasPadrao(String a, String b, String c, String d, String e) {
        List<String> colunas = projetoCriado.getKanbanColumns();
        assertEquals(List.of(a, b, c, d, e), colunas);
    }

    @E("o projeto aparece na lista de projetos da equipe")
    public void projetoApareceNaLista() {
        assertNotNull(projetoCriado);
    }

    @Quando("tento criar um projeto sem preencher o campo de nome")
    public void criarProjetoComNomeVazio() {
        try {
            new Project(new ProjectId(2), "", "Qualquer coisa", new TeamId(200));
        } catch (Exception e) {
            this.erro = e;
        }
    }

    @Entao("o sistema exibe a mensagem {string}")
    public void sistemaExibeMensagemDeErro(String mensagem) {
        assertNotNull(erro);
        assertEquals(mensagem, erro.getMessage());
    }

    @E("não permite concluir o cadastro")
    public void naoPermiteCriarProjeto() {
        assertNotNull(erro);
    }
}
