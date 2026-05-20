/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blockingQueue;

/**
 *
 * @author laboratorio
 */
public class Jogador implements Runnable{
    
    private int id;
    
    private String nickname;
    
    private boolean[] pode;
    
    private Fila fila;
    
     public Jogador (Fila fila,boolean[] pode){
        
        this.fila = fila;
        this.pode = pode;
        
    }

    
    public Jogador (int id, String nickname){
        
        this.id = id;
        this.nickname = nickname;
        
    }
    
    
    
    @Override
    public void run(){
        
        try{
            
            fila.inserir(new Jogador(this.id,this.nickname),pode);
            
        }catch(InterruptedException e){
            
            Thread.currentThread().interrupt();
            
        }
        
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
    
}
