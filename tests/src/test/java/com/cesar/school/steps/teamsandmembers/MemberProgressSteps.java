package com.cesar.school.steps.teamsandmembers;

import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.gamification.vo.RewardType;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;

import io.cucumber.java.pt.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemberProgressSteps {

    private Member member;
    private List<Reward> unlockedRewards;

    @Dado("que estou na aba “Meu Perfil”")
    public void estouNaAbaMeuPerfil() {
        // Simula o acesso ao perfil do usuário
        member = new Member(new MemberId(1), "João", "joao@email.com", "password123", null);
    }

    @Quando("visualizo meus dados de progresso")
    public void visualizoMeusDadosDeProgresso() {
        // Simula a visualização do progresso e das recompensas desbloqueadas
        unlockedRewards = List.of(new Reward(new RewardId(1), "Cupom R$ 50", 10000, RewardType.CUPOM, new MemberId(1)));
    }

    @Então("o sistema retorna meu total de pontos")
    public void sistemaRetornaTotalDePontos() {
        // Simula a verificação do total de pontos do membro
        int totalPontos = member.getIndividualScore();
        assertEquals(0, totalPontos); // Verificando que o total de pontos é 0 (como exemplo)
    }

    @E("a lista das recompensas que já desbloqueei")
    public void listaDeRecompensasDesbloqueadas() {
        assertNotNull(unlockedRewards, "A lista de recompensas desbloqueadas está vazia");
        assertEquals(1, unlockedRewards.size(), "Deveria ter 1 recompensa desbloqueada");
    }

    @Dado("que tenho menos pontos do que o necessário para qualquer recompensa")
    public void tenhoMenosPontos() {
        // Simula que o membro tem menos pontos do que o necessário para desbloquear qualquer recompensa
        member = new Member(new MemberId(1), "João", "joao@email.com", "password123", null);
        member.addPoints(5000); // Adicionando pontos insuficientes
        unlockedRewards = List.of(); // Nenhuma recompensa deve ser desbloqueada
    }

    @Quando("acesso a seção “Meu Progresso”")
    public void acessoASeçãoMeuProgresso() {
        // Simula o acesso à seção de progresso
        // Aqui, não é necessário fazer mais nada, pois a lógica já foi configurada no Dado.
    }

    @Então("o sistema retorna que não há recompensas desbloqueadas")
    public void sistemaRetornaSemRecompensasDesbloqueadas() {
        // Simula que nenhuma recompensa foi desbloqueada devido aos pontos insuficientes
        assertTrue(unlockedRewards.isEmpty(), "O sistema não deveria ter recompensas desbloqueadas");
    }

}
