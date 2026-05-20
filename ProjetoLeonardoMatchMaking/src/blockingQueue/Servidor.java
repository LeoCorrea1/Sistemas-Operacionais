/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blockingQueue;

/**
 *
 * @author laboratorio
 */
public class Servidor implements Runnable{
    
    private Fila fila;
    private boolean[] pode;
    
    public Servidor(Fila fila,boolean[] pode){
        
        this.fila = fila;
        this.pode = pode;
        
    }
    
    @Override
    public void run(){
        
        try{
            
            for (int i=0;i<20;i++){
                
                fila.coletar(pode);
                Thread.sleep(3);
                
            }
            
        } catch(InterruptedException e){
            
            Thread.currentThread().interrupt();
            
        }
        
    }
    
}
