## Created by brunoribeiro at 24/04/25
#Feature: Criar desafios extras com critérios específicos
#
#  Scenario: Criação de desafio bem-sucedida
#    Given que estou autenticado como líder no projeto “Sprint Integração”
#    When preencho o nome “Cobertura de testes”, critério “Cobertura mínima de 80%” e prazo para 30/04/2025
#    Then o desafio é criado com sucesso
#    And fica visível para todos os membros da equipe
#
#  Scenario: Critério vazio
#    Given que estou preenchendo um novo desafio
#    When deixo o campo de critério em branco
#    Then o sistema exibe a mensagem de erro do critério: “Critério é obrigatório”
#    And não permite salvar o desafio
