/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exemplodeadlock;


public class Processo1 extends Thread {

    private Recurso recurso1;
    private Recurso recurso2;

    public Processo1(Recurso r1, Recurso r2) {
        this.recurso1 = r1;
        this.recurso2 = r2;
    }

    @Override
    public void run() {
        synchronized (recurso1) {
            System.out.println("Processo 1 bloqueou " + recurso1.getNome());
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            
            synchronized (recurso2) {
                System.out.println("Processo 1 bloqueou " + recurso2.getNome());
            }
        }
    }
}