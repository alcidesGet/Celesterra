package br.com.Inatel.Jogo;

import br.com.Inatel.Audio.AudioPlayer;
import br.com.Inatel.Excecoes.EntradaInvalidaException;
import br.com.Inatel.Excecoes.ObjectNotFoundException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    public int exibirMenuInicial() {
        AudioPlayer audioPlayer = new AudioPlayer();
        try {
            audioPlayer.play("D:\\MusicaJogo\\musicamenu.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
        }

        while (true) {
            try {
                System.out.println("Bem-vindo ao jogo Celesterra!");
                System.out.println("Escolha o nível de dificuldade:");
                System.out.println("1. Fácil");
                System.out.println("2. Médio");
                System.out.println("3. Difícil");
                System.out.print("Escolha: ");

                int escolhaNivel = scanner.nextInt();
                scanner.nextLine();  // Consumir nova linha

                if (escolhaNivel < 1 || escolhaNivel > 3) {
                    throw new EntradaInvalidaException("Opção inválida, escolha uma das opções fornecidas.");
                }

                audioPlayer.stop();
                return escolhaNivel;

            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, escolha uma das opções fornecidas.");
                scanner.nextLine(); // Consumir a entrada inválida
            } catch (EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void iniciarJogo(int escolhaNivel) {
        FluxoJogo jogo = new FluxoJogo();
        jogo.iniciarJogo(escolhaNivel);
    }
}
