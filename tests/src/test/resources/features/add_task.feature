Feature: Adicionar nova tarefa ao projeto

  Scenario: Tarefa criada com sucesso
    Given estou criando uma tarefa no projeto
    When preencho o nome "Implementar endpoint de login", a descrição "API para autenticação" e atribuo ao membro "Joana"
    Then a tarefa é criada na coluna "Backlog" do Kanban

  Scenario: Responsável não selecionado
    Given estou criando uma tarefa no projeto
    When deixo o campo de responsável em branco
    Then mensagem de erro ao adicionar tarefa: "Selecione ao menos um responsável"
    And impede a criação da tarefa

  Scenario: Nome da tarefa em branco
    Given estou no formulário de nova tarefa
    When deixo o campo de nome vazio
    Then mensagem de erro ao adicionar tarefa: "O nome da tarefa é obrigatório"
    And bloqueia o envio
