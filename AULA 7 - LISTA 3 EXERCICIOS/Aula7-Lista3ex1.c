#include <pthread.h>
#include <stdio.h>
#include <stdlib.h> 

#define NUM_THREADS 2

int base = 4;
int altura = 2;

void *CalculaPerimetro(void *threadid) {
    long tid = (long)threadid;
    int perimetro = 2 * (base + altura);
    printf("Thread Perimetro - Perimetro do Terreno: %d\n", perimetro);
    pthread_exit(NULL);
}

void *CalculaArea(void *threadid) {
    long tid = (long)threadid;
    int area = base * altura;
    printf("Thread Area - Area do Terreno: %d\n", area);
    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
    pthread_t threads[NUM_THREADS];
    int status;

    printf("criando Thread Area\n");
    status = pthread_create(&threads[0], NULL, CalculaArea, (void *)0);
    
    printf("criando Thread Perimetro\n");
    status = pthread_create(&threads[1], NULL, CalculaPerimetro, (void *)1);

    for (long i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
    }
    
    return 0;
}
