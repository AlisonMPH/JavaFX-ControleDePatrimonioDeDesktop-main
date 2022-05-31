
package javafx.controledepatrimoniodedesktop.desktop;

import java.io.Serializable;


public class Localizacao implements Serializable{
       private int id;
       private String nome;
       private int capacidade;
       
    public Localizacao(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    
    @Override
    public String toString(){
        return getNome();
    }
}
