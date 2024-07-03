package br.com.Inatel.Personagens;
import br.com.Inatel.Audio.AudioEfeito;
import br.com.Inatel.Excecoes.ObjectNotFoundException;
import br.com.Inatel.Itens.Item;
import java.util.ArrayList;



public abstract class Heroi extends AtributosPersonagem {
    private String nome;
    private ArrayList<Item> inventario;
    private int quantidadePocoes;
    private int vidaMaxima = getVida();
    private static AudioEfeito audioEfeito;



    public Heroi(String nome, int vida, int ataque, int sorte, double modificador) {
        super(vida, ataque, sorte, modificador);
        this.nome = nome;
        this.inventario = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarItem(Item item) {
        inventario.add(item);
    }

    public ArrayList<Item> getInventario() {
        return inventario;
    }

    public void aumentarVida(int quantidade) {
        setVida(getVida() + quantidade);
        vidaMaxima = vidaMaxima + quantidade;
    }

    public void aumentarAtaque(int quantidade) {
        setAtaque(getAtaque() + quantidade);
    }

    public void aumentarSorte(int quantidade) {
        setSorte(getSorte() + quantidade);
    }

    public void quantidadePocao(int quantidade) {
        quantidadePocoes += quantidade;
    }
    public void usarPocaoDeVida() {
        audioEfeito = new AudioEfeito(); // Inicializar o AudioEfeito
        if (quantidadePocoes > 0) {
            try {
                audioEfeito.play("C:\\Users\\alcid\\Downloads\\health-pickup-6860.wav");
            } catch (ObjectNotFoundException e) {
                System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
            }

            if (getVida()<vidaMaxima-15) {
                aumentarVida(15); // Recupera 15 pontos de vida
                quantidadePocoes--;
                System.out.println("Você usou uma poção de vida. Vida atual: " + getVida());
            }
            else{
                int calculo;
                calculo = vidaMaxima - getVida();
                aumentarVida(calculo);
                quantidadePocoes--;
                System.out.println("Você usou uma poção de vida. Vida atual: " + getVida());
            }
        } else {
            System.out.println("Não há poções de vida disponíveis!");
        }
    }


}
