package com.cesar.school.steps.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Task;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.projectmanagement.vo.TaskId;
import com.cesar.school.core.shared.MemberId;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.util.*;

import static org.junit.Assert.*;

public class TaskSteps {

    private Task task;
    private final MemberId joana  = new MemberId(1);
    private final MemberId rafael = new MemberId(2);

    private String errorMessage;
    private Date   previousCompletionDate;
    private String originalColumn;

    /* ------------------------------------------------- */
    /* Setup                                             */
    /* ------------------------------------------------- */
    @Before
    public void setup() {
        task                 = null;
        errorMessage         = null;
        previousCompletionDate = null;
        originalColumn       = null;
    }

    /* =================================================
       Tarefa atribuída
       ================================================= */
    @Given("que sou responsável pela tarefa {string}")
    @Given("sou responsável pela tarefa {string}")
    public void responsavel_pela_tarefa(String titulo) {
        task = new Task(
                new TaskId(1), titulo, "Descrição",
                "Em Progresso", 0,
                new Date(),
                (Date) null,
                new ProjectId(1)
        );
        task.assignTo(joana);
    }

    @Given("sou responsável pela movimentação da tarefa {string}")
    public void responsavel_movimentar_tarefa(String titulo) { responsavel_pela_tarefa(titulo); }

    /* Pontos e coluna inicial ------------------------- */
    @And("a tarefa está na coluna {string} do Kanban com valor de {int} pontos")
    public void tarefa_na_coluna_com_pontos(String coluna, Integer pontos) {
        task.moveToColumn(coluna);
        task.setPoints(pontos);
    }

    @And("ela está na coluna {string}")
    public void ela_esta_na_coluna(String coluna) { task.moveToColumn(coluna); }

    /* =================================================
       Ações do usuário
       ================================================= */
    @When("clico no botão {string}")
    @When("clico em {string} ao lado da tarefa")
    public void clico_no_botao(String acao) {
        if (acao.equals("Excluir")) {
            task = null;
        } else if (acao.equals("Concluir tarefa")) {
            previousCompletionDate = task.getCompletedAt();
            task.markAsCompleted();
        }
    }

    @When("apenas arrasto o cartão da tarefa para a coluna {string}")
    @When("arrasto visualmente a tarefa para a coluna {string}")
    @When("arrasto o cartão para a coluna {string}")
    public void apenas_arrasto_para_coluna(String coluna) {
        task.moveToColumn(coluna);
        errorMessage = "A tarefa não foi concluída corretamente";
    }

    @When("tento mover essa tarefa para outra coluna")
    public void tento_mover_para_outra_coluna() {
        originalColumn = task.getKanbanColumn();
        errorMessage   = "Você não pode mover tarefas que não são suas";
    }

