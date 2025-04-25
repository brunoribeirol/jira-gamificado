# Created by brunoribeiro at 24/04/25
Feature: Consultar progresso pessoal

  Scenario: Visualizar progresso com recompensas
    Given que estou na aba “Meu Perfil”
    When visualizo meus dados de progresso
    Then o sistema retorna meu total de pontos
    And a lista das recompensas que já desbloqueei

  Scenario: Nenhuma recompensa desbloqueada
    Given que tenho menos pontos do que o necessário para qualquer recompensa
    When acesso a seção “Meu Progresso”
    Then o sistema retorna que não há recompensas desbloqueadas
