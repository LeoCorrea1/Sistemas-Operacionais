/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author laboratorio
 */
public class Fila {
    
    private Jogador jogador1;
    private Jogador jogador2;
    private int tamanho;
    boolean continuar;
    int j;
    
    private BlockingQueue<Jogador> fila; 
    
    public Fila(int tamanho){
        this.tamanho = tamanho;
        this.fila = new ArrayBlockingQueue<>(tamanho); 
    }
    public void inserir(Jogador jogador,boolean[] pode) throws InterruptedException {
        
        continuar= true;
        for (int i=0;continuar;i++){
            
            if (pode[i] == true){
                
                pode[i] = false;
                continuar = false;
            }
            
        }
        
        fila.put(jogador);
        
        System.out.println("Jogador inserido: "+jogador.getNickname()+" "+jogador.getId());
        
    }
    
    public void coletar(boolean[] pode) throws InterruptedException {
        
        jogador1 = fila.take();
        jogador2 = fila.take();
        this.j = this.tamanho -1 ;
        
        while(pode[j]){
            
            if (j>1){
                
                j--;  
            }
        }
        
        System.out.println("Jogadores pareados: "+jogador1.getNickname()+" ID :"+jogador1.getId()+
                           " - "+ jogador2.getNickname()+" ID: "+jogador2.getId());
        System.out.println("Partida Iniciada !");
        
        pode[j] = true;
        pode[j-1] = true;
        
    }
    
}
