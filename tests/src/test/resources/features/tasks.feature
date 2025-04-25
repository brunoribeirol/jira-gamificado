# Created by brunoribeiro at 24/04/25
Feature: Adicionar tarefas ao projeto

  Scenario: Tarefa criada com sucesso
    Given que estou criando uma tarefa no projeto
    When preencho o nome “Implementar endpoint de login”, a descrição “API para autenticação” e atribuo ao membro Joana
    Then a tarefa é criada na coluna Backlog do Kanban

  Scenario: Responsável não selecionado
    Given que estou criando uma tarefa no projeto
    When deixo o campo de responsável em branco
    Then o sistema exibe a mensagem “Selecione ao menos um responsável”
    And impede a criação da tarefa

  Scenario: Nome da tarefa em branco
    Given que estou no formulário de nova tarefa
    When deixo o campo de nome vazio
    Then o sistema exibe a mensagem “O nome da tarefa é obrigatório”
    And bloqueia o envio
