public class Principal extends Thread {

    public Principal(String str) {
        super(str);
    }

    public void run() {
        for (int i = 0; i <30; i++) {
            System.out.println(i + " " + getName());
        }
        System.out.println(getName()+ " Terminou!");
    }

    public static void main(String args[]) {
        Principal Thread1 = new Principal("Primeira");
        Principal Thread2 = new Principal("Segunda");
        Thread1.start();
        Thread2.start();
        for(int i = 0; i < 20; i++)
            System.out.println(i + " Thread principal");
        System.out.println("THREAD PRINCIPAL - Término de execução");
    }
}
