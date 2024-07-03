package br.com.Inatel.Jogo;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        int escolhaNivel = menu.exibirMenuInicial();
        menu.iniciarJogo(escolhaNivel);
    }
}
