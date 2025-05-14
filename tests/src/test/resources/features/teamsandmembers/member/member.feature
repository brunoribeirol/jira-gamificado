# Created by brunoribeiro at 24/04/25
Feature: Receber e visualizar feedbacks

  Scenario: Enviar feedback para um membro
    Given que estou no perfil de um colega
    When preencho o formulário de feedback e envio
    Then o feedback aparece na lista do membro com nota e comentário

  Scenario: Visualizar feedbacks recebidos
    Given que recebi feedbacks anteriormente
    When acesso minha aba de feedback
    Then o sistema retorna uma lista com os feedbacks recebidos e seus respectivos emissores e datas
