class Contador {
    int valor = 0;
}

public class Exercicio3 extends Thread {
    Contador contador;

    public Exercicio3(Contador c) {
        this.contador = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            contador.valor++; 
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Contador compartilhado = new Contador();
        
        Exercicio3 t1 = new Exercicio3(compartilhado);
        Exercicio3 t2 = new Exercicio3(compartilhado);

        t1.start();
        t2.start();

        // Aguarda as duas terminarem antes de imprimir o resultado
        t1.join();
        t2.join();

        System.out.println("Valor esperado: 2000");
        System.out.println("Valor real: " + compartilhado.valor);
        
        // Resumo Race Condition: Ocorre quando várias threads acessam a mesma 
        // variável simultaneamente. Como a operação de soma não é "atômica" 
        // (ela lê, soma e grava), uma thread pode sobrescrever o valor da outra.
    }
}
