package br.com.Inatel.Personagens;

public class Inimigo extends AtributosPersonagem {
    private String nome;

    public Inimigo(String nome, int vida, int ataque, int sorte, double modificador) {
        super(vida, ataque, sorte, modificador);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSorte(double sorte) {
        this.sorte = (int) sorte;
    }

    public void setModificador(double modificador) {
        this.modificador = modificador;
    }
}
