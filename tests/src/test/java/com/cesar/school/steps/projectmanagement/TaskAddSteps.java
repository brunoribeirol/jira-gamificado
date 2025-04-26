package com.cesar.school.steps.projectmanagement;

import static org.junit.jupiter.api.Assertions.*;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import io.cucumber.java.pt.*;

import java.util.List;

public class TaskAddSteps {

    private Task tarefaCriada;
    private Exception erro;

    @Dado("estou criando uma tarefa no projeto")
    public void estouCriandoUmaTarefa() {
        tarefaCriada = null;
        erro = null;
    }

    @Quando("preencho o nome {string}, a descrição {string} e atribuo ao membro {string}")
    public void preenchoCampos(String nome, String descricao, String nomeResponsavel) {
        try {
            tarefaCriada = new Task(new TaskId(1), nome, descricao, List.of(new MemberId(1)), 300);  // Alterado para MemberId do módulo compartilhado
        } catch (Exception e) {
            erro = e;
        }
    }

    @Então("a tarefa é criada na coluna {string} do Kanban")
    public void verificaColuna(String colunaEsperada) {
        assertNotNull(tarefaCriada, "Tarefa deveria ser criada");
        assertEquals(colunaEsperada, tarefaCriada.getKanbanColumn());
    }

    @Quando("deixo o campo de responsável em branco")
    public void semResponsavel() {
        try {
            tarefaCriada = new Task(new TaskId(2), "Nome", "Descrição", List.of(), 200);
        } catch (Exception e) {
            erro = e;
        }
    }

    @Então("mensagem de erro ao adicionar tarefa: {string}")
    public void mensagemErro(String mensagemEsperada) {
        assertNotNull(erro, "Deveria lançar erro de falta de responsável");
        assertEquals(mensagemEsperada, erro.getMessage());
    }

    @E("impede a criação da tarefa")
    public void impedeCriacao() {
        assertNotNull(erro, "A tarefa deveria ter sido bloqueada");
    }

    @Dado("estou no formulário de nova tarefa")
    public void estouNoFormulario() {
        // apenas preparando contexto, sem lógica
    }

    @Quando("deixo o campo de nome vazio")
    public void semNome() {
        try {
            tarefaCriada = new Task(new TaskId(3), "", "Descrição", List.of(new MemberId(1)), 150);  // Alterado para MemberId do módulo compartilhado
        } catch (Exception e) {
            erro = e;
        }
    }

    @E("bloqueia o envio")
    public void bloqueiaEnvio() {
        assertNotNull(erro, "A tarefa sem nome deveria ser bloqueada");
    }
}
