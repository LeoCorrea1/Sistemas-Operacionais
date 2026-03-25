public class DownloadThread extends Thread {
    public void run() {
        System.out.println("iniciando download");

        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("download concluido");
        }
}
