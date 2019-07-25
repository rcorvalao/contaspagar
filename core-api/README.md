# contaspagar - core api

Back end Rest para contas a pagar


## Executa as seguintes regras:

* Todos os campos são obrigatórios
* No cadastro de contas a pagar terá que verificar se a conta está em atraso, caso esteja será incluído a seguinte regra:
** Dias em atraso Multa Juros / dia
** até 3 dias 2% 0,1%
** superior a 3 dias 3% 0,2%
** superior a 5 dias 5% 0,3%
* A quantidade de dias em atraso e a regra de para o cálculo devem ser persistidos.


## Serviços
Inclusão de conta a pagar
Nome: Texto
Valor Original: Numeral
Data de Vencimento: Data
Data de Pagamento: Data


Listagem das contas cadastradas
Nome: Texto
Valor Original: Numeral
Valor Corrigido: Numeral
Quantidade de dias de atraso: Numeral
Data de Pagamento: Data

