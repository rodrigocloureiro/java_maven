package org.example.view;

import org.example.core.*;
import org.example.exceptions.InvalidChoiceException;
import org.example.exceptions.PlayerNotFoundException;
import org.example.personagens.Personagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static final Logger LOGGER = LoggerFactory.getLogger(Menu.class);

    private void exibirOpcoes() {
        LOGGER.info("O que você deseja?");
        LOGGER.info("1- Jogar | 2- Gerar relatório");
    }

    private int escolha(Scanner sc) {
        int escolha = 0;
        do {
            try {
                exibirOpcoes();
                escolha = sc.nextInt();
                if (escolha < 1 || escolha > 2) {
                    escolha = 0;
                    throw new InvalidChoiceException("Opção inválida. Tente novamente.");
                }
            } catch (InvalidChoiceException ex) {
                LOGGER.error(ex.getMessage());
            } catch (InputMismatchException ex) {
                LOGGER.error("Informe um valor INTEIRO.");
            } finally {
                sc.nextLine(); // limpando o buffer
            }
        } while (escolha == 0);
        return escolha;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String nickname = GerenciadorNickname.nickname(sc);

        switch (escolha(sc)) {
            case 1 -> {
                Personagem heroi = GerenciadorDePersonagens.criarHeroi(sc);
                Personagem monstro = GerenciadorDePersonagens.criarMonstroAleatorio();
                LOGGER.info(String.format("Você está contra o monstro \"%s\"\n", monstro));

                Jogo jogo = new Jogo(heroi, monstro);
                jogo.jogar();

                Log log = new Log(nickname, jogo);
                log.gerarLog();
            }
            case 2 -> {
                try {
                    Relatorio gerador = new Relatorio(nickname);
                    gerador.gerarRelatorio();
                } catch (PlayerNotFoundException | IOException ex) {
                    LOGGER.error(ex.getMessage());
                    run();
                }
            }
        }
    }
}
