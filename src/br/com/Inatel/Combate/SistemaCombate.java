package br.com.Inatel.Combate;

import br.com.Inatel.Excecoes.EntradaInvalidaException;
import br.com.Inatel.Excecoes.ObjectNotFoundException;
import br.com.Inatel.Jogo.FluxoJogo;
import br.com.Inatel.Personagens.Heroi;
import br.com.Inatel.Personagens.Inimigo;
import br.com.Inatel.Util.RoladorDeDados;
import br.com.Inatel.Audio.AudioEfeito;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SistemaCombate extends FluxoJogo {
    private static Scanner scanner = new Scanner(System.in);
    private static AudioEfeito audioEfeito;


    public static void iniciarCombate(Heroi heroi, Inimigo inimigo, int nivelDificuldade) {
        ajustarDificuldade(inimigo, nivelDificuldade);
        int vidaInicialHeroi = heroi.getVida();

        System.out.println("Combate iniciado: " + heroi.getNome() + " vs " + inimigo.getNome());

        audioEfeito = new AudioEfeito(); // Inicializar o AudioEfeito

        int opcao;
        while (true) {
            try {
                System.out.println("Pressione 1 para rolar dados, 2 para utilizar poção de vida ou 0 para sair:");
                opcao = scanner.nextInt();

                if (opcao < 0 || opcao > 2) {
                    throw new EntradaInvalidaException("Opção inválida. Pressione 1 para rolar dados, 2 para utilizar poção de vida ou 0 para sair.");
                }

                if (opcao == 1) {
                    System.out.println("Rolar dados!");

                    FutureTask<Integer> rolagemHeroi = new FutureTask<>(() -> RoladorDeDados.rolarD20(heroi.getSorte()));
                    FutureTask<Integer> rolagemInimigo = new FutureTask<>(() -> RoladorDeDados.rolarD20(inimigo.getSorte()));

                    Thread threadHeroi = new Thread(rolagemHeroi);
                    Thread threadInimigo = new Thread(rolagemInimigo);

                    threadHeroi.start();
                    threadInimigo.start();

                    try {
                        int resultadoHeroi = rolagemHeroi.get();
                        int resultadoInimigo = rolagemInimigo.get();

                        System.out.println("Herói rolou: " + resultadoHeroi);
                        System.out.println("Inimigo rolou: " + resultadoInimigo);

                        if (resultadoHeroi > 10) {
                            System.out.println("Herói acerta!");
                            try {
                                audioEfeito.play("C:\\Users\\alcid\\Downloads\\strong-hit-36455.wav"); // Toca o áudio a cada acerto do herói
                            } catch (ObjectNotFoundException e) {
                                System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
                            }
                            Danos danos = calcularDano(heroi, inimigo);
                            inimigo.setVida(inimigo.getVida() - danos.getDanoHeroi());
                            System.out.println(heroi.getNome() + " causa " + danos.getDanoHeroi() + " de dano a " + inimigo.getNome() + ". Vida do inimigo: " + inimigo.getVida());

                            if (inimigo.getVida() <= 0) {
                                System.out.println(inimigo.getNome() + " foi derrotado!");
                                heroi.setVida(vidaInicialHeroi);
                                System.out.println("Vida do herói restaurada para: " + heroi.getVida());
                                break;
                            }
                        } else {
                            System.out.println("Herói erra!");
                        }

                        if (resultadoInimigo > 10) {
                            System.out.println("Inimigo acerta!");
                            try {
                                audioEfeito.play("C:\\Users\\alcid\\Downloads\\sword-slash-and-swing-185432.wav"); // Toca o áudio a cada acerto do inimigo
                            } catch (ObjectNotFoundException e) {
                                System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
                            }
                            Danos danos = calcularDano(heroi, inimigo);
                            heroi.setVida(heroi.getVida() - danos.getDanoInimigo());
                            System.out.println(inimigo.getNome() + " causa " + danos.getDanoInimigo() + " de dano a " + heroi.getNome() + ". Vida do herói: " + heroi.getVida());

                            if (heroi.getVida() <= 0) {
                                System.out.println(heroi.getNome() + " foi derrotado!");
                                break;
                            }
                        } else {
                            System.out.println("Inimigo erra!");
                        }

                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                } else if (opcao == 2) {
                    heroi.usarPocaoDeVida();
                } else if (opcao == 0) {
                    System.out.println("Jogo encerrado pelo usuário.");
                    System.exit(0);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Pressione 1 para rolar dados, 2 para utilizar poção de vida ou 0 para sair.");
                scanner.nextLine(); // Consumir a entrada inválida
            } catch (EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Danos calcularDano(Heroi heroi, Inimigo inimigo) {
        int danoHeroi = (int) (heroi.getAtaque() * heroi.getModificador());
        int danoInimigo = (int) (inimigo.getAtaque() * inimigo.getModificador());

        return new Danos(danoHeroi, danoInimigo);
    }

    private static void ajustarDificuldade(Inimigo inimigo, int nivelDificuldade) {
        switch (nivelDificuldade) {
            case 1:
                inimigo.setVida(inimigo.getVida() - 10);
                inimigo.setAtaque(inimigo.getAtaque() - 5);
                inimigo.setSorte(inimigo.getSorte() - 1);
                inimigo.setModificador(inimigo.getModificador() - 0.2);
                break;
            case 2:
                // Nenhuma alteração necessária
                break;
            case 3:
                inimigo.setVida(inimigo.getVida() + 10);
                inimigo.setAtaque(inimigo.getAtaque() + 5);
                inimigo.setSorte(inimigo.getSorte() + 5);
                inimigo.setModificador(inimigo.getModificador() + 0.3);
                break;
        }
    }
}