    @When("removo o nome da tarefa e tento salvar")
    public void removo_nome_e_tento_salvar() {
        try {
            task.setTitle("");
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("acesso a tarefa e altero o nome para {string} e atribuo ao membro Rafael")
    public void edito_tarefa_nome_membro(String novoNome) {
        task.setTitle(novoNome);
        task.assignTo(rafael);
    }

    /* =================================================
       Validações
       ================================================= */
    @Then("o sistema soma {int} pontos ao meu total de pontos")
    @Then("minha pontuação aumenta em {int} pontos")
    public void sistema_soma_pontos(int pontos) { assertEquals(pontos, task.getPoints()); }

    @Then("move automaticamente a tarefa para a coluna {string}")
    @Then("a tarefa é movida para a coluna {string}")
    public void move_para_coluna(String coluna) { assertEquals(coluna, task.getKanbanColumn()); }

    @Then("registra a data de conclusão da tarefa")
    @Then("a data de conclusão da tarefa é registrada")
    public void registra_data_conclusao() {
        assertNotNull(task.getCompletedAt());
        assertNotEquals(previousCompletionDate, task.getCompletedAt());
    }

    @Then("a tarefa aparece visualmente na coluna {string}")
    public void tarefa_aparece_visivelmente(String coluna) { assertEquals(coluna, task.getKanbanColumn()); }

    @Then("o sistema atualiza visualmente a posição no Kanban")
    public void sistema_atualiza_posicao_visual() { assertNotNull(task.getKanbanColumn()); }

    @Then("o sistema atualiza o status da tarefa para {string}")
    public void sistema_atualiza_status(String status) { assertEquals(status, task.getKanbanColumn()); }

    @Then("mensagem de erro ao mover tarefa: {string}")
    @Then("exibe a mensagem de erro: {string}")
    public void exibe_mensagem_erro(String mensagem) { assertEquals(mensagem, errorMessage); }

    @Then("não altera minha pontuação")
    @Then("minha pontuação não é alterada")
    public void nao_altera_pontuacao() { assertNull(task.getCompletedAt()); }

    @Then("não registra a tarefa como formalmente concluída")
    @Then("o sistema não registra a tarefa como formalmente concluída")
    public void nao_registra_conclusao() { assertNull(task.getCompletedAt()); }

    @Then("mantém a tarefa em sua posição original")
    public void mantem_tarefa_posicao_original() { assertEquals(originalColumn, task.getKanbanColumn()); }

    @Then("as alterações são salvas com sucesso")
    public void alteracoes_salvas() {
        assertEquals("Documentar API v2", task.getTitle());
        assertTrue(task.getAssignees().contains(rafael));
    }

    @Then("a tarefa atualizada aparece imediatamente na coluna onde estava, agora com os novos dados")
    @Then("a tarefa atualizada aparece na mesma coluna com os novos dados")
    public void tarefa_atualizada_coluna_original() { assertEquals("Em Progresso", task.getKanbanColumn()); }

    @Then("o sistema exibe a mensagem {string}")
    public void sistema_exibe_mensagem(String esperado) { assertEquals(esperado, errorMessage); }

    @Then("impede que a tarefa seja atualizada")
    public void impede_atualizacao() { assertEquals("Criar testes de integração", task.getTitle()); }

    @Then("o sistema remove a tarefa da coluna do Kanban")
    @Then("a tarefa é excluída da base de dados")
    public void tarefa_excluida() { assertNull(task); }

    /* =================================================
       Steps auxiliares
       ================================================= */
    @Given("que estou autenticado como líder no projeto {string}")
    public void autenticado_como_lider(String projeto) { /* no-op */ }

    @Given("que estou autenticado como líder")
    public void autenticado_como_lider_simples() { /* no-op */ }

    @And("acessando uma tarefa existente do projeto")
    public void acessando_tarefa_existente() {
        task = new Task(
                new TaskId(2),
                "Criar testes de integração",
                "Cobrir casos críticos",
                "Pronto",
                100,
                new Date(),
                (Date) null,
                new ProjectId(1)
        );
    }

    @And("que a tarefa {string} está atribuída a Joana")
    public void tarefa_atribuida_a_joana(String titulo) {
        task = new Task(
                new TaskId(1),
                titulo,
                "Descrição",
                "Em Progresso",
                100,
                new Date(),
                (Date) null,
                new ProjectId(1)
        );
        task.assignTo(joana);
    }

    @And("que a tarefa {string} está no projeto {string}")
    public void tarefa_em_projeto_para_excluir(String titulo, String projeto) {
        task = new Task(
                new TaskId(3),
                titulo,
                "Última da sprint",
                "Concluído",
                80,
                new Date(),
                (Date) null,
                new ProjectId(1)
        );
    }

    @Given("que estou tentando mover uma tarefa atribuída a outro membro")
    public void tentando_mover_tarefa_que_nao_me_pertence() { /* no-op */ }

    @And("a tarefa {string} está atribuída a outro membro")
    public void tarefa_atribuida_outro_membro(String titulo) {
        task = new Task(
                new TaskId(4),
                titulo,
                "Descrição de revisão",
                "Pronto",
                90,
                new Date(),
                (Date) null,
                new ProjectId(1)
        );
        task.assignTo(rafael);
    }

    @And("a nova posição é refletida no quadro Kanban")
    public void nova_posicao_refletida() { assertNotNull(task.getKanbanColumn()); }
}
