package com.cesar.school.steps.projectmanagement;

import static org.junit.jupiter.api.Assertions.*;

import com.cesar.school.core.projectmanagement.entity.Challenge;
import com.cesar.school.core.projectmanagement.vo.ChallengeId;
import com.cesar.school.core.projectmanagement.vo.ProjectId;
import com.cesar.school.core.shared.MemberId;
import io.cucumber.java.pt.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChallengeCreateSteps {

    private Challenge desafio;
    private String erro;

    @Dado("que estou autenticado como líder no projeto {string}")
    public void estouAutenticadoComoLiderParaCriacao(String nomeProjeto) {
        // Lógica para autenticação
        assertNotNull(nomeProjeto, "O nome do projeto não pode ser nulo");
        // Autentica o líder no projeto
    }

    @Quando("preencho o nome {string}, critério {string} e prazo para {string}")
    public void preenchoNomeCriterioEPrazo(String nome, String criterio, String prazo) {
        try {
            Date prazoFormatado = new SimpleDateFormat("dd/MM/yyyy").parse(prazo);

            // Criando o desafio com os dados passados
            try {
                desafio = new Challenge(
                        new ChallengeId(1),
                        nome,
                        "Descrição padrão de desafio", // Se a descrição não for especificada, pode ser um valor default
                        criterio,
                        100, // Valor de pontos padrão
                        new MemberId(1), // Líder fictício
                        new ProjectId(1),
                        prazoFormatado
                );
            } catch (IllegalArgumentException e) {
                erro = e.getMessage(); // Captura da exceção se o critério estiver vazio
            }
        } catch (ParseException e) {
            fail("Erro ao converter data: " + prazo);
        }
    }

    @Então("o desafio é criado com sucesso")
    public void desafioCriadoComSucesso() {
        assertNotNull(desafio, "O desafio deveria ter sido criado");
    }

    @E("fica visível para todos os membros da equipe")
    public void desafioVisivelParaMembros() {
        assertNotNull(desafio, "O desafio deveria existir para ser visível");
        assertTrue(desafio.getTitle() != null && !desafio.getTitle().isEmpty(), "O título deveria estar preenchido");
    }

    @Dado("que estou preenchendo um novo desafio")
    public void estouPreenchendoNovoDesafio() {
        // Simula que o usuário está na tela de novo desafio
        desafio = null;
        erro = null;
    }

    @Quando("deixo o campo de critério em branco")
    public void deixoCampoDeCriterioEmBranco() {
        try {
            // Aqui o erro é lançado porque o critério está vazio
            desafio = new Challenge(
                    new ChallengeId(2),
                    "Desafio sem critério",
                    "Descrição de desafio",
                    "", // Critério vazio
                    100,
                    new MemberId(1),
                    new ProjectId(1),
                    new Date()
            );
        } catch (IllegalArgumentException e) {
            erro = e.getMessage(); // O erro esperado
        }
    }

    @Então("o sistema exibe a mensagem de erro do critério: {string}")
    public void sistemaExibeMensagemErroCriterio(String mensagemEsperada) {
        assertNotNull(erro, "Deveria ter ocorrido um erro ao deixar o critério em branco");
        assertEquals(mensagemEsperada, erro, "Mensagem de erro do critério incorreta");
    }

    @E("não permite salvar o desafio")
    public void naoPermiteSalvarDesafio() {
        assertNull(desafio, "O desafio deveria ser nulo, pois o critério está em branco");
    }
}
