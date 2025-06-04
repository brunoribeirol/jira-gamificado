# 🧩 SprintQuest - Backend

Este é o repositório do backend da aplicação **SprintQuest**, uma plataforma gamificada voltada para equipes de desenvolvimento ágil. Combinando metodologias como **Scrumban** à **gamificação de tarefas**, o sistema promove engajamento através de pontos, desafios, conquistas e rankings — tornando a gestão de projetos mais divertida, visual e eficiente.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven (multi-módulo)**
- **JPA (Hibernate)**
- **REST API**
- **BDD com Cucumber**
- **MySQL**

---
---

## 📌 Sobre o Projeto

SprintQuest permite a criação e gestão de projetos com visualização de tarefas em estilo *Kanban*, promovendo a colaboração entre membros da equipe. Os usuários acumulam pontos ao concluir tarefas e desafios, podendo desbloquear recompensas, subir no ranking e ganhar distintivos por suas conquistas. Líderes de equipe têm acesso à performance geral da equipe, podendo fornecer feedbacks e aplicar bônus motivacionais.

---

## 🔧 Funcionalidades Principais

- ✅ Criação de **projetos com etapas baseadas em Scrumban**
- 📋 Quadro **Kanban interativo** para visualização e movimentação de tarefas
- 🎯 Criação de **desafios personalizados** com critérios de pontuação
- ⚙️ **Sistema de pontuação** baseado na complexidade e tipo de tarefa
- 🏆 Rankings **semanais e mensais** entre membros e equipes
- 🎁 **Recompensas desbloqueáveis** (cupons, benefícios, etc.)
- 🥇 Sistema de **conquistas e distintivos**
- 💬 **Feedbacks positivos** que geram bônus de motivação

---

## 🛠️ Pré-requisitos

- Java 17+
- Maven 3.8+
- MySQL 8 ou superior
- IDE (recomendado: IntelliJ ou VS Code com extensões Java)

---

## 📦 Instalação e Execução

### 🔁 Clone o repositório

```bash
git clone https://github.com/brunoribeirol/jira-gamificado.git
cd jira-gamificado
```

### ⚙️ Configure o banco de dados

Acesse o MySQL via terminal ou ferramenta gráfica:

```
mysql -u root -p
```

Crie o banco de dados:
```
CREATE DATABASE sprintquest;
```

Por padrão, o backend espera um MySQL local rodando em `localhost:3306` com:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sprintquest
spring.datasource.username=root
spring.datasource.password=123456
```

> Altere o `application.properties` ou defina variáveis de ambiente de acordo com suas credenciais.

---

### 🧪 Dados de Teste (População do Banco)

O projeto utiliza um script SQL para popular o banco de dados com dados realistas que facilitam o teste das funcionalidades desenvolvidas.

**Arquivo:** [`data.sql`](presentation/src/main/resources/data.sql)

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

---

## ▶️ Executando a aplicação

Compile o projeto
```
mvn clean install
```

Execute a aplicação:

```bash
cd presentation
mvn spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

## 🧪 Testes

Para rodar os testes BDD com Cucumber:

```bash
mvn test
```

---
## 📚 Documentação

### 📦 Segunda Entrega
- 🧩 [Histórias Implementadas & Padrões de Projeto](./docs/SEGUNDA_ENTREGA.md)  
  Histórias implementadas & Padrões de projeto utilizados no desenvolvimento da aplicação.

### 🧾 Descrição Geral
- 📄 [Descrição do Domínio (PDF)](./docs/descricao_dominio.pdf)  
  Visão conceitual e motivação do sistema, destacando os objetivos e a lógica do negócio.

### 👥 Personas
- 👤 [Personas do Projeto](./docs/personas.pdf)  
  Perfis de usuários que interagem com o sistema, com seus objetivos e dores.

### 📝 Funcionalidades
- 📋 [Histórias de Usuário](./docs/historias_usuario.pdf)  
  Detalhamento das histórias no formato:  
  _"Como [persona], quero [funcionalidade] para [benefício]."_

- 🗺️ [Mapa de Histórias de Usuário](./docs/mapa_historias_usuario.jpeg)  
  Visualização da jornada e organização das histórias.

### 🧑‍🎨 Prototipação
- 🖌️ [Protótipo de Baixa Fidelidade (Figma)](https://www.figma.com/design/8yORsLBWoXVqCpEj7tWPCJ/Projeto-Requisistos?node-id=0-1&p=f&t=eIytbgGlwM35SFbd-0)  
  Interface inicial com telas representativas da navegação e funcionalidades principais.

### 🧠 Modelagem de Domínio
- 🗂️ [Modelo do Domínio (Context Mapper - CML)](./docs/context-mapper/cml/SprintQuest.cml)  
  Estrutura de bounded contexts e subdomínios seguindo DDD.

- 📊 [Diagrama UML (PlantUML - PNG)](./docs/context-mapper/images)  
  Representação visual das entidades, agregados e serviços do sistema.

### ✅ Validação Comportamental
- 🧪 [BDD - Cenários de Teste (Gherkin)](./docs/bdd_gherkin.pdf)  
  Cenários escritos com Gherkin para testes comportamentais automatizados com Cucumber.

### 📽️ Apresentação
- 🎤 [Slide da 1ª Apresentação](https://www.canva.com/design/DAGleCnl3jE/XS6bPCAyfjVwojx6mjnXhA/edit)  
  Material da 1ª apresentação.

___

## 🤝 Integração com Frontend

Certifique-se de que o backend esteja rodando em:

```
http://localhost:8080
```

Para que o frontend (React + Vite) funcione corretamente com:

```env
VITE_API_URL=http://localhost:8080/api
```

---

## 👥 Time de Desenvolvimento

- Arthur Suzuki
- Arthur Freire
- Brandon Hunt
- Bruno Ribeiro
- Gabriel Rodrigues
- Ian Nunes
- João Novaes
- Lucas Rosati
- Luís Melo
- Vinicius Petribu

---