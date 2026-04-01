public class Exercicio2 extends Thread {
    public Exercicio2(String nome) {
        super(nome);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " contando: " + i);
        }
    }

    public static void main(String[] args) {
        Exercicio2 threadA = new Exercicio2("Thread A");
        Exercicio2 threadB = new Exercicio2("Thread B");

        threadA.start();
        threadB.start();
    }
}
