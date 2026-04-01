public class Exercicio4 extends Thread {
    @Override
    public void run() {
        System.out.println("Iniciando download...");
        for (int i = 0; i <= 100; i += 10) {
            System.out.println("Progresso: " + i + "%");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Download concluído com sucesso!");
    }

    public static void main(String[] args) {
        Exercicio4 download = new Exercicio4();
        download.start();
    }
}
