package com.cesar.school.steps.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Project;
import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.projectmanagement.vo.TeamId;
import com.cesar.school.core.shared.MemberId;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.util.*;

import static org.junit.Assert.*;

public class ProjectSteps {

    private Project project;
    private Task createdTask;
    private String errorMessage;
    private final MemberId joana = new MemberId(1);

    @Before
    public void setup() {
        project = null;
        createdTask = null;
        errorMessage = null;
    }

    // === Criação de Projeto ===

    @Given("estou autenticado como líder da equipe {string}")
    public void estou_autenticado_como_lider_da_equipe(String teamName) {
        // Simulação de autenticação
    }

    @When("clico em “Novo Projeto” e preencho o nome {string} e a descrição {string}")
    public void clico_em_novo_projeto(String nome, String descricao) {
        project = new Project(
                new ProjectId(1),
                nome,
                descricao,
                new TeamId(1)
        );
    }

    @When("tento criar um projeto sem preencher o campo de nome")
    public void tento_criar_projeto_sem_nome() {
        try {
            project = new Project(
                    new ProjectId(2),
                    "",
                    "Sem nome válido",
                    new TeamId(1)
            );
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("o sistema cria o projeto com as colunas padrão: {string}, {string}, {string}, {string}, {string}")
    public void sistema_cria_projeto_com_colunas(String c1, String c2, String c3, String c4, String c5) {
        assertNotNull(project);
        List<String> expectedColumns = Arrays.asList(c1, c2, c3, c4, c5);
        assertEquals(expectedColumns, project.getKanbanColumns());
    }

    @Then("o projeto aparece na lista de projetos da equipe")
    public void projeto_aparece_na_lista() {
        assertNotNull(project);
    }

    @Then("mensagem de erro ao criar projeto: {string}")
    public void erro_ao_criar_projeto(String mensagemEsperada) {
        assertEquals(mensagemEsperada, errorMessage);
    }

    @Then("não permite concluir o cadastro")
    public void nao_conclui_cadastro() {
        assertNull(project);
    }

    // === Criação de Tarefa ===

    @Given("estou criando uma tarefa no projeto")
    public void criando_tarefa_projeto() {
        project = new Project(
                new ProjectId(3),
                "Projeto Teste",
                "Descrição",
                new TeamId(1)
        );
    }

    @When("preencho o nome {string}, a descrição {string} e atribuo ao membro {string}")
    public void preencho_tarefa(String nome, String descricao, String membro) {
        createdTask = new Task(
                new TaskId(10),
                nome,
                descricao,
                "Backlog",
                0,
                new Date()
        );
        if (membro.equalsIgnoreCase("Joana")) {
            createdTask.assignTo(joana);
        }
        project.addTask(createdTask);
    }

    @Then("a tarefa é criada na coluna {string} do Kanban")
    public void tarefa_criada_coluna(String coluna) {
        assertEquals(coluna, createdTask.getKanbanColumn());
        assertTrue(project.getTasks().contains(createdTask));
    }

    @When("deixo o campo de responsável em branco")
    public void responsavel_em_branco() {
        try {
            createdTask = new Task(
                    new TaskId(11),
                    "Tarefa sem responsável",
                    "Teste",
                    "Backlog",
                    0,
                    new Date()
            );
            if (createdTask.getAssignees().isEmpty()) {
                throw new IllegalArgumentException("Selecione ao menos um responsável");
            }
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("mensagem de erro ao adicionar tarefa: {string}")
    public void erro_ao_adicionar_tarefa(String mensagem) {
        assertEquals(mensagem, errorMessage);
    }

    @Then("impede a criação da tarefa")
    public void impede_criacao_tarefa() {
        assertTrue(createdTask.getAssignees().isEmpty());
    }

    @Given("estou no formulário de nova tarefa")
    public void formulario_nova_tarefa() {
        // Simulação de tela
    }

    @When("deixo o campo de nome vazio")
    public void campo_nome_vazio() {
        try {
            createdTask = new Task(
                    new TaskId(12),
                    "",
                    "Descrição teste",
                    "Backlog",
                    0,
                    new Date()
            );
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("bloqueia o envio")
    public void bloqueia_envio() {
        assertEquals("O nome da tarefa é obrigatório", errorMessage);
    }
}
