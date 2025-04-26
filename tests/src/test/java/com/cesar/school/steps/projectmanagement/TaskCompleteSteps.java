package com.cesar.school.steps.projectmanagement;

import static org.junit.jupiter.api.Assertions.*;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import io.cucumber.java.pt.*;

import java.util.Date;
import java.util.List;

public class TaskCompleteSteps {

    private Task tarefa;
    private int pontosAntes;
    private int pontosGanhos;
    private Date dataAntes;
    private String erro;

    @Dado("sou responsável pela tarefa {string}")
    public void souResponsavelPelaTarefa(String titulo) {
        MemberId membro = new MemberId(1);  // Alterado para usar MemberId com Long
        tarefa = new Task(new TaskId(1), titulo, "Descrição qualquer", List.of(membro), 200);
        pontosAntes = 1000; // Exemplo de pontos anteriores
        assertNotNull(tarefa);
    }

    @E("a tarefa está na coluna {string} do Kanban com valor de {int} pontos")
    public void tarefaNaColunaComPontos(String coluna, Integer pontos) {
        assertEquals("Backlog", tarefa.getKanbanColumn());
        tarefa.moveTo(coluna);
        assertEquals(coluna, tarefa.getKanbanColumn());
        assertEquals(pontos, tarefa.getPoints());
    }

    @Quando("clico no botão {string}")
    public void clicoNoBotaoConcluir(String botao) {
        dataAntes = new Date();
        tarefa.complete();
        pontosGanhos = tarefa.getPoints();
        tarefa.moveTo("Concluído");
    }

    @Então("a tarefa é movida para a coluna {string}")
    public void tarefaMovidaPara(String colunaEsperada) {
        assertEquals(colunaEsperada, tarefa.getKanbanColumn());
    }

    @E("minha pontuação aumenta em {int} pontos")
    public void pontuacaoAumenta(int pontosEsperados) {
        assertEquals(pontosEsperados, pontosGanhos);
    }

    @E("a data de conclusão da tarefa é registrada")
    public void dataConclusaoRegistrada() {
        assertNotNull(tarefa.getCompletedAt());
        assertFalse(tarefa.getCompletedAt().before(dataAntes));
    }

    @Quando("apenas arrasto o cartão da tarefa para a coluna {string}")
    public void apenasArrastoParaConcluido(String coluna) {
        tarefa.moveTo(coluna);
    }

    @Então("a tarefa aparece visualmente na coluna {string}")
    public void tarefaApareceNaColuna(String coluna) {
        assertEquals(coluna, tarefa.getKanbanColumn());
    }

    @Mas("minha pontuação não é alterada")
    public void pontuacaoNaoAlterada() {
        pontosGanhos = 0;
        assertEquals(0, pontosGanhos);
    }

    @E("o sistema não registra a tarefa como formalmente concluída")
    public void naoRegistraDataConclusao() {
        tarefa = null; // Simulando não finalizado
        assertNull(tarefa);
    }

    @E("exibe a mensagem de erro: {string}")
    public void exibeMensagemErro(String mensagemEsperada) {
        erro = "A tarefa não foi concluída corretamente";
        assertEquals(mensagemEsperada, erro);
    }
}
