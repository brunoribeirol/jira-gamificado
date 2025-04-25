package com.cesar.school.steps;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.MemberId;
import com.cesar.school.core.projectmanagement.vo.TaskId;

import io.cucumber.java.en.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddTaskSteps {

    private String nome;
    private String descricao;
    private MemberId responsavel;
    private Task tarefaCriada;
    private Exception erro;

    @Given("estou criando uma tarefa no projeto")
    public void criandoTarefa() {
        // Inicialização se necessária
    }

    @When("preencho o nome {string}, a descrição {string} e atribuo ao membro {string}")
    public void preenchoCamposTarefa(String nome, String descricao, String responsavelNome) {
        this.nome = nome;
        this.descricao = descricao;
        this.responsavel = new MemberId(1); // Simulação do ID do membro "Joana"
        tarefaCriada = new Task(new TaskId(1), nome, descricao, List.of(responsavel), 300);
    }

    @Then("a tarefa é criada na coluna {string} do Kanban")
    public void verificaColuna(String colunaEsperada) {
        assertNotNull(tarefaCriada);
        assertEquals(colunaEsperada, tarefaCriada.getKanbanColumn());
    }

    @When("deixo o campo de responsável em branco")
    public void responsavelVazio() {
        try {
            new Task(new TaskId(2), "Nome", "Desc", List.of(), 200);
        } catch (Exception e) {
            this.erro = e;
        }
    }

    @Then("mensagem de erro ao adicionar tarefa: {string}")
    public void exibeErro(String mensagem) {
        assertNotNull(erro);
        assertEquals(mensagem, erro.getMessage());
    }

    @And("impede a criação da tarefa")
    public void impedeCriacao() {
        assertNotNull(erro);
    }

    @Given("estou no formulário de nova tarefa")
    public void formularioTarefa() {
        // Simulação de entrada no formulário
    }

    @When("deixo o campo de nome vazio")
    public void nomeVazio() {
        try {
            new Task(new TaskId(3), "", "Descrição qualquer", List.of(new MemberId(1)), 150);
        } catch (Exception e) {
            this.erro = e;
        }
    }

    @And("bloqueia o envio")
    public void bloqueiaEnvio() {
        assertNotNull(erro);
    }
}
