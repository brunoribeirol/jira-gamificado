# Created by brunoribeiro at 24/04/25
Feature: Cadastrar tipos de recompensas

  Scenario: Criar novo tipo de recompensa
    Given que estou na aba “Recompensas” e clico em “Nova recompensa”
    When preencho nome, descrição e pontuação necessária
    Then a recompensa é salva no sistema
    And aparece disponível para os membros da equipe

  Scenario: Pontuação inválida
    Given que estou adicionando uma nova recompensa
    When informo uma pontuação menor ou igual a zero
    Then o sistema rejeita o cadastro
    And exibe a mensagem de erro: “A pontuação mínima deve ser maior que zero”
