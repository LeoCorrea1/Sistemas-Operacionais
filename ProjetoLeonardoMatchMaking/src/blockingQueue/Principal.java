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
        
        int tamanhoFila = 20;
                
        boolean[] pode = new boolean[tamanhoFila];

        Fila buffer = new Fila(tamanhoFila);
        
        for (int i=0;i<tamanhoFila;i++) pode[i] = true;
        
        
        Thread Jogadores = new Thread(new Jogador(buffer,pode));

        
        
        Jogador P1 = new Jogador(1,"Player1");
        Jogador P2 = new Jogador(2,"Player2");
        

                        
        
        Thread consumidor = new Thread(new Servidor(buffer,pode));
        
        Thread produtor = new Thread(new Servidor(buffer,pode));
        
        
        buffer.inserir(P1, pode);        buffer.inserir(P2, pode);
        
        buffer.coletar(pode);
        
        produtor.start();
        consumidor.start();
        
    }
    
}
