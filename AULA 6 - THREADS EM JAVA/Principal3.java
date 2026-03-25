import org.w3c.dom.ls.LSOutput;

public class Principal3 {

    public static void main(String[] args) {

        DownloadThread download = new DownloadThread();
        download.start();

        try{
            download.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("continuando execuçao da main...");
    }
}
