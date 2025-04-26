package com.cesar.school.steps.teamsandmembers;

import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.vo.FeedbackId;
import io.cucumber.java.pt.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemberSteps {

    private Feedback feedback;
    private MemberId givenBy;
    private MemberId receivedBy;
    private List<Feedback> feedbackList;

    // Cenário 1: Enviar feedback para um membro
    @Dado("que estou no perfil de um colega")
    public void estouNoPerfilDeUmColega() {
        // Simula o perfil do colega sendo acessado
        givenBy = new MemberId(1);  // Quem está enviando o feedback
        receivedBy = new MemberId(2);  // O membro que irá receber o feedback
    }

    @Quando("preencho o formulário de feedback e envio")
    public void preenchoFormularioDeFeedback() {
        // Criação do feedback
        feedback = new Feedback(new FeedbackId(1), "Muito bom trabalho!", new Date(), givenBy, receivedBy, null);
    }

    @Entao("o feedback aparece na lista do membro com nota e comentário")
    public void feedbackApareceNaLista() {
        assertNotNull(feedback, "O feedback não foi criado");
        assertEquals("Muito bom trabalho!", feedback.getMessage());
        assertEquals(givenBy, feedback.getGivenBy());
        assertEquals(receivedBy, feedback.getReceivedBy());
    }

    // Cenário 2: Visualizar feedbacks recebidos
    @Dado("que recebi feedbacks anteriormente")
    public void recebiFeedbacksAnteriormente() {
        // Simulação de recebimento de feedback
        feedbackList = List.of(
                new Feedback(new FeedbackId(1), "Muito bom trabalho!", new Date(), new MemberId(1), new MemberId(2), null),
                new Feedback(new FeedbackId(2), "Excelente desempenho!", new Date(), new MemberId(3), new MemberId(2), null)
        );
    }

    @Quando("acesso minha aba de feedback")
    public void acessoMinhaAbaDeFeedback() {
        // Simula o acesso à aba de feedback
    }

    @Entao("o sistema retorna uma lista com os feedbacks recebidos e seus respectivos emissores e datas")
    public void sistemaRetornaListaDeFeedbacks() {
        assertNotNull(feedbackList, "A lista de feedbacks está vazia");
        assertTrue(feedbackList.size() > 0, "Nenhum feedback encontrado");
        assertEquals("Muito bom trabalho!", feedbackList.get(0).getMessage());
        assertEquals("Excelente desempenho!", feedbackList.get(1).getMessage());
    }
}
