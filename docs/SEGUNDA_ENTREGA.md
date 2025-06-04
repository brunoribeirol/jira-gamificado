
# Segunda Entrega – SprintQuest

## ✅ Histórias Implementadas

### Subdomínio: **ProjectManagement**

1. **Criar projeto com colunas padrão Scrumban**  
   _Como líder, quero criar um novo projeto com colunas padrão Scrumban para organizar o fluxo da equipe_

   **Cenários BDD:**

    - **Criação bem-sucedida**  
      **Dado que** estou autenticado como líder da equipe Alpha Team  
      **Quando** clico em “Novo Projeto” e preencho o nome “Sprint Integração” e a descrição “Entrega do Módulo de Login”  
      **Então** o sistema cria o projeto com as colunas padrão: Backlog, Pronto, Em Progresso, Revisão, Concluído  
      **E** o projeto aparece na lista de projetos da equipe

    - **Nome do projeto em branco**  
      **Dado que** estou autenticado como líder da equipe Alpha Team  
      **Quando** tento criar um projeto sem preencher o campo de nome  
      **Então** o sistema exibe a mensagem “Nome do projeto é obrigatório”  
      **E** não permite concluir o cadastro


2. **Adicionar tarefas ao projeto**  
   _Como líder, quero adicionar tarefas ao projeto, para que fiquem disponíveis no Backlog e a equipe possa executá-las_

   **Cenários BDD:**

    - **Tarefa criada com sucesso**  
      **Dado que** estou criando uma tarefa no projeto  
      **Quando** preencho o título “Implementar endpoint de login”, a descrição “API para autenticação”, estabeleço os pontos e atribuo ao membro João Silva  
      **Então** a tarefa é criada na coluna Backlog do Kanban

    - **Responsável não selecionado**  
      **Dado que** estou criando uma tarefa no projeto  
      **Quando** deixo o campo de responsável em branco  
      **Então** o sistema exibe a mensagem “É necessário selecionar um responsável para a tarefa”  
      **E** impede a criação da tarefa

    - **Título da tarefa em branco**  
      **Dado que** estou no formulário de nova tarefa  
      **Quando** deixo o campo de título vazio  
      **Então** o sistema exibe a mensagem “Preencha este campo”  
      **E** destaca o campo  
      **E** bloqueia o envio


3. **Criar desafios extras**  
   _Como líder, quero criar desafios extras com critérios específicos, para estimular boas práticas_

   **Cenários BDD:**

    - **Dado que** estou criando um desafio no projeto “Sprint Integração”  
      **Quando** preencho o nome “Cobertura de testes”, o critério “Cobertura mínima de 80%”, o prazo para 30/06/2025 e a pontuação  
      **Então** o desafio é criado com sucesso  
      **E** fica visível para todos os membros da equipe

    - **Dado que** estou preenchendo um novo desafio  
      **Quando** deixo o campo de critério em branco  
      **Então** o sistema exibe a mensagem “O critério do desafio é obrigatório”  
      **E** não permite salvar o desafio


4. **Editar ou excluir tarefas**  
   _Como líder, quero editar ou excluir tarefas, para ajustar o planejamento conforme necessário_

   **Cenários BDD:**

    - **Edição bem-sucedida**  
      **Dado que** a tarefa “Configurar Banco H2” está atribuída a João Silva  
      **Quando** altero o nome da tarefa para “Configurar Banco H2 v2”  
      **Então** as alterações são salvas com sucesso  
      **E** a tarefa continua na coluna “Em Progresso”  
      **E** continua atribuída a João Silva

    - **Tentativa de edição com campos obrigatórios vazios**  
      **Dado que** estou acessando a tarefa “Criar tela de Dashboard” do projeto “Projeto Principal - App”  
      **Quando** removo o nome da tarefa e tento salvar  
      **Então** o sistema exibe a mensagem “Preencha este campo”  
      **E** a tarefa não é atualizada

    - **Exclusão de tarefa**  
      **Dado que** a tarefa “Testes de Performance” pertence ao projeto “Projeto Melhorias QA”  
      **Quando** clico nos 3 pontos e em “Excluir”  
      **Então** o sistema remove a tarefa da coluna do Kanban  
      **E** a tarefa é excluída da base de dados


5. **Mover tarefas no Kanban**  
   _Como membro, quero mover minhas tarefas no quadro Kanban, para indicar seu progresso_

   **Cenários BDD:**

    - **Mover tarefa com sucesso**  
      **Dado que** o membro João Silva (ID 1) é responsável pela tarefa “Implementar Login” (ID 1)  
      **E** ela está na coluna “Backlog”  
      **Quando** ele move a tarefa para a coluna “Em Progresso”  
      **Então** o sistema atualiza o status da tarefa para “Em Progresso”  
      **E** mantém essa nova posição no quadro Kanban

    - **Tentativa de mover tarefa que não me pertence**  
      **Dado que** estou autenticado como o membro João Silva  
      **E** que a tarefa “Criar tela de Dashboard” está atribuída a Ana Costa  
      **Quando** tento mover essa tarefa para a coluna “Concluído”  
      **Então** o sistema não permite  
      **E** mantém a tarefa na coluna original

