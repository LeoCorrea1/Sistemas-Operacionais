public class Principal2 {
    public static void main(String[] args) {

        Mensagem msg = new Mensagem();

        Thread t1 = new Thread(() -> {
            msg.esperarMesagem();
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msg.enviarMensagem();
        });

        t1.start();
        t2.start();
    }
}