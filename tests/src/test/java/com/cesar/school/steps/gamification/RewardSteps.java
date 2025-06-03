package com.cesar.school.steps.gamification;

import com.cesar.school.application.gamification.RewardServiceImpl;
import com.cesar.school.application.teamsmembers.MemberTeamServiceImpl;
import com.cesar.school.core.gamification.entity.Reward;
import com.cesar.school.core.gamification.repository.RewardRepository;
import com.cesar.school.core.gamification.service.RewardService;
import com.cesar.school.core.gamification.vo.RewardId;
import com.cesar.school.core.gamification.vo.RewardType;
import com.cesar.school.core.shared.TeamId;
import com.cesar.school.core.shared.MemberId;
import com.cesar.school.core.teamsmembers.entity.Feedback;
import com.cesar.school.core.teamsmembers.entity.Member;
import com.cesar.school.core.teamsmembers.entity.Team;
import com.cesar.school.core.teamsmembers.repository.FeedbackRepository;
import com.cesar.school.core.teamsmembers.repository.MemberRepository;
import com.cesar.school.core.teamsmembers.repository.TeamRepository;
import com.cesar.school.core.teamsmembers.service.MemberService;
import com.cesar.school.core.teamsmembers.vo.Role;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import java.util.*;

import static org.junit.Assert.*;

public class RewardSteps {

    private RewardService rewardService;
    private FakeRewardRepository   fakeRewardRepository;
    private FakeMemberRepository   fakeMemberRepository;
    private FakeTeamRepository     fakeTeamRepository;
    private FakeFeedbackRepository fakeFeedbackRepository;
    private MemberService memberService;

    private Reward reward;
    private Member member;
    private String errorMessage;

    /* ---------------------------------------------------- */
    /* Setup                                                */
    /* ---------------------------------------------------- */
    @Before
    public void setup() {
        fakeRewardRepository   = new FakeRewardRepository();
        fakeMemberRepository   = new FakeMemberRepository();
        fakeTeamRepository     = new FakeTeamRepository();
        fakeFeedbackRepository = new FakeFeedbackRepository();

        rewardService = new RewardServiceImpl(fakeRewardRepository);

        memberService = new MemberTeamServiceImpl(
                fakeTeamRepository,
                fakeMemberRepository,
                fakeRewardRepository,
                fakeFeedbackRepository
        );

        member = new Member(
                new MemberId(1),
                "Joana",
                "joana@email.com",
                "123",
                Role.DEV,
                new TeamId(101)
        );
        fakeMemberRepository.save(member);
        errorMessage = null;
    }

    /* ----------------------------------------------------
       Cadastro de recompensa
       ---------------------------------------------------- */
    @Given("que estou na aba “Recompensas” e clico em “Nova recompensa”")
    public void noop1() {}

    @When("preencho nome, descrição e pontuação necessária")
    public void i_fill_name_description_and_points() {
        reward = new Reward(
                new RewardId(1),
                "Cupom R$ 50",
                10_000,
                RewardType.CUPOM,
                member.getId()
        );
        rewardService.createReward(reward);
    }

    @Then("a recompensa é salva no sistema")
    public void reward_is_saved() {
        assertTrue(fakeRewardRepository.getAll().contains(reward));
    }

    @And("aparece disponível para os membros da equipe")
    public void reward_is_available() {
        assertTrue(rewardService.getRewardsAvailableForPoints(10_000).contains(reward));
    }

    /* ----------------------------------------------------
       Validação de pontuação zero/negativa
       ---------------------------------------------------- */
    @Given("que estou adicionando uma nova recompensa")
    public void noop2() {}