### Subdomínio: **Gamification**

6. **Ganhar pontos ao concluir tarefas**  
   _Como membro, quero ganhar pontos ao concluir tarefas, para subir no ranking_

   **Cenários BDD:**

    - **Pontuar ao marcar tarefa como concluída**  
      **Dado que** sou o membro João Silva (ID 1) responsável pela tarefa “Configurar Banco H2”  
      **E** a tarefa está na coluna “Em Progresso” do Kanban  
      **Quando** arrasto o cartão da tarefa para a coluna “Concluído”  
      **Então** o sistema soma os pontos ao meu total de pontos com bônus  
      **E** registra a data de conclusão da tarefa

    - **Reposicionar tarefa já concluída não gera pontos novamente**  
      **Dado que** sou o membro responsável pela tarefa “Implementar Login”  
      **E** a tarefa já foi movida anteriormente para a coluna “Concluído” e teve os pontos registrados  
      **Quando** movo novamente o cartão da tarefa para a coluna “Concluído”  
      **Então** o sistema mantém a tarefa na nova posição visual no Kanban  
      **Mas** não adiciona pontos novamente à minha pontuação  
      **E** não altera a data de conclusão já registrada


7. **Desbloquear recompensas ao atingir pontuação**  
   _Como membro, quero desbloquear recompensas ao atingir certa pontuação, para me manter motivado_

   **Cenários BDD:**

    - **Desbloqueio automático de recompensa**  
      **Dado que** o membro João Silva concluiu uma tarefa  
      **Quando** os pontos dessa tarefa são adicionados  
      **Então** a recompensa “Destaque no mural de conquistas” com requisito de 150 pontos fica disponível para resgate

    - **Pontuação insuficiente para desbloqueio**  
      **Dado que** a membro Rafaela Lima (ID 5) possui 95 pontos acumulados  
      **E** a próxima recompensa requer 100 pontos  
      **Quando** ela acessa a seção de recompensas  
      **Então** a recompensa “Meio dia de folga extra” permanece bloqueada  
      **E** não aparece como recompensa disponível no seu perfil

### Subdomínio: **TeamsAndUsers**

8. **Consultar progresso individual**  
   _Como membro, quero consultar meu progresso para acompanhar minha evolução_

   **Cenários BDD:**

    - **Visualizar progresso com recompensas**  
      **Dado que** estou na aba “Meu Perfil”  
      **Quando** visualizo meus dados de progresso  
      **Então** o sistema retorna meu total de pontos  
      **E** a lista das recompensas que já desbloqueei

    - **Nenhuma recompensa desbloqueada**  
      **Dado que** tenho menos pontos do que o necessário para qualquer recompensa  
      **Quando** acesso a seção “Recompensas Disponíveis”  
      **Então** o sistema retorna que não há recompensas desbloqueadas


9. **Receber feedbacks**  
   _Como membro, quero receber feedback dos colegas ou do líder, para melhorar minhas entregas_

   **Cenários BDD:**

    - **Enviar feedback para um membro**  
      **Dado que** estou na lista de membros da equipe  
      **Quando** clico nos 3 pontos e seleciono “Enviar Feedback”, preencho o feedback e envio  
      **Então** o feedback aparece na lista do membro

    - **Visualizar feedbacks recebidos**  
      **Dado que** recebi feedbacks anteriormente  
      **Quando** acesso meu perfil e vou na sessão “Feedbacks Recebidos”  
      **Então** o sistema retorna uma lista com os feedbacks recebidos e suas respectivas datas

10. **Cadastrar tipos de recompensa**  
    _Como líder, quero cadastrar tipos de recompensas, para oferecer prêmios para as equipes e usuários de acordo com o esforço deles_

    **Cenários BDD:**

    - **Criar novo tipo de recompensa**  
      **Dado que** estou na aba “Recompensas” e clico em “Nova recompensa”  
      **Quando** preencho descrição, pontuação necessária e tipo  
      **Então** a recompensa é salva no sistema  
      **E** aparece disponível para os membros da equipe

    - **Pontuação inválida**  
      **Dado que** estou adicionando uma nova recompensa  
      **Quando** informo uma pontuação menor ou igual a zero  
      **Então** o sistema rejeita o cadastro  
      **E** exibe a mensagem de erro: “O valor deve ser maior ou igual a 1”


## 📐 Padrões de Projeto Utilizados

| Padrão              | Arquivos Envolvidos                                                                                                                 |
| ------------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| **Template Method** | - `TaskCompletionTemplate.java` (interface base)<br>- `StandardTaskCompletion.java` (implementação)                                 |
| **Strategy**        | - `TaskScoreStrategy.java` (interface)<br>- `DefaultTaskScoreStrategy.java` (implementação)<br>- `TaskServiceImpl.java`             |
| **Observer**        | - `TaskCompletedEvent.java` (evento)<br>- `TaskCompletedListener.java` (listener)<br>- `TaskServiceImpl.java` (publisher do evento) |
| **Iterator**        | - `Project.java` (método `iterator()` sobre tarefas)<br>- `Team.java` (método `iterator()` sobre membros)<br>- Usos nos controllers |

---