package com.cesar.school.steps.gamification;

import com.cesar.school.application.gamification.RewardServiceImpl;
import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;
import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.repository.RewardRepository;
import com.cesar.school.core.gamification.service.RewardService;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.gamification.vo.RewardType;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import com.cesar.school.core.teamsmembers.service.MemberService;
import com.cesar.school.core.teamsmembers.vo.Role;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.util.*;

import static org.junit.Assert.*;

public class RewardSteps {

    private RewardService rewardService;
    private FakeRewardRepository fakeRewardRepository;
    private FakeMemberRepository fakeMemberRepository;
    private MemberService memberService;
    private Reward reward;
    private Member member;
    private String errorMessage;

    @Before
    public void setup() {
        fakeRewardRepository = new FakeRewardRepository();
        rewardService = new RewardServiceImpl(fakeRewardRepository);

        fakeMemberRepository = new FakeMemberRepository();
        memberService = new MemberTeamServiceImpl(null, fakeMemberRepository);

        member = new Member(new MemberId(1), "Joana", "joana@email.com", "123", Role.DEV);
        fakeMemberRepository.save(member);
        errorMessage = null;
    }

    // ================================
    // Feature: Cadastrar tipos de recompensas
    // ================================

    @Given("que estou na aba “Recompensas” e clico em “Nova recompensa”")
    public void i_am_on_reward_tab_and_click_new() {
        // No-op
    }

    @When("preencho nome, descrição e pontuação necessária")
    public void i_fill_name_description_and_points() {
        reward = new Reward(new RewardId(1), "Cupom R$ 50", 10000, RewardType.CUPOM, new MemberId(1));
        rewardService.createReward(reward);
    }

    @Then("a recompensa é salva no sistema")
    public void reward_is_saved() {
        assertTrue(fakeRewardRepository.getAll().contains(reward));
    }

    @And("aparece disponível para os membros da equipe")
    public void reward_is_available() {
        List<Reward> available = rewardService.getRewardsAvailableForPoints(10000);
        assertTrue(available.contains(reward));
    }

    @Given("que estou adicionando uma nova recompensa")
    public void i_am_adding_a_new_reward() {
        // No-op
    }

    @When("informo uma pontuação menor ou igual a zero")
    public void i_input_zero_or_negative_points() {
        try {
            rewardService.createReward(new Reward(new RewardId(2), "Cupom Inválido", 0, RewardType.CUPOM, new MemberId(1)));
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("o sistema rejeita o cadastro")
    public void system_rejects_creation() {
        assertNotNull(errorMessage);
    }

    @And("exibe a mensagem de erro: “A pontuação mínima deve ser maior que zero”")
    public void shows_error_invalid_points() {
        assertEquals("Required points must be greater than zero.", errorMessage);
    }

    // ================================
    // Feature: Desbloquear recompensas por pontuação
    // ================================

    @Given("que meu total de pontos atual é {int}")
    public void my_total_points_is(Integer pontos) {
        member.addPoints(pontos);
    }

    @When("eu concluo uma tarefa de {int} pontos")
    public void i_complete_task_with_points(Integer pontos) {
        reward = new Reward(new RewardId(3), "Cupom R$ 50", 10000, RewardType.CUPOM, new MemberId(99));
        memberService.addPoints(member.getId(), pontos);
        if (member.getIndividualScore() >= reward.getRequiredPoints()) {
            memberService.unlockReward(member.getId(), reward);
        }
    }

    @Then("meu total de pontos é atualizado para {int}")
    public void my_points_is_updated_to(Integer esperado) {
        assertEquals((int) esperado, member.getIndividualScore());
    }

    @And("a recompensa “Cupom R$ {int}” com requisito de {int} pontos aparece como desbloqueada no meu perfil")
    public void reward_is_unlocked(Integer valorMonetario, Integer pontosNecessarios) {
        assertTrue(member.getUnlockedRewardIds().contains(reward.getId()));
        assertEquals((int) pontosNecessarios, reward.getRequiredPoints());
    }

    @Given("que tenho {int} pontos acumulados")
    public void i_have_accumulated_points(Integer pontos) {
        member.addPoints(pontos);
    }

    @And("a próxima recompensa requer {int} pontos")
    public void next_reward_requires_points(Integer pontos) {
        reward = new Reward(new RewardId(4), "Cupom R$ 100", pontos, RewardType.CUPOM, new MemberId(99));
    }

    @When("visualizo a seção de recompensas")
    public void i_view_rewards_section() {
        // No-op
    }

    @Then("a recompensa permanece bloqueada")
    public void reward_remains_locked() {
        assertFalse(member.getUnlockedRewardIds().contains(reward.getId()));
    }

    @And("não aparece como conquistada no meu perfil")
    public void reward_not_appearing_as_unlocked() {
        assertFalse(member.getUnlockedRewardIds().contains(reward.getId()));
    }

    // ================================
    // Fake repositories
    // ================================

    private static class FakeRewardRepository implements RewardRepository {
        private final List<Reward> rewards = new ArrayList<>();

        @Override
        public void save(Reward reward) {
            rewards.add(reward);
        }

        @Override
        public List<Reward> findAll() {
            return new ArrayList<>(rewards);
        }

        @Override
        public List<Reward> findRewardsAvailableForPoints(int points) {
            return rewards.stream().filter(r -> r.getRequiredPoints() <= points).toList();
        }

        @Override
        public Optional<Reward> findById(RewardId id) {
            return rewards.stream().filter(r -> r.getId().equals(id)).findFirst();
        }

        @Override
        public void deleteById(RewardId id) {
            rewards.removeIf(r -> r.getId().equals(id));
        }

        public List<Reward> getAll() {
            return rewards;
        }
    }

    private static class FakeMemberRepository implements MemberRepository {
        private final Map<MemberId, Member> db = new HashMap<>();

        @Override
        public void save(Member member) {
            db.put(member.getId(), member);
        }

        @Override
        public Optional<Member> findById(MemberId id) {
            return Optional.ofNullable(db.get(id));
        }

        @Override
        public void delete(Member member) {
            db.remove(member.getId());
        }
    }
}
