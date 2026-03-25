public class PrintThread extends Thread {

    private int sleepTime;

    public PrintThread(String str) {
        super(str);
        sleepTime = (int) (Math.random() * 5000);
        System.out.println(getName() +" dormirá "+ sleepTime + "ms...");
    }

    public void run() {
        try {
            System.out.println(getName() + " indo dormir.");
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
        System.out.println(getName() + " TERMINOU!");
    }
}
