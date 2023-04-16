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

``` git clone https://github.com/Leorjh/miniautorizador.git ```
 - Na pasta raiz do projeto:

``` cd docker && docker-compose up -d ```

- Utilizando a IDE execute o arquivo de aplicação MiniautorizadorApplication.java

Tendo java 17  e maven instalado na máquina é possível buildar o projeto e rodar por comandos maven dentro da pasta raiz:

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
  ```


- PUT json exemplo:
  ```
  {
  "balance": "1000",
  "passwordCard": "pass123"
  }
  ```
## Card Controller
| Método | Path                    | Descrição                    |
|--------|-------------------------|------------------------------|
| GET    | /cartoes                | Busca todos os cartões       |
| GET    | /cartoes/id/{id}        | Busca o cartão pelo id       |
| GET    | /cartoes/{numeroCartao} | Busca o saldo do cartão      |
| POST   | /cartoes                | Criação de um novo cartão    |
| PUT    | /cartoes/{id}           | Atualiza um cartão pelo id * |
| DELETE | /cartoes/{id}           | Deleta um cartão pelo id     |

* *Só é possível atualizar o saldo, colocando a senha correta no body

## Transaction Controller

- Validação do número do cartão, senha e saldo
- Exemplo json para efetuar uma transação:

```
{
    "cardNumber": "123",
    "passwordCard": "pass123",
    "value": "50"
}
```
| Método | Path                    | Descrição                          |
|--------|-------------------------|------------------------------------|
| POST   | /transacoes             | Efetua uma transação para o cartão |

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

* Resposta:

Algumas observações feitas:
- Controller com toda a regra de negócio, montando e repassando o domínio para aplicação. É complicado mexer aqui e de conseguir manter;
- A camada de aplicação repassando apenas a informação para o repositório;
- Nenhum teste encontrado, ficando difícil manter a integridade das informações e o sucesso dos processos;
- Não observei preocupação com a segurança.

Primeiro procuraria pela documentação e informações sobre tarefas e melhorias. Entenderia do que se trata de fato essa aplicação e seus requisitos principais. Após entender melhor todo o cenário, começaria a mexer um pouco no código. Separando a camada de lógica, modulando e tornando mais fácil de mexer e manter. Removeria o que não fosse necessário, como essa parte onde diz que a aplicação apenas repassa o objeto ao repositório. Inclusão de testes unitários e teste automatizados. Monitoramento da aplicação, para entender melhor a saude. Práticas de segurança, ainda mais se tratando de processamento de informações sensíveis.

2. Descreva quais são as principais limitações ao se adotar servidores de aplicação em uma arquitetura orientada a microsserviços.

* Resposta:

As principais limitações ao adotar microserviços são:
- Aumento da complexidade;
- Performance;
- Aplicação sendo um monolíto tudo está muito amarrado, sendo díficil de manter e alterar;
- Monitoramento;
- Dependência, tornando-se díficil caso seja necessário alguma migração.

O ideal seria possuir os microserviços em containers sem dependências e utilizar por exemplo Kubernetes para sua orquestação.
3. Atualmente, diversas aplicações escritas em Java estão deixando de serem desenvolvidas para rodarem em servidores (JBoss, Tomcat), adotando ferramentas que disponibilizam um servidor embutido na própria ferramenta. Quais são os principais desafios ao se tomar uma decisão dessas? Justifique sua resposta.

* Resposta:

Os principais desafios são:

- Migração e configuração correta da aplicação;
- Escalabilidade, garantir que a aplicação será escalonável, pois adotando servidor na própria ferramenta os recursos podem ficar mais limitados;
- Pode ser mais difícil a criação de uma arquitetura de microserviços;
- Garantir que a aplicação estará nos padrões.

Em suma, em termos de configurações e implatação utilização de servidores embutidos pode ser mais fácil, porém é necessário analisar todo o contexto a fim de verificar se é a melhor solução.
# Desafios propostos
Desafios (não obrigatórios):
* é possível construir a solução inteira sem utilizar nenhum if. Só não pode usar *break* e *continue*!
  * Resposta: É possível trocando os ifs, utilizando mais enums, uso de lambdas e uso de ternário. Entre outras soluções conjuntas.
* como garantir que 2 transações disparadas ao mesmo tempo não causem problemas relacionados à concorrência?
  Exemplo: dado que um cartão possua R$10.00 de saldo. Se fizermos 2 transações de R$10.00 ao mesmo tempo, em instâncias diferentes da aplicação, como o sistema deverá se comportar?
	* Utilizando-se de mecanismo de transações como o ACID (Atomicidade, Consistência, Isolamento e Durabilidade)
    * Uso de concorrência, permitindo utilização de locks e bloqueando alguns registros. Assim se uma transação estiver sendo feita o sistema não ira permitir outra até que a primeira seja finalizada.

# Utilização de banco de dados H2

- Se quiser utilizar o banco H2 para a aplicação, substituindo o uso do MySQL no docker compose:
	- Adicione a dependência ao pom.xml:
```
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>
```
- E altere as properties no application.properties:
```
spring.datasource.url=jdbc:h2:mem:miniauth
spring.datasource.driverClassName=org.h2.DriverYES
spring.datasource.username=sa
spring.datasource.password=123
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2
```
