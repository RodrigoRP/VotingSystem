# VotingSystem

## Descrição.
Este projeto tem por finalidade funcionarios escolherem onde irão almoçar.

- 2 tipos de usuários: admin e user
- Admin tem certos privilegios como deletar e atualizar um funcionário.
- É realizado a validação dos campos cpf e email;
- Os funcionarios podem votar apenas uma vez ao dia.
- É determinado um limite de horario para votacao neste caso 11:30
- Os funcionarios podem alterar o seu voto, até o horario limite.
- A documentacao do projeto foi realizado com o Swagger.
- O banco de dados que foi utilizado - H2
- Ao gerar o projeto, automaticamente o banco H2 é populado para testes.
- Todos os endpoints exceto o criar um novo funcionario necessita de um token.
- A autenticacao dos funcionarios é realizado atraves de um token, o metódo utilizado foi o JWT (JSON Web Token).

# Endpoint Votacao

- O funcionario informa o id do restaurante que deseja votar e o seu token.
- No endpoint /api/vote/winner traz como resposta o id do restaurante vencedor
 

### ADMIN

{
	"email" : "admin@admin.com.br",
	"password": "123456"
}

### User

{
	"email" : "mic@terra.com.br",
	"password": "123456"
}
