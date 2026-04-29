# Exercícios de Sistemas Operacionais: Concorrência e Sincronização

Este documento apresenta resoluções de questões sobre processos concorrentes, condições de corrida e o clássico problema dos Leitores e Escritores, baseado na literatura de Tanenbaum.

---

## Questão 1: Concorrência e Variáveis Compartilhadas

Considere dois processos que acessam uma variável compartilhada `cont = 5`.

* **Processo A:** `cont = cont + 1`
* **Processo B:** `cont = cont - 1`

Ambos executam concorrentemente.

### a) Qual deveria ser o valor final?
O resultado ideal deveria ser **5**. Como uma operação incrementa e a outra decrementa o mesmo valor, se executadas de forma atômica ou sequencial, o estado final deve ser igual ao estado inicial.

### b) Possível execução com valor incorreto (Condição de Corrida)
Se ocorrer uma **condição de corrida (race condition)**, um processo pode ser interrompido pelo escalonador antes de completar todas as etapas da operação (Leitura -> Modificação -> Escrita).

**Exemplo de cenário problemático:**
1.  **Processo A** lê o valor de `cont` (5).
2.  O sistema interrompe o Processo A e troca para o **Processo B**.
3.  **Processo B** lê o valor de `cont` (5), subtrai 1 e salva o resultado: `cont = 4`.
4.  O sistema volta para o **Processo A**.
5.  **Processo A**, que já tinha o valor 5 guardado em seu registrador, soma 1 e salva: `cont = 6`.

**Resultado:** O trabalho do Processo B foi "atropelado" e perdido, resultando em um valor final inconsistente.

---

## Questão 2: Seção Crítica

Dado o código: `saldo = saldo - valor;`

### 1) Esse trecho é uma seção crítica? Por quê?
**Sim.** É uma seção crítica porque acessa e modifica um recurso compartilhado (`saldo`). No nível do processador, essa linha não é uma operação única (atômica); ela envolve ler o saldo da memória, subtrair o valor e escrever de volta. Se múltiplos processos executarem isso simultaneamente sem proteção, os dados podem ser corrompidos.

### 2) O que pode acontecer se dois processos executarem isso ao mesmo tempo?
Pode ocorrer uma **condição de corrida**, levando a um saldo incorreto. Por exemplo, se dois saques de R$ 100,00 acontecerem ao mesmo tempo em uma conta com R$ 150,00, ambos podem ler o saldo de 150, calcular que há fundos suficientes, e atualizar o saldo para 50, quando o correto seria o saldo ficar negativo ou uma das operações ser negada.

---

## Questão 3: Problema dos Leitores e Escritores

Baseado na solução de Andrew S. Tanenbaum em *Sistemas Operacionais Modernos*.

### O Dilema
Como permitir leitura simultânea (vários leitores) mas garantir exclusividade total para escrita?
* **Leitores:** Podem ler juntos.
* **Escritor:** Precisa de acesso exclusivo. Se houver alguém lendo ou outro escrevendo, ele deve esperar.

### Implementação em C (Semáforos)

```c
typedef int semaphore;       /* Tipo semáforo */
semaphore mutex = 1;         /* Controla o acesso à variável 'rc' */
semaphore db = 1;            /* Controla o acesso à base de dados (database) */
int rc = 0;                  /* Read Count: número de processos lendo */

void reader(void) {
    while (TRUE) {
        down(&mutex);        /* Garante exclusividade para alterar 'rc' */
        rc = rc + 1;         /* Um leitor a mais */
        
        if (rc == 1) {       /* Se for o primeiro leitor a chegar... */
            down(&db);       /* ...ele trava o banco para os escritores */
        }
        
        up(&mutex);          /* Libera 'rc' para outros leitores */
        
        read_data_base();    /* SEÇÃO CRÍTICA: Acesso aos dados (leitura) */
        
        down(&mutex);        /* Garante exclusividade para alterar 'rc' */
        rc = rc - 1;         /* Um leitor a menos */
        
        if (rc == 0) {       /* Se for o último leitor a sair... */
            up(&db);         /* ...ele libera o banco para os escritores */
        }
        
        up(&mutex);          /* Libera 'rc' */
        
        use_data_read();     /* Região não crítica */
    }
}

void writer(void) {
    while (TRUE) {
        think_up_data();     /* Região não crítica */
        
        down(&db);           /* Tenta obter acesso exclusivo ao banco */
        write_data_base();   /* SEÇÃO CRÍTICA: Atualiza os dados */
        up(&db);             /* Libera o acesso exclusivo */
    }
}

```
###  O Problema do Starvation (Inanição)
A solução acima prioriza os leitores. Se houver um fluxo constante de novos leitores entrando antes que os antigos saiam, o contador rc nunca chegará a zero. Consequentemente, o Escritor nunca conseguirá dar o down(&db) e ficará bloqueado indefinidamente na fila. Isso é chamado de Starvation.
