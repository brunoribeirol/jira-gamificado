## Created by brunoribeiro at 24/04/25
#Feature: Desbloquear recompensas por pontuação
#
#  Scenario: Desbloqueio automático de recompensa
#    Given que meu total de pontos atual é 9500
#    When eu concluo uma tarefa de 600 pontos
#    Then meu total de pontos é atualizado para 10100
#    And a recompensa “Cupom R$ 50” com requisito de 10000 pontos aparece como desbloqueada no meu perfil
#
#  Scenario: Pontuação insuficiente para desbloqueio
#    Given que tenho 8300 pontos acumulados
#    And a próxima recompensa requer 10000 pontos
#    When visualizo a seção de recompensas
#    Then a recompensa permanece bloqueada
#    And não aparece como conquistada no meu perfil
