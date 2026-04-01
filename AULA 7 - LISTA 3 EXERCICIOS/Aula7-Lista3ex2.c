#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS 3
#define N 5  

void *MostrarIdentificacao(void *threadid) {
    long tid = (long)threadid;
    
    for (int i = 0; i < N; i++) {
 
        printf("Thread ID : %ld - Vezes que Apareci : %d\n", tid+1, i);
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
    
    pthread_exit(NULL);
}
