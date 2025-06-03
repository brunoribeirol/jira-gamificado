USE sprintquest;
-- === MEMBROS ===
INSERT INTO members (name, email, password, role, individual_score, is_admin, team_id) VALUES
                                                                                           ('João Silva', 'joao.silva@example.com', 'senha123', 'DEV', 150, false, 101),   -- ID 1
                                                                                           ('Maria Souza', 'maria.souza@example.com', 'senha456', 'QA', 120, false, 101),  -- ID 2
                                                                                           ('Pedro Gomes', 'pedro.gomes@example.com', 'senha789', 'LIDER', 200, true, 101),-- ID 3 (líder de Alpha e Gamma)
                                                                                           ('Ana Costa', 'ana.costa@example.com', 'senhaabc', 'DEV', 80, false, 101),      -- ID 4
                                                                                           ('Rafaela Lima', 'rafaela.lima@example.com', 'senhadef', 'QA', 95, false, 102), -- ID 5
                                                                                           ('Lucas Rocha', 'lucas.rocha@example.com', 'senhaaaa', 'DEV', 70, false, 103),  -- ID 6
                                                                                           ('Bianca Melo', 'bianca.melo@example.com', 'senha1234', 'QA', 130, false, 103), -- ID 7
                                                                                           ('Carlos Pinto', 'carlos.pinto@example.com', 'senhaabc123', 'LIDER', 160, true, 102), -- ID 8
                                                                                           ('Letícia Andrade', 'leticia.andrade@example.com', '123senha', 'QA', 90, false, 101); -- ID 9

INSERT INTO teams (id, name, leader_id, team_score) VALUES
                                                        (101, 'Alpha Team', 3, 400),
                                                        (102, 'Beta Team', 8, 230),
                                                        (103, 'Gamma Team', 3, 0);

-- === PROJETOS ===
INSERT INTO projects (name, description, team_id) VALUES
                                                      ('Projeto Principal - App', 'Desenvolvimento do sistema Jira Gamificado', 101), -- ID 1
                                                      ('Projeto Melhorias QA', 'Foco em automação e testes de regressão', 101),       -- ID 2
                                                      ('Projeto Mobile', 'App mobile complementar', 102);                              -- ID 3

-- === TAREFAS ===
INSERT INTO tasks (title, description, points, created_at, kanban_column, completed_at, project_id) VALUES
                                                                                                        ('Implementar Login', 'Funcionalidade de login de usuários', 10, '2023-01-10 10:00:00', 'Backlog', NULL, 1),
                                                                                                        ('Criar Testes Unitários para Login', 'Testes unitários do login', 5, '2023-01-12 11:00:00', 'Pronto', NULL, 1),
                                                                                                        ('Configurar Banco H2', 'Banco de dados em memória H2', 8, '2023-01-05 09:00:00', 'Em Progresso', NULL, 1),
                                                                                                        ('Criar tela de Dashboard', 'Interface gráfica do dashboard', 15, '2023-01-20 13:00:00', 'Revisão', NULL, 1),
                                                                                                        ('Configurar Cypress', 'Ambiente de testes com Cypress', 12, '2023-02-01 10:00:00', 'Concluído', '2023-02-01 10:00:00', 2),
                                                                                                        ('Teste E2E Login', 'Script de teste E2E para login', 7, '2023-02-05 14:00:00', 'Backlog', NULL, 2),
                                                                                                        ('Ambiente Flutter', 'Setup Flutter', 10, '2023-03-01 09:00:00', 'Pronto', NULL, 3),
                                                                                                        ('Nova Tela de Estatísticas', 'Dashboard analítico', 11, '2023-03-10 10:00:00', 'Em Progresso', NULL, 1),
                                                                                                        ('Aprimorar UX do Formulário', 'Melhorias visuais no formulário de cadastro', 6, '2023-03-15 11:00:00', 'Revisão', NULL, 3),
                                                                                                        ('Testes de Performance', 'Stress test do sistema', 13, '2023-03-20 15:00:00', 'Pronto', NULL, 2);

-- === ASSIGNEES ===
INSERT INTO task_assignees (task_id, member_id) VALUES
                                                    (1, 1), (2, 2), (3, 1), (3, 3),
                                                    (4, 4), (5, 2), (6, 5), (7, 4),
                                                    (8, 6), (9, 9), (10, 7);

-- === RECOMPENSAS ===
INSERT INTO rewards (description, required_points, type, created_by) VALUES
                                                                         ('Cupom de 10% na cafeteria', 50, 'CUPOM', 3),
                                                                         ('Meio dia de folga extra', 100, 'FOLGA', 3),
                                                                         ('Destaque no mural de conquistas', 150, 'DESTAQUE', 3),
                                                                         ('Acesso antecipado a novidades', 75, 'DESTAQUE', 3);

-- === MEMBER REWARDS ===
INSERT INTO member_rewards (member_id, reward_id) VALUES
                                                      (1, 1), (1, 4), (3, 2);

-- === DESAFIOS ===
INSERT INTO challenges (title, description, criteria, extra_points, created_by, project_id, deadline) VALUES
                                                                                                          ('Desafio Refatoração', 'Refatorar autenticação', 'Reduzir duplicação em 20%', 30, 3, 1, '2023-03-30 17:00:00'),
                                                                                                          ('Desafio Performance Mobile', 'Melhorar tempo de carga', 'Reduzir em 50%', 25, 1, 3, '2023-04-15 17:00:00');

-- === FEEDBACKS ===
INSERT INTO feedbacks (message, date, given_by, received_by, related_task) VALUES
                                                                               ('Ótimo trabalho no login!', '2023-01-16', 3, 1, 1),
                                                                               ('Excelente revisão de código!', '2023-02-01', 1, 2, NULL),
                                                                               ('Teste E2E excelente!', '2023-02-08', 2, 5, 6);
-- === TEAM MEMBERS ===
INSERT INTO team_members (team_id, member_id) VALUES
                                                  (101, 1),  -- João Silva
                                                  (101, 2),  -- Maria Souza
                                                  (101, 3),  -- Pedro Gomes (líder Alpha/Gamma)
                                                  (101, 4),  -- Ana Costa (para projeto 1 e 2)
                                                  (101, 5),  -- Rafaela Lima (corrigido)
                                                  (101, 6),  -- Lucas Rocha (corrigido)
                                                  (101, 7),  -- Bianca Melo (já ajustado)
                                                  (101, 9),  -- Letícia Andrade (usada em projeto 1)
                                                  (102, 4),  -- Ana Costa (duplicado para projeto 3)
                                                  (102, 8),  -- Carlos Pinto (líder Beta)
                                                  (102, 9),  -- Letícia Andrade (usada em projeto 3)
                                                  (103, 3);  -- Pedro Gomes (líder Gamma)
