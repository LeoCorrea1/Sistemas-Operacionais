public class Mensagem {

    private boolean pronta = false;

    public synchronized void esperarMesagem() {
        while (!pronta) {
            try {
                System.out.println("Thread esperando mensagem...");
                wait();  //esperando até alguém chamar notify
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Mensagem recebida!");
    }

    public synchronized void enviarMensagem() {
        System.out.println("Preparando mensagem...");
        pronta = true;
        notify(); //acorda a thread que está esperando
        System.out.println("Mensagem enviada!");
    }
}