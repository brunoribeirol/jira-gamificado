package com.cesar.school.steps.projectmanagement;

import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.projectmanagement.vo.ChallengeId;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.shared.MemberId;
import io.cucumber.java.en.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ChallengeSteps {

    private Challenge challenge;
    private String errorMessage;

    private final MemberId leaderId = new MemberId(1);
    private final ProjectId projectId = new ProjectId(1);

    @When("preencho o nome {string}, critério {string} e prazo para {string}")
    public void preencho_dados_do_desafio(String title, String criteria, String deadlineStr) {
        try {
            Date deadline = new SimpleDateFormat("dd/MM/yyyy").parse(deadlineStr);
            challenge = new Challenge(
                    new ChallengeId(1),
                    title,
                    "Desafio extra criado via BDD",
                    criteria,
                    50,
                    leaderId,
                    projectId,
                    deadline
            );
        } catch (IllegalArgumentException | ParseException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("o desafio é criado com sucesso")
    public void desafio_criado_com_sucesso() {
        assertNotNull(challenge);
    }

    @Then("fica visível para todos os membros da equipe")
    public void visivel_para_membros() {
        assertEquals(projectId, challenge.getProjectId());
        assertEquals(leaderId, challenge.getCreatedBy());
    }

    @Given("que estou preenchendo um novo desafio")
    public void preenchendo_novo_desafio() {
        // inicialização de contexto — não faz nada diretamente
    }

    @When("deixo o campo de critério em branco")
    public void deixo_criterio_em_branco() {
        try {
            challenge = new Challenge(
                    new ChallengeId(2),
                    "Desafio sem critério",
                    "Descrição qualquer",
                    "",
                    30,
                    leaderId,
                    projectId,
                    new Date()
            );
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("o sistema exibe a mensagem de erro do critério: {string}")
    public void sistema_exibe_erro_criterio(String mensagemEsperada) {
        assertEquals(mensagemEsperada, errorMessage);
    }

    @Then("não permite salvar o desafio")
    public void nao_permite_salvar_desafio() {
        assertNull(challenge);
    }
}
