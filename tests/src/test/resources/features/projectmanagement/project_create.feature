Feature: Criar novo projeto

  Scenario: Criação bem sucedida
    Given estou autenticado como líder da equipe "FrontEndZ"
    When clico em “Novo Projeto” e preencho o nome "Sprint Integração" e a descrição "Entrega do Módulo de Login"
    Then o sistema cria o projeto com as colunas padrão: "Backlog", "Pronto", "Em Progresso", "Revisão", "Concluído"
    And o projeto aparece na lista de projetos da equipe

  Scenario: Nome do projeto em branco
    Given estou autenticado como líder da equipe "BackEndZ"
    When tento criar um projeto sem preencher o campo de nome
    Then mensagem de erro ao criar projeto: "Nome do projeto é obrigatório"
    And não permite concluir o cadastro
