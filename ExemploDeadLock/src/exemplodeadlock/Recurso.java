
package exemplodeadlock;

public class Recurso {
    
    private String nome;
    
    public Recurso(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
}