Feature: Sistema Kanban Gamificado

  Scenario: KAN-1 - Criação de desafio extra
    Given estou no painel de líder
    When clico em "Criar Desafio Extra"
    Then aparece formulário para nome, descrição, critérios, prazo, pontos e equipe-alvo

  Scenario: KAN-2 - Submissão de desafio extra
    Given preencho e envio o formulário
    When confirmo a criação
    Then o desafio entra na lista e membros são notificados

  Scenario: KAN-3 - Visualização do painel da equipe
    Given sou líder e acesso "Painel da Equipe"
    When a página abre
    Then vejo tabela com membros, pontos atuais e variação desde a última atualização

  Scenario: KAN-4 - Atualização do Kanban
    Given acesso "Quadro Kanban"
    When a página carrega
    Then vejo colunas configuráveis com cartões

  Scenario: KAN-5 - Marcação de tarefa concluída
    Given concluo uma tarefa
    When marco-a como concluída
    Then recebo imediatamente os pontos e meu total é atualizado

  Scenario: KAN-6 - Acesso ao progresso
    Given estou logado
    When acesso "Meu Progresso"
    Then vejo gráfico com pontos totais, conquistas e metas alcançadas

  Scenario: KAN-7 - Sistema de feedback
    Given sou membro de equipe e acesso meu perfil
    When clico em "Feedback"
    Then vejo lista de feedbacks recebidos com autor, data e comentário

  Scenario: KAN-8 - Ranking geral
    Given estou autenticado
    When navego até "Ranking Geral"
    Then vejo abas “Por Equipe” e “Por Membro”, ordenadas por pontuação

  Scenario: KAN-9 - Desbloqueio automático de recompensa
    Given sou usuário logado e meus pontos totais alcançam o limite
    When a atualização de pontos é processada
    Then a recompensa correspondente é desbloqueada e aparece no meu perfil

  Scenario: KAN-10 - CRUD de tipos de recompensa
    Given estou autenticado como administrador
    When acesso a página "Tipos de Recompensa"
    Then vejo a lista de todos os tipos com nome, descrição e valor em pontos
