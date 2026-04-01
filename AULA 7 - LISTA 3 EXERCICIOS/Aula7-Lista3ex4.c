#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int vetor[5];
int contadora = 0;
int pos_escrita = 0;
int pos_leitura = 0;

void *Escritora(void *arg) {
    while (1) {
        if (contadora < 5) {
            vetor[pos_escrita] = 100;
            pos_escrita = (pos_escrita + 1) % 5;

            int temp = contadora;
            usleep(10000);
            contadora = temp + 1;
            
            printf("ESCREVI | Itens no vetor: %d\n", contadora);
        }
        usleep(100000);
    }
}

void *Leitora(void *arg) {
    while (1) {
        if (contadora > 0) {
            int dado = vetor[pos_leitura];
            pos_leitura = (pos_leitura + 1) % 5;
          
            int temp = contadora;
            usleep(10000);
            contadora = temp - 1;
            
            printf("Vetor REMOVI  | Itens no vetor: %d\n", contadora);
        }
        usleep(100000);
    }
}

int main() {
    pthread_t t1, t2;

    pthread_create(&t1, NULL, Escritora, NULL);
    pthread_create(&t2, NULL, Leitora, NULL);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    return 0;
}
