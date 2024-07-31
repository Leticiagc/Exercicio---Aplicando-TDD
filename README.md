# Exercicio---Aplicando-TDD
Exercício da cadeira de verificação e validação de software, com objetivo de praticar TDD

Processador de Contas e Sistema de Ingressos
Este projeto implementa dois sistemas distintos: um Processador de Contas e um Sistema de Venda de Ingressos para shows, ambos desenvolvidos em Java utilizando Gradle para gerenciamento de dependências e JUnit para testes unitários. A seguir, detalhamos as especificações de cada sistema.

Processador de Contas
O Processador de Contas tem como objetivo verificar todas as contas de uma fatura e determinar se a fatura deve ser marcada como "PAGA" ou "PENDENTE" com base nos pagamentos recebidos.

Especificação:
Fatura:

Contém data, valor total e nome do cliente.
Conta:

Contém código da conta, data e valor pago.
Pagamento:

Ao receber uma lista de contas, o processador cria um "pagamento" associado a uma fatura.
Tipos de pagamento:
Boleto ("BOLETO")
Cartão de crédito ("CARTAO_CREDITO")
Transferência bancária ("TRANSFERENCIA_BANCARIA")
Regras para Boletos:

Não podem ter valor inferior a R$ 0,01 nem superior a R$ 5.000,00.
Boletos pagos após a data da conta são considerados atrasados e têm um acréscimo de 10% no valor do pagamento.
Validação da Fatura:

Se a soma de todos os pagamentos for igual ou superior ao valor da fatura, a fatura é marcada como "PAGA".
Caso contrário, a fatura é marcada como "PENDENTE".
Pagamentos por cartão de crédito só são válidos se a data da conta for pelo menos 15 dias anteriores à data da fatura.
Os demais pagamentos são válidos se a data da conta for igual ou anterior à data da fatura.
Exemplos:
Exemplo 1: Fatura de R$ 1.500,00 (20/02/2023) com três contas de R$ 500,00, R$ 400,00 e R$ 600,00, pagas por boleto na data da fatura. Resultado: "PAGA".
Exemplo 2: Fatura de R$ 1.500,00 (20/02/2023) com uma conta de R$ 700,00 (cartão de crédito) e outra de R$ 800,00 (transferência). Resultado: "PAGA".
Exemplo 3: Fatura de R$ 1.500,00 (20/02/2023) com uma conta de R$ 700,00 (cartão de crédito) e outra de R$ 800,00 (transferência). Resultado: "PENDENTE".
Sistema de Ingressos
O Sistema de Ingressos gerencia a venda de ingressos para shows, incluindo diferentes tipos de ingressos e a aplicação de descontos.

Especificação:
Show:

Contém data, artista, cachê, total de despesas de infraestrutura, lotes de ingressos e se é realizado em data especial.
Ingressos:

Tipos de ingressos: VIP, MEIA_ENTRADA, NORMAL.
Percentuais: 20-30% VIP, 10% MEIA_ENTRADA, restante NORMAL.
Preços:
VIP custa o dobro do NORMAL.
MEIA_ENTRADA custa metade do NORMAL.
Lotes de Ingressos:

Contêm um id, conjunto de ingressos e desconto aplicável (máximo 25%, válido para VIP e NORMAL).
Venda de Ingressos:

Deve ser possível marcar ingressos como vendidos ou não.
Relatório do Show:

Número de ingressos vendidos de cada tipo.
Receita líquida, considerando descontos, despesas de infraestrutura e cachê do artista.
Status financeiro:
LUCRO: receita líquida positiva.
ESTÁVEL: receita líquida zero.
PREJUÍZO: receita líquida negativa.
Exemplo:
Exemplo: Show em data especial com cachê de R$ 1.000,00, despesas de R$ 2.000,00, e lote de 500 ingressos com 20% VIP, preço NORMAL R$ 10,00 e 15% de desconto. Todos os ingressos vendidos. Receita: R$ 4.925,00. Despesas: R$ 3.300,00. Resultado: "LUCRO" com lucro de R$ 1.625,00.
Tecnologias Utilizadas
Java: Linguagem de programação principal.
Gradle: Ferramenta de automação e gerenciamento de dependências.
JUnit: Biblioteca de testes unitários.
