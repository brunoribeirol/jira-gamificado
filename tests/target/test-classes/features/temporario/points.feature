# Created by brunoribeiro at 24/04/25
Feature: Ganhar pontos ao concluir tarefas

  Scenario: Pontuar ao marcar tarefa como concluída
    Given que sou responsável pela tarefa “Documentar endpoints”
    And a tarefa está na coluna “Em Progresso” do Kanban com valor de 200 pontos
    When clico no botão “Concluir tarefa”
    Then o sistema soma 200 pontos ao meu total de pontos
    And move automaticamente a tarefa para a coluna “Concluído”
    And registra a data de conclusão da tarefa

  Scenario: Apenas mover para “Concluído” não gera pontos
    Given que sou responsável pela tarefa “Atualizar README”
    When apenas arrasto o cartão da tarefa para a coluna “Concluído”
    Then o sistema atualiza visualmente a posição no Kanban
    But não altera minha pontuação
    And não registra a tarefa como formalmente concluída
