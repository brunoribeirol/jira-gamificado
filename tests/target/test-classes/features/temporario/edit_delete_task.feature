# Created by brunoribeiro at 24/04/25
Feature: Editar ou excluir tarefas

  Scenario: Edição bem-sucedida
    Given que estou autenticado como líder no projeto “Sprint Integração”
    And que a tarefa “Documentar API” está atribuída a Joana
    When acesso a tarefa e altero o nome para “Documentar API v2” e atribuo ao membro Rafael
    Then as alterações são salvas com sucesso
    And a tarefa atualizada aparece imediatamente na coluna onde estava, agora com os novos dados

  Scenario: Tentativa de edição com campos obrigatórios vazios
    Given que estou autenticado como líder
    And acessando uma tarefa existente do projeto
    When removo o nome da tarefa e tento salvar
    Then o sistema exibe a mensagem “O nome da tarefa é obrigatório”
    And impede que a tarefa seja atualizada

  Scenario: Exclusão de tarefa
    Given que estou autenticado como líder
    And que a tarefa “Revisar testes” está no projeto “Sprint Integração”
    When clico em “Excluir” ao lado da tarefa
    Then o sistema remove a tarefa da coluna do Kanban
    And a tarefa é excluída da base de dados