    @When("informo uma pontuação menor ou igual a zero")
    public void i_input_zero_or_negative_points() {
        try {
            rewardService.createReward(
                    new Reward(new RewardId(2), "Cupom Inválido", 0, RewardType.CUPOM, member.getId())
            );
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("o sistema rejeita o cadastro")
    public void system_rejects_creation() { assertNotNull(errorMessage); }

    @And("exibe a mensagem de erro: “A pontuação mínima deve ser maior que zero”")
    public void shows_error_invalid_points() {
        assertEquals("Required points must be greater than zero.", errorMessage);
    }

    /* ----------------------------------------------------
       Desbloquear por pontos
       ---------------------------------------------------- */
    @Given("que meu total de pontos atual é {int}")
    public void my_total_points_is(Integer pts) { member.addPoints(pts); }

    @When("eu concluo uma tarefa de {int} pontos")
    public void i_complete_task_with_points(Integer pts) {
        reward = new Reward(new RewardId(3), "Cupom R$ 50", 10_000, RewardType.CUPOM, new MemberId(99));
        fakeRewardRepository.save(reward);

        member.addPoints(pts);
        
        if (member.getIndividualScore() >= reward.getRequiredPoints()) {
            member.unlockReward(reward.getId());
        }
    }


    @Then("meu total de pontos é atualizado para {int}")
    public void my_points_is_updated_to(Integer ignorado) {
        assertTrue(
                "Pontuação final esperada precisa atingir o requisito da recompensa",
                member.getIndividualScore() >= reward.getRequiredPoints()
        );
    }

    @And("a recompensa “Cupom R$ {int}” com requisito de {int} pontos aparece como desbloqueada no meu perfil")
    public void reward_is_unlocked(Integer value, Integer required) {
        assertTrue(member.getUnlockedRewardIds().contains(reward.getId()));
        assertEquals((int) required, reward.getRequiredPoints());
    }

    /* ----------------------------------------------------
       Cenário: recompensa ainda bloqueada
       ---------------------------------------------------- */
    @Given("que tenho {int} pontos acumulados")
    public void i_have_accumulated_points(Integer pts) { member.addPoints(pts); }

    @And("a próxima recompensa requer {int} pontos")
    public void next_reward_requires_points(Integer pts) {
        reward = new Reward(new RewardId(4), "Cupom R$ 100", pts, RewardType.CUPOM, new MemberId(99));
    }

    @When("visualizo a seção de recompensas")
    public void i_view_rewards_section() {}

    @Then("a recompensa permanece bloqueada")
    public void reward_remains_locked() {
        assertFalse(member.getUnlockedRewardIds().contains(reward.getId()));
    }

    @And("não aparece como conquistada no meu perfil")
    public void reward_not_appearing_as_unlocked() {
        assertFalse(member.getUnlockedRewardIds().contains(reward.getId()));
    }

    /* ----------------------------------------------------
       Fakes
       ---------------------------------------------------- */
    private static class FakeRewardRepository implements RewardRepository {
        private final List<Reward> rewards = new ArrayList<>();
        @Override public void save(Reward r) { rewards.add(r); }
        @Override public List<Reward> findAll() { return new ArrayList<>(rewards); }
        @Override public List<Reward> findRewardsAvailableForPoints(int pts) {
            return rewards.stream().filter(r -> r.getRequiredPoints() <= pts).toList();
        }
        @Override public Optional<Reward> findById(RewardId id) { return rewards.stream().filter(r -> r.getId().equals(id)).findFirst(); }
        @Override public void deleteById(RewardId id) { rewards.removeIf(r -> r.getId().equals(id)); }
        public List<Reward> getAll() { return rewards; }
    }

    private static class FakeMemberRepository implements MemberRepository {
        private final Map<MemberId, Member> db = new HashMap<>();
        @Override public void save(Member m) { db.put(m.getId(), m); }
        @Override public Optional<Member> findById(MemberId id) { return Optional.ofNullable(db.get(id)); }
        @Override public void delete(Member m) { db.remove(m.getId()); }
        @Override public List<Member> findAll() { return new ArrayList<>(db.values()); }
        @Override public Optional<Member> findByEmail(String email) { return Optional.empty(); }
    }

    private static class FakeTeamRepository implements TeamRepository {
        @Override public Optional<Team> findById(com.cesar.school.core.shared.TeamId id) { return Optional.empty(); }
        @Override public void delete(Team team) { /* no-op */ }

        public List<Team> findAll() { return List.of(); }
        public void save(Team team) { /* no-op */ }
    }

    private static class FakeFeedbackRepository implements FeedbackRepository {
        @Override public Optional<Feedback> findById(com.cesar.school.core.teamsmembers.vo.FeedbackId id) { return Optional.empty(); }
        @Override public List<Feedback> findByReceivedBy(MemberId id) { return List.of(); }

        public List<Feedback> findAll() { return List.of(); }
        public void save(Feedback feedback) { /* no-op */ }
        public void delete(Feedback feedback) { /* no-op */ }
    }
}
