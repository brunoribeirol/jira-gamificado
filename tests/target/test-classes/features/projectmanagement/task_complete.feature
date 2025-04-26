Feature: Concluir tarefa

  Scenario: Pontuar ao marcar tarefa como concluída
    Given sou responsável pela tarefa "Documentar endpoints"
    And a tarefa está na coluna "Em Progresso" do Kanban com valor de 200 pontos
    When clico no botão "Concluir tarefa"
    Then a tarefa é movida para a coluna "Concluído"
    And minha pontuação aumenta em 200 pontos
    And a data de conclusão da tarefa é registrada

  Scenario: Apenas mover para "Concluído" não gera pontos
    Given sou responsável pela tarefa "Atualizar README"
    When apenas arrasto o cartão da tarefa para a coluna "Concluído"
    Then a tarefa aparece visualmente na coluna "Concluído"
    But minha pontuação não é alterada
    And o sistema não registra a tarefa como formalmente concluída
    And exibe a mensagem de erro: "A tarefa não foi concluída corretamente"
