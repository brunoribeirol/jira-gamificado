
# Segunda Entrega – SprintQuest

## ✅ Histórias Implementadas

### Subdomínio: **ProjectManagement**
1. **Criar projeto com colunas padrão Scrumban**  
   _Como líder, quero criar um novo projeto com colunas padrão Scrumban para organizar o fluxo da equipe_

2. **Adicionar tarefas ao projeto**  
   _Como líder, quero adicionar tarefas ao projeto, para que fiquem disponíveis no Backlog e a equipe possa executá-las_

3. **Criar desafios extras**  
   _Como líder, quero criar desafios extras com critérios específicos, para estimular boas práticas_

4. **Editar ou excluir tarefas**  
   _Como líder, quero editar ou excluir tarefas, para ajustar o planejamento conforme necessário_

5. **Mover tarefas no Kanban**  
   _Como membro, quero mover minhas tarefas no quadro Kanban, para indicar seu progresso_

---

### Subdomínio: **Gamification**
6. **Ganhar pontos ao concluir tarefas**  
   _Como membro, quero ganhar pontos ao concluir tarefas, para subir no ranking_

7. **Desbloquear recompensas ao atingir pontuação**  
   _Como membro, quero desbloquear recompensas ao atingir certa pontuação, para me manter motivado_

---

### Subdomínio: **TeamsAndUsers**
8. **Consultar progresso individual**  
   _Como membro, quero consultar meu progresso para acompanhar minha evolução_

9. **Receber feedbacks**  
   _Como membro, quero receber feedback dos colegas ou do líder, para melhorar minhas entregas_

10. **Cadastrar tipos de recompensa**  
    _Como líder, quero cadastrar tipos de recompensas, para oferecer prêmios para as equipes e usuários de acordo com o esforço deles_

---

## 📐 Padrões de Projeto Utilizados

| Padrão              | Arquivos Envolvidos                                                                                                                 |
| ------------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| **Template Method** | - `TaskCompletionTemplate.java` (interface base)<br>- `StandardTaskCompletion.java` (implementação)                                 |
| **Strategy**        | - `TaskScoreStrategy.java` (interface)<br>- `DefaultTaskScoreStrategy.java` (implementação)<br>- `TaskServiceImpl.java`             |
| **Observer**        | - `TaskCompletedEvent.java` (evento)<br>- `TaskCompletedListener.java` (listener)<br>- `TaskServiceImpl.java` (publisher do evento) |
| **Iterator**        | - `Project.java` (método `iterator()` sobre tarefas)<br>- `Team.java` (método `iterator()` sobre membros)<br>- Usos nos controllers |

---