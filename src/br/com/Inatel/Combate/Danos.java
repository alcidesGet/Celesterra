package br.com.Inatel.Combate;

public class Danos {
    private int danoHeroi;
    private int danoInimigo;

    public Danos(int danoHeroi, int danoInimigo) {
        this.danoHeroi = danoHeroi;
        this.danoInimigo = danoInimigo;
    }

    public int getDanoHeroi() {
        return danoHeroi;
    }

    public int getDanoInimigo() {
        return danoInimigo;
    }
}

