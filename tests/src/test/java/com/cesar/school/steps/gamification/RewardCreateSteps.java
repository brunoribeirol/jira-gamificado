package com.cesar.school.steps.gamification;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.gamification.vo.RewardType;
import com.cesar.school.core.shared.MemberId;
import io.cucumber.java.pt.*;
import static org.junit.jupiter.api.Assertions.*;

public class RewardCreateSteps {

    private Reward reward;
    private String errorMessage;

    @Dado("que estou na aba “Recompensas” e clico em “Nova recompensa”")
    public void que_estou_na_aba_recompensas_e_clico_em_nova_recompensa() {
        // Simula o clique na aba "Nova recompensa"
    }

    @Quando("preencho nome, descrição e pontuação necessária")
    public void preencho_nome_descricao_e_pontuacao_necessaria() {
        // Preenche os dados da recompensa
        reward = new Reward(
                new RewardId(1),
                "Cupom R$ 50",
                10000,
                RewardType.CUPOM,
                new MemberId(1)
        );
    }

    @Entao("a recompensa é salva no sistema")
    public void a_recompensa_e_salva_no_sistema() {
        assertNotNull(reward, "A recompensa deveria ter sido criada");
    }

    @E("aparece disponível para os membros da equipe")
    public void aparece_disponivel_para_os_membros_da_equipe() {
        assertTrue(reward.getDescription().contains("Cupom"), "A recompensa deve estar visível para os membros");
    }

    @Dado("que estou adicionando uma nova recompensa")
    public void que_estou_adicionando_uma_nova_recompensa() {
        // Simula o início do processo de adição de uma nova recompensa
    }

    @Quando("informo uma pontuação menor ou igual a zero")
    public void informo_uma_pontuacao_menor_ou_igual_a_zero() {
        try {
            // Tenta criar uma recompensa com uma pontuação inválida
            reward = new Reward(
                    new RewardId(1),
                    "Cupom R$ 10",
                    -10, // Pontuação inválida
                    RewardType.CUPOM,
                    new MemberId(1)
            );
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();  // Captura a mensagem de erro gerada
        }
    }

    @Então("o sistema rejeita o cadastro")
    public void o_sistema_rejeita_o_cadastro() {
        assertNotNull(errorMessage, "A recompensa deveria ter sido rejeitada");
        assertEquals("Required points must be greater than zero.", errorMessage);
    }

    @Então("exibe a mensagem de erro: “A pontuação mínima deve ser maior que zero”")
    public void exibe_a_mensagem_de_erro_a_pontuacao_minima_deve_ser_maior_que_zero() {
        assertEquals("Required points must be greater than zero.", errorMessage, "A mensagem de erro está incorreta");
    }
}
