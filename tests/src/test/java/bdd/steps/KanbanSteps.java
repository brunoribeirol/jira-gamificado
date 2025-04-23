package bdd.steps;

import io.cucumber.java.en.*;

public class KanbanSteps {

    @Given("o desafio entra na lista e membros são notificados")
    public void o_desafio_entra_na_lista_e_membros_sao_notificados() {
        System.out.println("Desafio foi listado e membros notificados");
    }

    @Given("sou líder e acesso {string}")
    public void sou_lider_e_acesso(String painel) {
        System.out.println("Líder acessa: " + painel);
    }

    @When("a página abre")
    public void a_pagina_abre() {
        System.out.println("Página aberta");
    }

    @Then("vejo tabela com membros, pontos atuais e variação desde a última atualização")
    public void vejo_tabela_com_membros_pontos_atuais_variacao() {
        System.out.println("Tabela de progresso exibida");
    }

    @Given("acesso {string}")
    public void acesso(String pagina) {
        System.out.println("Acessando: " + pagina);
    }

    @When("a página carrega")
    public void a_pagina_carrega() {
        System.out.println("Página carregada");
    }

    @Then("vejo colunas configuráveis com cartões")
    public void vejo_colunas_com_cartoes() {
        System.out.println("Colunas configuráveis exibidas");
    }

    @Given("concluo uma tarefa")
    public void concluo_uma_tarefa() {
        System.out.println("Tarefa concluída");
    }

    @When("marco-a como concluída")
    public void marco_a_como_concluida() {
        System.out.println("Tarefa marcada como concluída");
    }

    @Then("recebo imediatamente os pontos e meu total é atualizado")
    public void recebo_pontos_total_atualizado() {
        System.out.println("Pontos recebidos e total atualizado");
    }

    @Given("estou logado")
    public void estou_logado() {
        System.out.println("Usuário autenticado");
    }

    @Then("vejo gráfico com pontos totais, conquistas e metas alcançadas")
    public void vejo_grafico_pontos_conquistas_metas() {
        System.out.println("Gráfico exibido");
    }

    @Given("sou membro de equipe e acesso meu perfil")
    public void sou_membro_acesso_perfil() {
        System.out.println("Membro acessa o perfil");
    }

    @When("clico em {string}")
    public void clico_em(String texto) {
        System.out.println("Cliquei em: " + texto);
    }

    @Then("vejo lista de feedbacks recebidos com autor, data e comentário")
    public void vejo_lista_feedbacks() {
        System.out.println("Feedbacks listados");
    }

    @Given("estou autenticado")
    public void estou_autenticado() {
        System.out.println("Usuário autenticado");
    }

    @When("navego até {string}")
    public void navego_ate(String aba) {
        System.out.println("Navegando até: " + aba);
    }

    @Then("vejo abas “Por Equipe” e “Por Membro”, ordenadas por pontuação")
    public void vejo_abas_ordenadas() {
        System.out.println("Abas exibidas por pontuação");
    }

    @Given("sou usuário logado e meus pontos totais alcançam o limite")
    public void pontos_totais_alcancam_limite() {
        System.out.println("Limite de pontos alcançado");
    }

    @When("a atualização de pontos é processada")
    public void atualizacao_pontos_processada() {
        System.out.println("Atualização processada");
    }

    @Then("a recompensa correspondente é desbloqueada e aparece no meu perfil")
    public void recompensa_desbloqueada_aparece() {
        System.out.println("Recompensa desbloqueada no perfil");
    }

    @Given("estou autenticado como administrador")
    public void autenticado_como_admin() {
        System.out.println("Admin autenticado");
    }

    @When("acesso a página {string}")
    public void acesso_pagina(String pagina) {
        System.out.println("Admin acessa: " + pagina);
    }

    @Then("vejo a lista de todos os tipos com nome, descrição e valor em pontos")
    public void vejo_lista_tipos_recompensa() {
        System.out.println("Lista de tipos de recompensa exibida");
    }

    @Given("estou no painel de líder")
    public void estou_no_painel_de_líder() {
        System.out.println("Painel de líder acessado.");
    }

    @Then("aparece formulário para nome, descrição, critérios, prazo, pontos e equipe-alvo")
    public void aparece_formulário_para_nome_descrição_critérios_prazo_pontos_e_equipe_alvo() {
        System.out.println("Formulário de criação de desafio extra visível.");
    }

    @Given("preencho e envio o formulário")
    public void preencho_e_envio_o_formulário() {
        System.out.println("Formulário preenchido e enviado.");
    }

    @When("confirmo a criação")
    public void confirmo_a_criação() {
        System.out.println("Criação de desafio confirmada.");
    }


}
