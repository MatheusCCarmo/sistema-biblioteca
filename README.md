# Sistema Biblioteca
Sistema desenvolvido para o trabalho da disciplina Engenharia de Software I

Este trabalho estava focado na utilização de alguns padrões de projeto, foram utilizados alguns padrões como Command, Façade, Singleton e Observer


## Trecho do enunciado


### 3. Funcionalidades
* 1. O sistema deve permitir o empréstimo de livros. Durante o empréstimo, o usuário informará o
comando “emp” seguido do código do usuário e do código do livro, separados por espaço em
branco. Ex.: “emp 123 100”. Caso o usuário tenha uma reserva feita previamente por ele para
o dado livro, a reserva deve ser excluída e o empréstimo efetivado. Ao final do procedimento
o sistema deve emitir uma mensagem de sucesso ou insucesso, que mencione o nome do
usuário e o título do livro. Se for uma mensagem de insucesso, ela deve também mencionar o
motivo do insucesso.
O empréstimo do livro só será concretizado para um aluno de graduação ou um aluno de
pós-graduação se: (i) houver a disponibilidade de algum exemplar daquele livro na
biblioteca; (ii) o usuário não estiver “devedor” de um livro em atraso; (iii) forem obedecidas
as regras específicas daquele tipo de usuário no que se refere à quantidade máxima de
empréstimos, de acordo com a Tabela 2; (iv) a quantidade de reservas existentes do livro for
menor do que a quantidade de exemplares disponíveis, caso o usuário não tenha reserva para
ele; (v) a quantidade de reservas for maior ou igual a de exemplares, mas uma das reservas é
do usuário; e (vi) o usuário não tiver nenhum empréstimo em curso de um exemplar daquele
mesmo livro.
O empréstimo do livro só será concretizado para um professor se: (i) houver a disponibilidade
de algum exemplar daquele livro na biblioteca; e (ii) o usuário não estiver “devedor” de um
livro em atraso.
Note que os professores não tem empréstimo negado caso haja reservas para aquele livro e
não tem limite da quantidade de livros que pode pegar emprestado.
É sabido que nesse tipo de domínio essas regras estão sujeitas a frequentes mudanças. Além
disso, podem surgir novos tipos de usuário para os quais as regras de empréstimo sejam
diferentes das já existentes para professor e alunos de graduação e de pós.

Tipo de Usuário | Limite de Empréstimos em Aberto
:-------------: | :----------------:
Aluno Graduação | 3 livros
Aluno Pós-Graduação | 4 livros


Tabela 2: Limites da quantidade de livros tomados como empréstimo

* 2. O sistema deve permitir a devolução de um dado livro. Durante a devolução, o usuário deve
digitar o comando “dev” seguido do código de identificação do usuário e do código de
identificação do livro emprestado. Ao final, o sistema deve emitir uma mensagem de sucesso
ou insucesso da devolução, que mencione o nome do usuário e o título do livro. A mensagem
de insucesso deve dizer o motivo. Nesse caso, o insucesso só ocorre se não houver
empréstimo em aberto daquele livro para aquele usuário.

* 3. O sistema deve permitir a reserva de um livro. Durante esse processo de reserva, o usuário
deve digitar o comando “res”, o código de identificação do usuário e o código de identificação
do livro que o usuário deseja reservar. Será permitida a reserva de apenas 3 livros por usuário.
Ao final, o sistema deve emitir uma mensagem de sucesso ou insucesso da reserva, que
mencione o nome do usuário e o título do livro. A mensagem de insucesso deve dizer o
motivo.

* 4. O sistema deve permitir que professores registrem que querem observar toda vez que
determinado livro tiver mais de duas reservas simultâneas. O professor se registra como
“observador” do livro que desejar. Toda vez que o livro tiver mais de duas reservas
simultâneas, o livro deve “avisar” aos “observadores”. O observador deve simplesmente
registrar internamente quantas vezes ele foi notificado. No futuro, o sistema pode ser evoluído
de forma que permita outros tipos de usuários, por exemplo, coordenadores, que também
possam observar a reserva de livros. Implemente essa funcionalidade usando um padrão que
permita facilmente essa evolução no futuro. Para registrar um professor como observador de
um livro, o usuário deve digitar o comando “obs” seguido do código do usuário e do código
do livro. Não há necessidade de checar se o código do usuário se refere realmente a um
professor.

* 5. O sistema deve fornecer as seguintes consultas:
a. Dado o código de um livro, o sistema deve apresentar suas informações da seguinte
forma: (i) título, (ii) quantidade de reservas para aquele livro, e, se diferente de zero,
devem ser também apresentados o nome dos usuários que realizaram cada reserva, (iii)
para cada exemplar, deve ser apresentado seu código, seu status (disponível ou
emprestado), e em caso do exemplar estar emprestado deverá ser exibido o nome do
usuário que realizou o empréstimo, a data de empréstimo e a data prevista para
devolução. Para solicitar tal consulta, o usuário deverá digitar o comando “liv”, seguido
do código do livro.
b. Dado um usuário, o sistema deverá apresentar a lista de todos os seus empréstimos
correntes e passados, assim como de suas reservas. A listagem de cada empréstimo
deverá apresentar o título do livro, a data do empréstimo, o status atual daquele
empréstimo (em curso ou finalizado) e a data da devolução já realizada ou prevista. A
listagem das reservas deverá apresentar o título do livro reservado e a data da solicitação
da reserva. Para solicitar tal consulta, o usuário deverá digitar o comando “usu”, seguido
do código do usuário.
c. Dado um professor, o sistema deve retornar a quantidade de vezes que ele foi
notificado sobre mais de duas reservas simultâneas em livros observados por ele. Para
solicitar tal consulta, o usuário deverá digitar o comando “ntf”, seguido do código do
usuário. Não há necessidade de checar se o código se refere realmente a um professor.

* 6. O usuário deve ter a opção de sair do sistema. Para isso, basta digitar o comando “sai”.

### 4. Exigências de Projeto
*1. O sistema NÃO deve se preocupar com a persistência de dados, ou seja, NÃO deve usar banco de
dados. Os objetos relativos aos dados de teste (Seção 7) deverão ser instanciados na memória no
momento da inicialização do sistema.

* 2. O sistema NÃO deve ter uma interface com o usuário gráfica. Todos os comandos deverão ser
fornecidos via linha de comando, e suas respostas devem ser mostradas no console.

* 3. O projeto deve ter uma classe responsável por ler os comandos e mostrar as respostas no console.
Essa classe deve se comunicar com a fachada do sistema por meio de um esquema de comandos,
projetados de acordo com o padrão de projeto “Command”.

* 4. A comunicação entre os comandos e as classes de negócio deverão ser feitos por meio de uma classe
fachada que também deverá ser um Singleton.

* 5. Evite o uso de “if “ou “switch” para selecionar entre implementações dos diferentes tipos de usuário.
Use polimorfismo para todas as diferenças entre os tipos de usuário. Em particular, use algum
padrão de projeto para implementar as diferentes regras para realização de empréstimo.
