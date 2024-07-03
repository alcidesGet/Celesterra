package br.com.Inatel.Jogo;

import br.com.Inatel.Audio.AudioEfeito;
import br.com.Inatel.Audio.AudioPlayer;
import br.com.Inatel.Combate.SistemaCombate;
import br.com.Inatel.Excecoes.EntradaInvalidaException;
import br.com.Inatel.Excecoes.ObjectNotFoundException;
import br.com.Inatel.Itens.*;
import br.com.Inatel.Personagens.Classes.Arqueiro;
import br.com.Inatel.Personagens.Classes.Guerreiro;
import br.com.Inatel.Personagens.Classes.Mago;
import br.com.Inatel.Personagens.Heroi;
import br.com.Inatel.Personagens.Inimigos.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Desafios extends FluxoJogo {

    private Scanner scanner = new Scanner(System.in);
    private static AudioEfeito audioEfeito;
    AudioPlayer audioPlayer = new AudioPlayer();

    void primeiroDesafio(Heroi heroi, int nivelDificuldade) {
        audioEfeito = new AudioEfeito(); // Inicializar o AudioEfeito
        System.out.println("Primeiro Desafio: O Enigma do Velho Sábio");
        System.out.println("Despertando nas ruínas, você sente um chamado místico que ressoa em seu coração. Caminhando pelas pedras ancestrais, cada passo ecoa histórias de um passado distante.");
        System.out.println("As ruínas se desvanecem e você adentra uma floresta, onde a luz do sol luta para penetrar a densa copa das árvores. A atmosfera é carregada de magia e mistério.");
        System.out.println("Nas sombras da floresta esquecida, um velho sábio aguarda por aqueles de coração puro e mente aguçada. Ele apresenta um enigma, um portal para o conhecimento antigo:");
        System.out.println("\"Eu falo sem boca, ouço sem ouvidos. Não tenho corpo, mas venho vivo com o vento. O que sou eu?\"");

        while (true) {
            try {
                System.out.print("Escolha sua resposta (1. Eco, 2. Sombra, 3. Pedra): ");

                int resposta = scanner.nextInt();
                scanner.nextLine();  // Consumir nova linha

                if (resposta < 1 || resposta > 3) {
                    throw new EntradaInvalidaException("Opção inválida, escolha uma das opções fornecidas.");
                }

                if (resposta == 1) {
                    System.out.println("Resposta correta! O velho sábio lhe concede suas bênçãos.");
                    System.out.println("Você ganhou duas poções de vida!");
                    heroi.adicionarItem(new PocaoDeVida());
                    heroi.quantidadePocao(2);
                    Item ultimoItemAdicionado = heroi.getInventario().get(heroi.getInventario().size() - 1); // Último item adicionado ao inventário
                    System.out.println(ultimoItemAdicionado.getNome());
                    System.out.println(ultimoItemAdicionado.getDescricao());

                    System.out.println("O Velho Sábio revela que sua amnésia é obra de um feitiço poderoso lançado pelo Senhor do Caos, um ser malévolo que busca dominar Celesterra.");
                    System.out.println("Para recuperar suas memórias e derrotá-lo, você precisará enfrentar seus servos e recuperar fragmentos de um antigo artefato conhecido como o Espelho da Verdade.");
                    segundoDesafio(heroi, nivelDificuldade);
                } else {
                    FluxoJogo.audioPlayer.stop();
                    try {
                        audioEfeito.play("D:\\MusicaJogo\\gameover.wav");
                    } catch (ObjectNotFoundException e) {
                        System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
                    }
                    System.out.println("Resposta errada. Você se perde na névoa da ignorância.");
                    try {
                        // Pausa por 3 segundos (3000 milissegundos)
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Fim de jogo.");
                    System.exit(0);
                }
                break; // sair do loop se uma opção válida foi escolhida
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, escolha uma das opções fornecidas.");
                scanner.nextLine(); // Consumir a entrada inválida
            } catch (EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void segundoDesafio(Heroi heroi, int nivelDificuldade) {
        System.out.println("Segundo Desafio: Combate Contra um Bandido");
        System.out.println("Com a bênção do sábio, você se dirige para fora da floresta. A densa vegetação cede lugar a um vasto campo aberto.");
        System.out.println("Montanhas majestosas se erguem ao longe, e um rio cristalino serpenteia pelo vale. O som da água corrente e o canto dos pássaros acompanham sua jornada.");
        System.out.println("Em uma encruzilhada, um bandido surge, sua lâmina refletindo a lua traiçoeira. Ele busca o que você tem de mais valioso: sua vida e seu destino.");
        System.out.println("A batalha é inevitável, e a vitória lhe trará recursos para continuar sua saga. A derrota, no entanto, será um golpe duro em sua jornada.");

        Bandido bandido = new Bandido();
        FluxoJogo.audioPlayer.stop();
        try {
            audioPlayer.play("D:\\MusicaJogo\\bf1.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");;
        }
        SistemaCombate.iniciarCombate(heroi, bandido, nivelDificuldade);

        if (heroi.getVida() > 0) {
            System.out.println("Você derrotou o Bandido!");
            System.out.println("O bandido é um ex-soldado que foi corrompido pelo Senhor do Caos, prometendo riqueza e poder em troca de sua lealdade.");
            System.out.println("Ele guarda um dos fragmentos do Espelho da Verdade, sem saber seu verdadeiro valor.");
            terceiroDesafio(heroi, nivelDificuldade);
        } else {
            System.out.println("Você foi derrotado pelo Bandido.");
            audioPlayer.stop();
            try {
                audioEfeito.play("D:\\MusicaJogo\\gameover.wav");
            } catch (ObjectNotFoundException e) {
                System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
            }
            try {
                // Pausa por 3 segundos (3000 milissegundos)
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fim de jogo.");
            System.exit(0);
        }
    }

    private void terceiroDesafio(Heroi heroi, int nivelDificuldade) {
        audioPlayer.stop();
        try {
            audioPlayer.play("C:\\Users\\alcid\\Downloads\\Hijo-de-la-luna-_instrumental_.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
        }
        System.out.println("Terceiro Desafio: Dilema Moral");
        System.out.println("Após derrotar o bandido, você encontra uma trilha que leva a uma pequena vila. A vila é simples, com casas de madeira e campos de cultivo.");
        System.out.println("As crianças brincam nas ruas, e os moradores olham com curiosidade enquanto você passa.");
        System.out.println("Quando a noite cai sobre Celesterra, você testemunha um ato de injustiça: um ladrão roubando um órfão.");

        while (true) {
            try {
                System.out.print("Escolha sua ação (1. Interferir, 2. Ignorar): ");

                int escolha = scanner.nextInt();
                scanner.nextLine();  // Consumir nova linha

                if (escolha < 1 || escolha > 2) {
                    throw new EntradaInvalidaException("Opção inválida, escolha uma das opções fornecidas.");
                }

                if (escolha == 1) {
                    System.out.println("Você decide interferir e mudar o destino do órfão! Como agradecimento ele lhe entrega seu único bem, um amuleto mágico dado a ele por sua mãe.");
                    heroi.adicionarItem(new AmuletoSolNascente());

                    Item ultimoItemAdicionado = heroi.getInventario().get(heroi.getInventario().size() - 1); // Último item adicionado ao inventário
                    System.out.println(ultimoItemAdicionado.getNome()); // Nome do amuleto
                    System.out.println(ultimoItemAdicionado.getDescricao());

                    heroi.getNome(); //Chamar nome e descrição do amuleto aqui
                    heroi.aumentarVida(10);
                    quartoDesafio(heroi, nivelDificuldade);
                } else {
                    System.out.println("Você decide passar despercebido, evitando conflito, mas carregando o peso da inação.");
                    quartoDesafio(heroi, nivelDificuldade);
                }
                break; // sair do loop se uma opção válida foi escolhida
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, escolha uma das opções fornecidas.");
                scanner.nextLine(); // Consumir a entrada inválida
            } catch (EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void quartoDesafio(Heroi heroi, int nivelDificuldade) {
        audioPlayer.stop();
        try {
            audioPlayer.play("D:\\MusicaJogo\\bf1.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
        }
        System.out.println("Quarto Desafio: Combate Contra o General Exilado");
        System.out.println("Após deixar a vila, você segue em direção às montanhas. Atravessando pontes antigas e trilhas traiçoeiras, você chega ao topo, onde a vista de uma cidade em ruínas se revela.");
        System.out.println("A cidade, outrora grandiosa, agora está coberta de vegetação e escombros, vestígios de batalhas passadas.");
        System.out.println("No coração da cidade em ruínas, o General Exilado, banido de seu próprio reino, desafia você para um duelo de honra.");
        System.out.println("Ele é um reflexo do que você pode se tornar: poderoso, mas sozinho. Derrotá-lo não é apenas uma questão de sobrevivência, mas também de compreender o peso do poder e da responsabilidade.");

        GeneralExilado general = new GeneralExilado();
        SistemaCombate.iniciarCombate(heroi, general, nivelDificuldade);

        if (heroi.getVida() > 0) {
            System.out.println("Você derrotou o General Exilado!");
            System.out.println("O General Exilado foi um herói outrora, mas foi manipulado pelo Senhor do Caos a cometer traições que o levaram à desgraça.");
            System.out.println("Ele busca redenção através do combate, acreditando que sua única chance de paz é na morte. Ele possui outro fragmento do Espelho da Verdade.");
            quintoDesafio(heroi, nivelDificuldade);
        } else {
            System.out.println("Você foi derrotado pelo General Exilado.");
            audioPlayer.stop();
            try {
                audioEfeito.play("D:\\MusicaJogo\\gameover.wav");
            } catch (ObjectNotFoundException e) {
                System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
            }
            try {
                // Pausa por 3 segundos (3000 milissegundos)
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fim de jogo.");
            System.exit(0);
        }


    }


    private void quintoDesafio(Heroi heroi, int nivelDificuldade) {
        audioPlayer.stop();
        try {
            audioPlayer.play("C:\\Users\\alcid\\Downloads\\Hijo-de-la-luna-_instrumental_.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
        }
        System.out.println("Quinto Desafio: Novo Enigma do Guardião das Estrelas");
        System.out.println("Após deixar as ruínas, você segue uma trilha que leva a um antigo observatório no topo de uma montanha.");
        System.out.println("O observatório é um lugar de sabedoria e mistério, onde as estrelas são estudadas e segredos do cosmos são revelados.");
        System.out.println("Um guardião das estrelas apresenta um enigma para você:");
        System.out.println("\"Eu posso construir castelos, mas não sou feito de pedra. Eu posso derrubar reis, mas não uso espadas. O que sou eu?\"");

        while (true) {
            try {
                System.out.print("Escolha sua resposta (1. Tempo, 2. Vento, 3. Areia): ");

                int resposta = scanner.nextInt();
                scanner.nextLine();  // Consumir nova linha

                if (resposta < 1 || resposta > 3) {
                    throw new EntradaInvalidaException("Opção inválida, escolha uma das opções fornecidas.");
                }

                if (resposta == 1) {
                    System.out.println("Resposta correta! O guardião lhe concede um mapa estelar que aponta para o covil do Senhor do Caos.");
                    System.out.println("Siga avançando pela cordilheira.");
                    sextoDesafio(heroi, nivelDificuldade);
                } else {
                    System.out.println("Resposta errada. Você perde a chance de obter o mapa estelar e se perde nos caminhos de sua própria história.");
                    audioPlayer.stop();
                    try {
                        audioEfeito.play("D:\\MusicaJogo\\gameover.wav");
                    } catch (ObjectNotFoundException e) {
                        System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
                    }
                    try {
                        // Pausa por 3 segundos (3000 milissegundos)
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Fim de jogo.");
                    System.exit(0);
                }
                break; // sair do loop se uma opção válida foi escolhida
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, escolha uma das opções fornecidas.");
                scanner.nextLine(); // Consumir a entrada inválida
            } catch (EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }





    private void sextoDesafio(Heroi heroi, int nivelDificuldade) {
        audioPlayer.stop();
        try {
            audioPlayer.play("D:\\MusicaJogo\\bf1.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
        }
        System.out.println("Sexto Desafio: Combate Contra o Velho Bruxo");
        System.out.println("Após deixar a montanha, você segue em direção a uma cordilheira. Escalando penhascos íngremes e atravessando florestas densas, você encontra a entrada de uma caverna profunda a qual aparenta ser o único caminho à se seguir.");
        System.out.println("O ar dentro da caverna é pesado e as paredes são cobertas por runas antigas.");
        System.out.println("Nas profundezas da caverna, um Velho Bruxo estranhamente lhe aguarda, tecendo sua magia negra e criando ilusões que desafiam a realidade.");

        VelhoBruxo bruxo = new VelhoBruxo();
        SistemaCombate.iniciarCombate(heroi, bruxo, nivelDificuldade);

        if (heroi.getVida() > 0) {
            System.out.println("Você derrotou o Velho Bruxo!");
            System.out.println("O Velho Bruxo é um antigo estudioso da magia, agora corrompido pela promessa de poder imortal pelo Senhor do Caos.");
            System.out.println("Ele guarda o terceiro fragmento do Espelho da Verdade.");
            setimoDesafio(heroi, nivelDificuldade);
        } else {
            System.out.println("Você foi derrotado pelo Velho Bruxo.");
            audioPlayer.stop();
            try {
                audioEfeito.play("D:\\MusicaJogo\\gameover.wav");
            } catch (ObjectNotFoundException e) {
                System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
            }
            try {
                // Pausa por 3 segundos (3000 milissegundos)
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fim de jogo.");
            System.exit(0);
        }
    }



    private void setimoDesafio(Heroi heroi, int nivelDificuldade) {
        audioPlayer.stop();
        try {
            audioPlayer.play("C:\\Users\\alcid\\Downloads\\Hijo-de-la-luna-_instrumental_.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
        }
        System.out.println("Sétimo Desafio: Nova Escolha Moral");
        System.out.println("Com o mapa estelar em mãos e enfim saíndo da caverna, você atravessa um vale sombrio.");
        System.out.println("No meio do caminho, você encontra um grupo de aldeões acampados, temerosos pois um grupo de mercenários os ameaça.");
        System.out.println("Os mercenários estão a serviço do Senhor do Caos, contratados para espalhar medo e manter as áreas sob controle.");

        while (true) {
            try {
                System.out.print("Escolha sua ação (1. Ajudar os aldeões, 2. Seguir seu caminho): ");

                int escolha = scanner.nextInt();
                scanner.nextLine();  // Consumir nova linha

                if (escolha < 1 || escolha > 2) {
                    throw new EntradaInvalidaException("Opção inválida, escolha uma das opções fornecidas.");
                }

                if (escolha == 1) {
                    System.out.println("Você decide ajudar os aldeões a se defenderem dos mercenários, ganhando a gratidão e um anel mágico.");
                    heroi.adicionarItem(new AnelTrevoCincoFolhas());

                    Item ultimoItemAdicionado = heroi.getInventario().get(heroi.getInventario().size() - 1); // Último item adicionado ao inventário
                    System.out.println(ultimoItemAdicionado.getNome());
                    System.out.println(ultimoItemAdicionado.getDescricao());

                    heroi.aumentarSorte(5);
                    oitavoDesafio(heroi, nivelDificuldade);
                } else {
                    System.out.println("Você decide seguir seu caminho, deixando-os à própria sorte.");
                    oitavoDesafio(heroi, nivelDificuldade);
                }
                break; // sair do loop se uma opção válida foi escolhida
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, escolha uma das opções fornecidas.");
                scanner.nextLine(); // Consumir a entrada inválida
            } catch (EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }






    private void oitavoDesafio(Heroi heroi, int nivelDificuldade) {
        audioPlayer.stop();
        try {
            audioPlayer.play("D:\\MusicaJogo\\bf1.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
        }
        System.out.println("Oitavo Desafio: Emboscada de Criaturas Sombrias");
        System.out.println("Deixando os aldeões para trás, você chega à uma vasta planície.");
        System.out.println("À noite, o céu estrelado brilha intensamente, mas uma sensação de perigo iminente o envolve. Você pressente que algo sombrio está à espreita.");
        System.out.println("Sob um céu estrelado, criaturas sombrias emergem das fendas da terra, sedentas por caos.");
        System.out.println("Sua habilidade em sobreviver a esta emboscada definirá seu legado como herói ou como mais uma sombra perdida entre as lendas de Celesterra.");

        CriaturaSombria criaturasSombrias = new CriaturaSombria();
        SistemaCombate.iniciarCombate(heroi, criaturasSombrias, nivelDificuldade);

        if (heroi.getVida() > 0) {
            System.out.println("Você sobreviveu à emboscada das Criaturas Sombrias!");
            System.out.println("As criaturas são servas diretas do Senhor do Caos, enviadas para impedir que você recupere o último fragmento do Espelho da Verdade, que revela a localização de seu covil.");
            nonoDesafio(heroi, nivelDificuldade);
        } else {
            System.out.println("Você foi derrotado pelas Criaturas Sombrias.");
            audioPlayer.stop();
            try {
                audioEfeito.play("D:\\MusicaJogo\\gameover.wav");
            } catch (ObjectNotFoundException e) {
                System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
            }
            try {
                // Pausa por 3 segundos (3000 milissegundos)
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fim de jogo.");
            System.exit(0);
        }
    }




    private void nonoDesafio(Heroi heroi, int nivelDificuldade) {
        audioPlayer.stop();
        try {
            audioPlayer.play("C:\\Users\\alcid\\Downloads\\Hijo-de-la-luna-_instrumental_.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
        }
        System.out.println("Nono Desafio: Nova Escolha Moral");
        System.out.println("Continuando sua jornada, você chega a uma floresta encantada ao amanhecer. A floresta é um lugar de beleza e perigo, onde criaturas mágicas tanto benignas quanto malignas habitam.");
        System.out.println("Quando, ao escutar um triste gemido, em uma clareira, você encontra um elfo ferido, caçado por bestas malignas.");
        System.out.println("O elfo relatou que é um guardião da floresta e que as bestas sanguinárias tinham sido enviadas pelo Senhor do Caos para consumir aquele belo lugar.");
        System.out.println("Porém, ao defender a floresta, o elfo se feriu gravemente e solicita desesperadamente sua ajuda para sair daquela situação e derrotar as maldosas criaturas.");
        System.out.println("Um novo aliado pode ser crucial na luta contra o Senhor do Caos. Contudo, ajudar pode atrasar sua jornada.");

        while (true) {
            try {
                System.out.print("Escolha sua ação (1. Ajudar o elfo, 2. Seguir seu caminho): ");

                int escolha = scanner.nextInt();
                scanner.nextLine();  // Consumir nova linha

                if (escolha < 1 || escolha > 2) {
                    throw new EntradaInvalidaException("Opção inválida, escolha uma das opções fornecidas.");
                }

                if (escolha == 1) {
                    System.out.println("Você decide ajudar o elfo, ganhando a quarta parte do espelho da verdade.");
                    System.out.println("O fragmento do espelho estava sendo guardado por ele há séculos, aguardando o guerreiro escolhido que acabaria com as eras de escuridão.");
                    System.out.println("Também revela que sem o fragmento seria impossível encontrar a localização do Senhor do Caos.");
                    System.out.println("Além disso, como retribuição ele lhe cede uma arma correspondente à sua classe:");
                    if (heroi instanceof Guerreiro) {
                        System.out.println("Você ganha uma Espada Encantada - Uma espada que brilha com um brilho mágico, aumentando a força e a coragem do portador.");
                        heroi.adicionarItem(new EspadaLuzLuar());

                        Item ultimoItemAdicionado = heroi.getInventario().get(heroi.getInventario().size() - 1); // Último item adicionado ao inventário
                        System.out.println(ultimoItemAdicionado.getNome());
                        System.out.println(ultimoItemAdicionado.getDescricao());

                        heroi.aumentarAtaque(10);
                    } else if (heroi instanceof Arqueiro) {
                        System.out.println("Você ganha um Arco Encantado - Um arco de um material inusitado, proporcionando precisão e poder nas flechas disparadas.");
                        heroi.adicionarItem(new ArcoOssoDragao());

                        Item ultimoItemAdicionado = heroi.getInventario().get(heroi.getInventario().size() - 1); // Último item adicionado ao inventário
                        System.out.println(ultimoItemAdicionado.getNome());
                        System.out.println(ultimoItemAdicionado.getDescricao());

                        heroi.aumentarAtaque(10);
                    } else if (heroi instanceof Mago) {
                        System.out.println("Você ganha um Cajado Encantado - Um cajado que emana energia mágica, aumentando a sabedoria e a astúcia do usuário.");
                        heroi.adicionarItem(new CajadoPedrilhante());

                        Item ultimoItemAdicionado = heroi.getInventario().get(heroi.getInventario().size() - 1); // Último item adicionado ao inventário
                        System.out.println(ultimoItemAdicionado.getNome());
                        System.out.println(ultimoItemAdicionado.getDescricao());

                        heroi.aumentarAtaque(10);
                    }
                    decimoDesafio(heroi, nivelDificuldade);
                } else {
                    System.out.println("Você decide seguir seu caminho, deixando o elfo perecer e preservando suas forças para a batalha final.");
                    decimoDesafio(heroi, nivelDificuldade);
                }
                break; // sair do loop se uma opção válida foi escolhida
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida, escolha uma das opções fornecidas.");
                scanner.nextLine(); // Consumir a entrada inválida
            } catch (EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private void decimoDesafio(Heroi heroi, int nivelDificuldade) {
        audioPlayer.stop();
        try {
            audioPlayer.play("D:\\MusicaJogo\\bff.wav");
        } catch (ObjectNotFoundException e) {
            System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
        }
        System.out.println("Décimo Desafio: Combate Contra o Senhor do Caos");
        System.out.println("Seguindo as pistas do mapa estelar, você atravessa a floresta encantada.");
        System.out.println("Ao sair da floresta você chega à um abismo, onde o espelho da verdade agora completo começa a vibrar, se expandindo e se tornando um portal");
        System.out.println("Ao atravessá-lo você avista o covil do Senhor do Caos, um castelo sombrio envolto em tempestades mágicas.");
        System.out.println("O castelo é uma fortaleza de desespero, onde o mal reina supremo.");
        System.out.println("No ápice de sua jornada, o Senhor do Caos se revela, um ser de poder insondável e intenções nefastas.");
        System.out.println("Este confronto é o clímax de sua saga, onde cada golpe e feitiço ecoará através da história. A vitória trará paz a Celesterra, enquanto a derrota selará seu destino nas mãos do caos.");

        SenhorDoCaos senhorDoCaos = new SenhorDoCaos();
        SistemaCombate.iniciarCombate(heroi, senhorDoCaos, nivelDificuldade);

        if (heroi.getVida() > 0) {
            System.out.println("Você derrotou o Senhor do Caos!");
            System.out.println("Ao derrotar o Senhor do Caos, você recupera todas as suas memórias e descobre que era um antigo protetor de Celesterra, conhecido como Elyon, o Guardião da Luz, esquecido pelo tempo.");
            System.out.println("Você relembra que, há eras, o Senhor do Caos, outrora chamado Lukan, era seu aliado e irmão de armas, ambos lutando juntos para proteger Celesterra.");
            System.out.println("Lukan, porém, foi corrompido pela ganância, buscando poder com uma entidade das trevas chamada Nocturnis.");
            System.out.println("Ao ver o caminho de perdição de seu amigo, você, Elyon, tentou impedí-lo, mas Nocturnis já havia dominado Lukan.");
            System.out.println("Elyon e Lukan travaram uma batalha incessante, mas no fim, o mal venceu. A força sobre-humana de Nocturnis prevaleceu sobre a força apenas humana de Elyon, que pereceu nas ruínas de Azaroth, onde toda a batalha aconteceu.");
            System.out.println("Séculos se passaram e, quando o mal estava a ponto de dominar Celesterra, a deusa antiga, Elaria, se compadeceu e decidiu cortar o mal pela raiz, trazendo você, Elyon, de volta à vida para concluir o que começou.");
            System.out.println("Ao fim da luta, Elaria se revela e conta toda a verdade: seu sacrifício não foi em vão, e agora, a verdadeira batalha foi vencida.");
            System.out.println("Seu nome, Elyon, será sinônimo de esperança e coragem, e sua história, assim como a de Lukan, será passada de geração em geração como uma lenda de redenção e triunfo sobre o mal.");
            System.out.println("A terra floresce novamente sob sua proteção, e a paz reina, com sua figura sendo celebrada em canções e contos, lembrando a todos do poder da luz e da redenção.");

        } else {
            System.out.println("O Senhor do Caos triunfa, e você cai em batalha. Celesterra mergulha na escuridão, mas seu sacrifício se torna um sussurro de resistência, uma fagulha de esperança de que um novo herói possa se levantar das cinzas.");
            audioPlayer.stop();
            try {
                audioEfeito.play("D:\\MusicaJogo\\gameover.wav");
            } catch (ObjectNotFoundException e) {
                System.out.println("Arquivo de áudio não encontrado. Este trecho do game seguirá sem som.");
            }
            try {
                // Pausa por 3 segundos (3000 milissegundos)
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Fim de jogo.");
            System.exit(0);
        }
    }
}
