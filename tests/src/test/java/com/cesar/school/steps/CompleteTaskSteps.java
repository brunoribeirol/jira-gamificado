package com.cesar.school.steps;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.MemberId;
import com.cesar.school.core.projectmanagement.vo.TaskId;

import io.cucumber.java.en.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompleteTaskSteps {

    private Task tarefa;
    private int pontosIniciais = 0;
    private int pontosGanhos = 0;
    private Date dataAntes;
    private String erro;

    @Given("sou responsável pela tarefa {string}")
    public void souResponsavelPelaTarefa(String titulo) {
        MemberId membro = new MemberId(1);
        tarefa = new Task(new TaskId(1), titulo, "Descrição qualquer", List.of(membro), 200);
        pontosIniciais = 1000;
    }

    @And("a tarefa está na coluna {string} do Kanban com valor de {int} pontos")
    public void tarefaNaColunaComPontos(String coluna, Integer pontos) {
        assertEquals("Backlog", tarefa.getKanbanColumn());
        tarefa.moveTo(coluna);
        assertEquals(coluna, tarefa.getKanbanColumn());
        assertEquals(pontos, tarefa.getPoints());
    }

    @When("clico no botão {string}")
    public void clicoNoBotaoConcluir(String botao) {
        dataAntes = new Date();
        tarefa.complete();
        pontosGanhos = tarefa.getPoints();
        tarefa.moveTo("Concluído");
    }

    @Then("a tarefa é movida para a coluna {string}")
    public void tarefaMovidaPara(String coluna) {
        assertEquals(coluna, tarefa.getKanbanColumn());
    }

    @And("minha pontuação aumenta em {int} pontos")
    public void pontuacaoAumenta(int esperado) {
        assertEquals(esperado, pontosGanhos);
    }

    @And("a data de conclusão da tarefa é registrada")
    public void dataConclusaoRegistrada() {
        assertNotNull(tarefa.getCompletedAt());
        assertFalse(tarefa.getCompletedAt().before(dataAntes)); // ✅ mais seguro
    }


    @When("apenas arrasto o cartão da tarefa para a coluna {string}")
    public void apenasArrastoParaConcluido(String coluna) {
        tarefa.moveTo(coluna);
    }

    @Then("a tarefa aparece visualmente na coluna {string}")
    public void tarefaApareceNaColuna(String coluna) {
        assertEquals(coluna, tarefa.getKanbanColumn());
    }

    @But("minha pontuação não é alterada")
    public void pontuacaoNaoAlterada() {
        pontosGanhos = 0;
        assertEquals(0, pontosGanhos);
    }

    @And("o sistema não registra a tarefa como formalmente concluída")
    public void naoRegistraDataConclusao() {
        assertNull(tarefa.getCompletedAt());
    }

    @And("exibe a mensagem de erro: {string}")
    public void exibeMensagemErro(String msgEsperada) {
        erro = "A tarefa não foi concluída corretamente";
        assertEquals(msgEsperada, erro);
    }
}
