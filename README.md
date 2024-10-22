# **Sistema de Autenticação Biométrica**

Este projeto implementa um **sistema de autenticação biométrica** com arquitetura full-stack, utilizando **React** no frontend, **Spring Boot** com **Java** no backend e **PostgreSQL** como banco de dados. Ele integra dados biométricos (por exemplo, impressões digitais) de um dispositivo de hardware (**Arduino**) e credenciais tradicionais de login para autenticar os usuários.

## **Índice**
- [Visão Geral](#visão-geral)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Funcionalidades](#funcionalidades)
- [Estrutura do Banco de Dados](#estrutura-do-banco-de-dados)
- [Integração Biométrica](#integração-biométrica)
- [Instalação](#instalação)
- [Uso](#uso)
- [Licença](#licença)

---

## **Visão Geral**

Este projeto é desenvolvido para demonstrar como a autenticação biométrica pode ser integrada a uma aplicação web. Os usuários podem se autenticar via **nome de usuário**, **senha** e **dados biométricos** (impressão digital). Onde o próprio **Arduino** á digital e envia o id para o backend poder fazer sua lógica de acessos.

### **Principais Funcionalidades**:
- Autenticação de usuários via **biometria** e **credenciais**.
- Comunicação em tempo real entre o frontend (React) e o dispositivo de hardware (Arduino).
- Gerenciamento de funções de usuários (ex.: admin, usuário comum).
- Operações CRUD para usuários e posts.
- API REST com autenticação baseada em tokens (**JWT**).

---

## **Tecnologias Utilizadas**

### **Frontend**:
- **React**: Biblioteca JavaScript para construir interfaces interativas.
- **Axios**: Para realizar requisições HTTP e comunicar com o backend.
- **React Router**: Para navegação e roteamento dentro da aplicação.
- **Material-UI (MUI)**: Para estilizar o frontend com componentes pré-construídos.

### **Backend**:
- **Java** com **Spring Boot**: Para criar um backend escalável e robusto.
- **Spring Security**: Para gerenciar a autenticação e autorização.
- **JWT (JSON Web Tokens)**: Para gerenciar sessões de usuário e proteger as requisições da API.

### **Banco de Dados**:
- **PostgreSQL**: Banco de dados relacional para armazenar credenciais de usuários, dados biométricos e posts.

### **Hardware Biométrico**:
- **Arduino**: Processa dados de impressão digital e envia ao backend.

---

## **Funcionalidades**

- **Autenticação de Usuário**:
    - Suporte para login via nome de usuário, senha e verificação biométrica.
    - Acesso baseado em roles.

- **Gerenciamento de Posts**:
    - Os usuários podem criar, visualizar, atualizar e excluir posts.
    - Os posts são vinculados a usuários, e somente usuários autenticados podem interagir com seus posts.

- **Autenticação Biométrica**:
    - O dispositivo biométrico (Arduino) processa impressões digitais e retorna um ID para impressões digitais válidas.
    - Caso a impressão digital seja inválida, o login é negado.

- **Operações CRUD**:
    - **Gerenciamento de Usuários**: Admins podem criar, editar ou excluir usuários.
    - **Gerenciamento de Posts**: Os usuários podem gerenciar seus próprios posts.

---

## **Estrutura do Banco de Dados**

### **Tabela de Usuários (users)**
Armazena informações dos usuários, incluindo o ID biométrico associado à impressão digital e funções (roles).

```sql
create table if not exists users (
    id serial primary key,
    biometricId integer, -- ID associado aos dados biométricos
    username varchar(255) not null unique,
    password varchar(255) not null,
    status boolean default true,
    accessLevel varchar(255) not null,
    createdAt timestamp not null default current_timestamp,
    updatedAt timestamp not null default current_timestamp
);
```

### **Tabela de Posts (posts)**
Armazena os posts criados pelos usuários, com cada post vinculado a um usuário específico via `userId`.

```sql
create table if not exists posts (
    id serial primary key,
    title varchar(255) not null,
    content text not null,
    status boolean default true,
    userId integer not null,
    createdAt timestamp not null default current_timestamp,
    updatedAt timestamp not null default current_timestamp,
    foreign key (userId) references users(id)
);
```

---

## **Integração Biométrica**

O sistema integra-se com um dispositivo **Arduino** para lidar com o reconhecimento de impressões digitais:
- Quando uma impressão digital é escaneada, o Arduino retorna um **biometricId**.
- Esse **biometricId** é enviado para o backend, onde é comparado com os dados do usuário.
- Se a biometria for válida, o usuário é autenticado. Caso contrário, o acesso é negado.

### **Fluxo de Trabalho**:
1. O usuário coloca o dedo no scanner biométrico.
2. O Arduino processa a impressão digital e envia o **biometricId** de volta para a aplicação.
3. O frontend (React) comunica-se com o backend para verificar o **biometricId** e concluir o processo de login.

---

## **Instalação**

### **Pré-requisitos**:
- **Node.js** (para rodar o frontend)
- **Java 11+** (para o backend)
- **PostgreSQL** (para o banco de dados)
- **Arduino SDK** (para integração de hardware)

### **Passos**:

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/biometric-auth-system.git
   ```

2. Instale as dependências do frontend:
   ```bash
   cd frontend
   npm install
   ```

3. Instale as dependências do backend:
   ```bash
   cd backend
   mvn clean install
   ```

4. Configure o PostgreSQL:
    - Crie um banco de dados e atualize o arquivo `application.properties` com as credenciais corretas:
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/seubanco
      spring.datasource.username=seuusuario
      spring.datasource.password=suasenha
      ```

5. Inicie o backend:
   ```bash
   mvn spring-boot:run
   ```

6. Inicie o frontend:
   ```bash
   cd frontend
   npm start
   ```

7. Conecte o Arduino:
    - Certifique-se de que o Arduino está corretamente conectado e comunica-se com o backend via porta serial.

---

## **Uso**

1. **Registro de Usuário**:
    - Admins podem registrar usuários inserindo um nome de usuário, senha e escaneando a impressão digital do usuário.

2. **Login**:
    - Os usuários podem fazer login usando **nome de usuário e senha**, ou via **impressão digital**.

3. **Criar e Gerenciar Posts**:
    - Usuários autenticados podem criar, editar e excluir seus posts.

4. **Dashboard de Admin**:
    - Usuários admin têm acesso ao gerenciamento de usuários e podem atualizar ou remover usuários.

---

## **Licença**

Este projeto está licenciado sob a licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---

Este **README.md** cobre todos os aspectos principais do seu projeto e ajuda os usuários a entenderem e utilizarem a aplicação. Se precisar de ajustes ou detalhes adicionais, sinta-se à vontade para modificar!
