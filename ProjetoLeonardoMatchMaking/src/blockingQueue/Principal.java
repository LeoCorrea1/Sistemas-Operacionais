/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blockingQueue;

/**
 *
 * @author laboratorio
 */
public class Principal {
    
    public static void main(String[] args) throws InterruptedException {
        
        int tamanhoFila = 30;
                
        boolean[] pode = new boolean[tamanhoFila];

        Fila buffer = new Fila(tamanhoFila);
        
        for (int i=0;i<tamanhoFila;i++) pode[i] = true;
       
        
        for(int t = 0 ; t < tamanhoFila ; t++){  
                    
                buffer.inserir(new Jogador(t*3,"Player" + t), pode);    
        }
   
                        
        
        Thread consumidor = new Thread(new Servidor(buffer,pode));
        
        Thread produtor = new Thread(new Servidor(buffer,pode));
        
        
        buffer.coletar(pode);
        
        produtor.start();
        consumidor.start();
        
    }
    
}
