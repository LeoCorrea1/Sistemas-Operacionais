#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS 8
#define N 10 

int C = 0;

void *MostrarIdentificacao(void *threadid) {
    long tid = (long)threadid;
    
    for (int i = 0; i < N; i++) {
 
        printf("Thread ID : %ld - Somei na Compartilhada : %d Vezes\n", tid, i );
        C++;
    }
    
    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
    pthread_t threads[NUM_THREADS];
    int status;
    long i;

    for (i = 0; i < NUM_THREADS; i++) {
        printf("thread %ld Criada\n" , i+1);
        status = pthread_create(&threads[i], NULL, MostrarIdentificacao, (void *)i);

    }
    for (i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
    }
    
    printf("\nsoma total da variavel compartilhada : %i\n" , C);
    pthread_exit(NULL);
}
