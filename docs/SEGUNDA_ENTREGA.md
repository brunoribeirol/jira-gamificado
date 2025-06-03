
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

| Padrão              | Aplicação                                                                  |
|---------------------|----------------------------------------------------------------------------|
| **Template Method** | `StandardTaskCompletion.java` – Encapsula o fluxo de conclusão de tarefas  |
| **Strategy**        | `TaskScoreStrategy.java` e implementações – Define regras de pontuação     |
| **Observer**        | `ApplicationEventPublisher` + `TaskCompletedEvent` – Notificação de eventos |
| **Iterator**        | Listar tarefas de um projeto, listar membros de uma tarefa                 |

---

## ▶️ Instruções para Executar o Projeto

1. **Pré-requisitos:**
   - Java 17+
   - Maven 3.8+
   - MySQL 8 ou superior
   - IDE (recomendado: IntelliJ ou VS Code com extensões Java)

2. **Configurar o banco de dados:**

   - Acesse o MySQL via terminal ou ferramenta gráfica:

   ```
   bash
   Copiar
   Editar
   mysql -u root -p
   Crie o banco de dados:```

   ```
   sql
   Copiar
   Editar
   CREATE DATABASE sprintquest;
   Crie um usuário com senha segura e conceda as permissões necessárias:```

   ```
   sql
   Copiar
   Editar
   CREATE USER 'sprintquest_user'@'localhost' IDENTIFIED BY 'sua_senha_segura';
   GRANT ALL PRIVILEGES ON sprintquest.* TO 'sprintquest_user'@'localhost';
   FLUSH PRIVILEGES;```


   - Crie um banco chamado `sprintquest`
   - Atualize o `application.properties` com as credenciais do seu banco

3. **Compilar o projeto:**
   ```bash
   mvn clean install
   ```

4. **Executar a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

5. **Acessar a aplicação:**
   - Página inicial (Swagger ou endpoint REST):  
     [http://localhost:8080](http://localhost:8080)

---

## 🧪 Dados de Teste (População do Banco)

O projeto utiliza um script SQL para popular o banco de dados com dados realistas que facilitam o teste das funcionalidades desenvolvidas.

**Arquivo:** [`sprintquest.sql`](../../../Downloads/sprintquest.sql)

Esse script insere dados em diversas tabelas:

- **members**: usuários com diferentes funções e pontuações;
- **teams**: equipes e seus respectivos líderes;
- **projects**: projetos vinculados a equipes;
- **tasks**: tarefas com status, pontos e datas;
- **task_assignees**: membros atribuídos às tarefas;
- **rewards**: recompensas com tipos e critérios;
- **member_rewards**: recompensas desbloqueadas por membros;
- **challenges**: desafios com critérios e pontos extras;
- **feedbacks**: avaliações de membros sobre o desempenho de colegas.

### Como utilizar

1. Crie o schema no seu banco MySQL:
   ```sql
   CREATE DATABASE sprintquest;
   ```

2. Execute o script no banco:
   - Usando sua IDE (ex: DBeaver, MySQL Workbench) ou CLI:
     ```bash
     mysql -u seu_usuario -p sprintquest < sprintquest.sql
     ```

3. Em seguida, inicie a aplicação normalmente (veja seção anterior).

Esse processo garante uma base funcional e coerente para simulações de:
- Movimentação de tarefas;
- Pontuação e ranqueamento;
- Recompensas;
- Feedbacks entre membros;
- Gestão de desafios.

---

_Em caso de dúvidas, consulte a equipe de desenvolvimento._
