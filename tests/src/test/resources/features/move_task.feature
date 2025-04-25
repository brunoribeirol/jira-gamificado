# Created by brunoribeiro at 24/04/25
Feature: Mover tarefas no quadro Kanban

  Scenario: Mover tarefa com sucesso
    Given que sou responsável pela tarefa “Implementar login”
    And ela está na coluna “Pronto”
    When arrasto o cartão para a coluna “Em Progresso”
    Then o sistema atualiza o status da tarefa para “Em Progresso”
    And a nova posição é refletida no quadro Kanban

  Scenario: Tentativa de mover tarefa que não me pertence
    Given que estou tentando mover uma tarefa atribuída a outro membro
    And a tarefa “Revisar documentação” está atribuída a outro membro
    When tento mover essa tarefa para outra coluna
    Then o sistema exibe a mensagem “Você não pode mover tarefas que não são suas”
    And mantém a tarefa em sua posição original
