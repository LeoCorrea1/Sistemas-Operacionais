
package exemplodeadlock;


public class Principal {

    public static void main(String[] args) {
        
        Recurso teclado = new Recurso("Teclado");
        Recurso impressora = new Recurso("Impressora");
        
        Processo1 p1 = new Processo1(teclado, impressora);
        Processo2 p2 = new Processo2(teclado, impressora);
        
        p1.start();
        p2.start();
        
    }
}