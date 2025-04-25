# language: pt
Funcionalidade: Criar Projeto

  Como líder de equipe
  Quero criar um novo projeto com colunas padrão Scrumban
  Para organizar o fluxo da equipe

  Cenario: Criação bem sucedida
    Dado estou autenticado como líder da equipe "FrontEndZ"
    Quando clico em “Novo Projeto” e preencho o nome "Sprint Integração" e a descrição "Entrega do Módulo de Login"
    Então o sistema cria o projeto com as colunas padrão: "Backlog", "Pronto", "Em Progresso", "Revisão", "Concluído"
    E o projeto aparece na lista de projetos da equipe

  Cenario: Nome do projeto em branco
    Dado estou autenticado como líder da equipe "BackEndX"
    Quando tento criar um projeto sem preencher o campo de nome
    Então o sistema exibe a mensagem "Nome do projeto é obrigatório"
    E não permite concluir o cadastro
