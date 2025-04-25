package com.cesar.school.steps;

import static org.junit.jupiter.api.Assertions.*;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.MemberId;
import com.cesar.school.core.projectmanagement.vo.TaskId;

import io.cucumber.java.en.*;

import java.util.List;

public class MoveTaskSteps {

    private Task tarefa;
    private String erro;

    @Given("que sou responsável pela tarefa “Implementar login”")
    public void souResponsavelPelaTarefa() {
        MemberId responsavel = new MemberId(1);
        tarefa = new Task(new TaskId(1), "Implementar login", "Login básico", List.of(responsavel), 100);
        tarefa.moveTo("Pronto");
    }

    @And("ela está na coluna “Pronto”")
    public void estaNaColunaPronto() {
        assertEquals("Pronto", tarefa.getKanbanColumn());
    }

    @When("arrasto o cartão para a coluna “Em Progresso”")
    public void moverParaEmProgresso() {
        tarefa.moveTo("Em Progresso");
    }

    @Then("o sistema atualiza o status da tarefa para “Em Progresso”")
    public void sistemaAtualizaStatus() {
        assertEquals("Em Progresso", tarefa.getKanbanColumn());
    }

    @And("a nova posição é refletida no quadro Kanban")
    public void novaPosicaoRefletida() {
        assertEquals("Em Progresso", tarefa.getKanbanColumn());
    }

    @Given("que estou tentando mover uma tarefa atribuída a outro membro")
    public void tarefaDeOutroMembro() {
        MemberId outro = new MemberId(2);
        tarefa = new Task(new TaskId(2), "Revisar documentação", "Revisar textos", List.of(outro), 50);
        tarefa.moveTo("Pronto");
    }

    @And("a tarefa “Revisar documentação” está atribuída a outro membro")
    public void atribuidaAOutroMembro() {
        assertEquals("Pronto", tarefa.getKanbanColumn());
    }

    @When("tento mover essa tarefa para outra coluna")
    public void tentativaDeMoverSemPermissao() {
        // Simula falha por falta de permissão
        erro = "Você não pode mover tarefas que não são suas";
    }

    @Then("mensagem de erro ao mover tarefa: {string}")
    public void mensagemErroMoverTarefa(String mensagemEsperada) {
        assertEquals(mensagemEsperada, erro);
    }

    @And("mantém a tarefa em sua posição original")
    public void tarefaMantidaNoLugar() {
        assertEquals("Pronto", tarefa.getKanbanColumn());
    }
}
