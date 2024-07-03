package br.com.Inatel.Jogo;

import br.com.Inatel.Audio.AudioPlayer;
import br.com.Inatel.Excecoes.EntradaInvalidaException;
import br.com.Inatel.Excecoes.ObjectNotFoundException;
import br.com.Inatel.Personagens.Classes.Arqueiro;
import br.com.Inatel.Personagens.Classes.Guerreiro;
import br.com.Inatel.Personagens.Classes.Mago;
import br.com.Inatel.Personagens.Heroi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FluxoJogo {
    private Scanner scanner = new Scanner(System.in);
    static AudioPlayer audioPlayer = new AudioPlayer();

    public void iniciarJogo(int nivelDificuldade) {
        try {
            audioPlayer.play("C:\\Users\\alcid\\Downloads\\Hijo-de-la-luna-_instrumental_.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
        }

        System.out.println("Em um mundo onde o crepúsculo beija as montanhas e a aurora abraça os vales, você, um herói esquecido, desperta nas terras de Celesterra.");
        System.out.println("Com a memória fragmentada como um espelho quebrado, você se levanta em meio a ruínas ancestrais, ecoando histórias de eras perdidas.");
        Heroi heroi = escolherClasse();
        desenvolverHistoria(nivelDificuldade, heroi);
    }

    private Heroi escolherClasse() {
        while (true) {
            try {
                System.out.println("Três símbolos místicos surgem diante de você, cada um representando um caminho de poder e redenção:");
                System.out.println("1. Guerreiro - O símbolo da espada, prometendo força e coragem.");
                System.out.println("2. Arqueiro - O símbolo do arco, oferecendo precisão e fortuna.");
                System.out.println("3. Mago - O símbolo do cajado, emanando sabedoria e vitalidade.");
                System.out.print("Escolha sua classe (1-3): ");

                int escolhaClasse = scanner.nextInt();
                scanner.nextLine();  // Consumir nova linha

                if (escolhaClasse < 1 || escolhaClasse > 3) {
                    throw new EntradaInvalidaException("Opção inválida, escolha uma das opções fornecidas.");
                }

                switch (escolhaClasse) {
                    case 1:
                        System.out.println("Você escolheu a classe Guerreiro.");
                        return new Guerreiro("Guerreiro");
                    case 2:
                        System.out.println("Você escolheu a classe Arqueiro.");
                        return new Arqueiro("Arqueiro");
                    case 3:
                        System.out.println("Você escolheu a classe Mago.");
                        return new Mago("Mago");
                }

            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, escolha uma das opções fornecidas.");
                scanner.nextLine();  // Consumir a entrada inválida
            } catch (EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void desenvolverHistoria(int nivelDificuldade, Heroi heroi) {
        System.out.println("Você embarca em sua jornada com nível de dificuldade: " + nivelDificuldade);

        Desafios desafios = new Desafios();

        desafios.primeiroDesafio(heroi, nivelDificuldade);

        System.out.println("Parabéns! Você completou a jornada em Celesterra!");
    }
}
