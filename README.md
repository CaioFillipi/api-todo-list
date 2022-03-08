
<h1>API TO-DO LIST</h1>

Essa API desenvolvida se assemelha a um todo-list(lista de tarefas), onde é possivel cadastrar, editar,excluir e listar as tarefas(CRUD).

<h2>Tecnologias utilizadas</h2>
<ul>
  <li>Java 11</li>
  <li>Spring</li>
  <li>Swagger</li>
  <li>H2 Database</li>
  <li>Docker</li>
  <li>Maven</li>
<ul>
  
  <h2>Executando a aplicação no Docker</h2>
    Esse projeto possui uma imagem disponibilizada no Docker Hub. Para executar ela no Docker basta digitar o comando abaixo:
    docker container run -d -p {PORT}:7071 caiofillipi/todo-list-api:1.0.0
    
  <h2>Observação</h2>
  Para acessar o swager da aplicação, basta acessar http://localhost:{PORT}/swagger-ui.html com a aplicação em execução
  
