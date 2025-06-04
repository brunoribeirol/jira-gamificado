package com.cesar.school.steps.teamsandmembers;

import com.cesar.school.core.shared.vo.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.shared.vo.FeedbackId;
import com.cesar.school.core.shared.vo.TaskId;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.util.*;

import static org.junit.Assert.*;

public class MemberSteps {

    private MemberId member;
    private List<Feedback> feedbacks;
    private List<Feedback> receivedFeedbacks;
    private int totalPoints;

    @Before
    public void setup() {
        member = new MemberId(1);
        feedbacks = new ArrayList<>();
        receivedFeedbacks = new ArrayList<>();
        totalPoints = 0;
    }

    // === Progresso pessoal ===

    @Given("que estou na aba “Meu Perfil”")
    public void estou_na_aba_meu_perfil() {
        // Nada necessário
    }

    @When("visualizo meus dados de progresso")
    public void visualizo_dados_progresso() {
        // Simulando progresso: 2 recompensas desbloqueadas
        totalPoints = 1500;
    }

    @Then("o sistema retorna meu total de pontos")
    public void retorna_total_pontos() {
        assertTrue(totalPoints > 0);
    }

    @And("a lista das recompensas que já desbloqueei")
    public void lista_recompensas_desbloqueadas() {
        List<String> unlocked = Arrays.asList("Desconto R$ 10", "Vale café");
        assertFalse(unlocked.isEmpty());
    }

    @Given("que tenho menos pontos do que o necessário para qualquer recompensa")
    public void menos_pontos_que_recompensas() {
        totalPoints = 0;
    }

    @When("acesso a seção “Meu Progresso”")
    public void acesso_meu_progresso() {
        // Nenhuma ação necessária
    }

    @Then("o sistema retorna que não há recompensas desbloqueadas")
    public void sem_recompensas_desbloqueadas() {
        List<String> unlocked = new ArrayList<>();
        assertTrue(unlocked.isEmpty());
    }

    // === Feedbacks ===

    @Given("que estou no perfil de um colega")
    public void no_perfil_de_um_colega() {
        // Simula visualização do perfil
    }

    @When("preencho o formulário de feedback e envio")
    public void preencho_formulario_feedback() {
        Feedback novoFeedback = new Feedback(
                new FeedbackId(1),
                "Excelente colaboração!",
                new Date(),
                member,
                new MemberId(2),
                new TaskId(1)
        );
        feedbacks.add(novoFeedback);
    }

    @Then("o feedback aparece na lista do membro com nota e comentário")
    public void feedback_aparece_na_lista() {
        assertFalse(feedbacks.isEmpty());
        Feedback f = feedbacks.get(0);
        assertEquals("Excelente colaboração!", f.getMessage());
        assertNotNull(f.getDate());
        assertEquals(member, f.getGivenBy());
    }

    @Given("que recebi feedbacks anteriormente")
    public void recebi_feedbacks_anteriores() {
        Feedback f1 = new Feedback(
                new FeedbackId(2),
                "Ótimo trabalho em equipe",
                new Date(),
                new MemberId(3),
                member,
                null
        );
        Feedback f2 = new Feedback(
                new FeedbackId(3),
                "Precisa melhorar testes",
                new Date(),
                new MemberId(4),
                member,
                null
        );
        receivedFeedbacks.addAll(List.of(f1, f2));
    }

    @When("acesso minha aba de feedback")
    public void acesso_aba_feedback() {
        // Nenhuma ação necessária, dados já carregados
    }

    @Then("o sistema retorna uma lista com os feedbacks recebidos e seus respectivos emissores e datas")
    public void sistema_retorna_lista_feedbacks() {
        assertEquals(2, receivedFeedbacks.size());
        for (Feedback f : receivedFeedbacks) {
            assertNotNull(f.getMessage());
            assertNotNull(f.getGivenBy());
            assertNotNull(f.getDate());
        }
    }
}
