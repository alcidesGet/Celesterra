package br.com.Inatel.Personagens;

public abstract class AtributosPersonagem {
    protected int vida;
    protected int ataque;
    protected int sorte;
    protected double modificador;

    public AtributosPersonagem(int vida, int ataque, int sorte, double modificador) {
        this.vida = vida;
        this.ataque = ataque;
        this.sorte = sorte;
        this.modificador = modificador;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getSorte() {
        return sorte;
    }

    public void setSorte(int sorte) {
        this.sorte = sorte;
    }

    public double getModificador() {
        return modificador;
    }

    public void setModificador(double modificador) {
        this.modificador = modificador;
    }
}
