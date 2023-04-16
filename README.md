# Miniautorizador
Miniautorizador desafio técnico para empresa VR

Pequena amostra de um autorizador de transações para cartões.

Detalhes do desafio e alguns requisitos podem ser visualizados no arquivo DESAFIO.md

# Principais tecnologias usadas no projeto
 - Java
 - SpringBoot
 - Maven
 - Docker
 - jUnit
 - Lombok
 - Hibernate
 - JPA
 - Swagger
# Instalações necessárias
 - JDK 17
 - Git
 - Docker
 - IDE
# Passos para rodar o projeto
- Baixe o projeto do github

``` Git clone https://github.com/Leorjh/miniautorizador.git ```
 - Na pasta raiz do projeto:

``` cd docker && docker-compose up -d ```

- Utilizando a IDE execute o arquivo de aplicação MiniautorizadorApplication.java

Tendo java 17 instalado na máquina é possível buildar o projeto e rodar por comandos maven dentro da pasta raiz:

``` mvn clean compile ```

``` mvn clean spring-boot:run ```
# Swagger
 - http://localhost:8080/swagger-ui/index.html#/
# Rotas e validações
- POST json exemplo:
  ```
  {
  "cardNumber": "123",
  "passwordCard": "pass123"
  }
- PUT json exemplo:
  ```
  {
  "balance": "1000",
  "passwordCard": "pass123"
  }
## Card Controller
| Método | Path                    | Descrição                    |
|--------|-------------------------|------------------------------|
| GET    | /cartoes                | Busca todos os cartões       |
| GET    | /cartoes/id/{id}        | Busca o cartão pelo id       |
| GET    | /cartoes/{numeroCartao} | Busca o saldo do cartão      |
| POST   | /cartoes                | Criação de um novo cartão    |
| PUT    | /cartoes/{id}           | Atualiza um cartão pelo id * |
| DELETE | /cartoes/{id}           | Deleta um cartão pelo id     |

* *Só é possível atualizar o saldo e colocando a senha correta no body

# Formatação do projeto
 - Classe principal
 - Controller
 - DTO
 - Entity
 - Enums
 - Exeception
 - Mapper
 - Repository
 - Service
 - Camada de testes
# Próximos passos
- Criação da entidade de pessoa
- Vinculação da pessoa ao cartão
- Novos testes
- Monitoramento da aplicação
- Disponibilização na nuvem
- Pipeline de deploys
- Uso de filas para trabalhar de forma assíncrona
- Frontend
- Token de validação
# Perguntas feitas
1. Recebemos um código desenvolvido por terceiros de um sistema que possui alto volume de lógica de negócio e apresenta as seguintes características:
- O sistema recebe requisições REST, está dividido em camadas e possui classes de domínio;
- O controller recebe a requisição e está com toda lógica de negócio. Monta e repassa o domínio para a aplicação;
- A aplicação tem a responsabilidade de repassar o objeto pronto para o repositório;
- O repositório apenas persiste os objetos mapeados do hibernate através de spring data;
- O domínio apenas faz o mapeamento para o BD;
- Nenhum teste unitário foi escrito.
- O sistema está escrito em java para rodar como spring boot.

Apresente observações/problemas sobre essa solução.
Comente qual(is) a(s) sua(s) estratégia(s) para melhorar este sistema em termos de qualidade e manutenção. Justifique suas decisões.
# Desafios propostos
Desafios (não obrigatórios):
* é possível construir a solução inteira sem utilizar nenhum if. Só não pode usar *break* e *continue*!
  * Resposta: É possível trocando os ifs, utilizando mais enums, uso de lambdas e uso de ternário. Entre outras soluções conjuntas.
* como garantir que 2 transações disparadas ao mesmo tempo não causem problemas relacionados à concorrência?
  Exemplo: dado que um cartão possua R$10.00 de saldo. Se fizermos 2 transações de R$10.00 ao mesmo tempo, em instâncias diferentes da aplicação, como o sistema deverá se comportar?
	* Utilizando-se de mecanismo de transações como o ACID (Atomicidade, Consistência, Isolamento e Durabilidade)
    * Uso de concorrência, permitindo utilização de locks e bloqueando alguns registros. Assim se uma transação estiver sendo feita o sistema não ira permitir outra até que a primeira seja finalizada.
