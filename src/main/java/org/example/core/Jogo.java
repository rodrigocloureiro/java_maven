package org.example.core;

import lombok.Getter;
import org.example.personagens.Personagem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Jogo {
    private static final Logger LOGGER = LoggerFactory.getLogger(Jogo.class);

    @Getter
    private int rodada;
    @Getter
    private boolean ganhou;
    @Getter
    private LocalDateTime dataHoraPartida;
    @Getter
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy_HH:mm:ss"); // hh -> formato 12 horas | HH -> formato 24 horas;
    @Getter
    private Personagem heroi;
    @Getter
    private Personagem monstro;

    public Jogo(Personagem heroi, Personagem monstro) {
        this.rodada = 1;
        this.ganhou = false;
        this.dataHoraPartida = LocalDateTime.now();
        this.heroi = heroi;
        this.monstro = monstro;
    }

    private void realizarAtaque(Personagem atacante, Personagem defensor) {
        int dano = atacante.calcularDano();
        defensor.receberDano(dano);
        LOGGER.info("Fator Dano: " + (dano - atacante.getForca()));
        LOGGER.info("Dano Atacante: " + dano);
    }

    private void realizarRodada() {
        Personagem atacante;
        Personagem defensor;
        int iniciativaHeroi = 0, iniciativaMonstro = 0;

        while (iniciativaHeroi == iniciativaMonstro) {
            iniciativaHeroi = this.heroi.calcularIniciativa();
            iniciativaMonstro = this.monstro.calcularIniciativa();
        }

        LOGGER.info("Iniciativa Heroi: " + iniciativaHeroi);
        LOGGER.info("Iniciativa Monstro: " + iniciativaMonstro);

        if (iniciativaHeroi > iniciativaMonstro) {
            atacante = this.heroi;
            defensor = this.monstro;
            LOGGER.info(String.format("Heroi (%s) é o atacante\n", atacante));
        } else {
            atacante = this.monstro;
            defensor = this.heroi;
            LOGGER.info(String.format("Monstro (%s) é o atacante\n", atacante));
        }

        int fatorAtaque = atacante.calcularFatorAtaque();
        int fatorDefesa = defensor.calcularFatorDefesa();

        LOGGER.info("Fator de Ataque: " + fatorAtaque);
        LOGGER.info("Fator de Defesa: " + fatorDefesa);

        if (fatorAtaque > fatorDefesa) {
            this.realizarAtaque(atacante, defensor);
        }

        LOGGER.info(String.format("Vida Atacante (%s): %d\n", atacante, atacante.getPontosDeVida()));
        LOGGER.info(String.format("Vida Defensor (%s): %d\n", defensor, defensor.getPontosDeVida()));
    }

    public void jogar() {
        for (this.rodada = 1; this.heroi.getPontosDeVida() > 0 && this.monstro.getPontosDeVida() > 0; this.rodada++) {
            LOGGER.info(String.format("***** Rodada: %d *****\n", this.rodada));
            this.realizarRodada();
        }
        LOGGER.info("<<<<<<<<<<<<<<< BATALHA ENCERRADA >>>>>>>>>>>>>>>");
        this.exibirVencedor();
    }

    private void exibirVencedor() {
        if (this.heroi.getPontosDeVida() > 0) {
            LOGGER.info(String.format("O herói \"%s\" venceu.", this.heroi));
            this.ganhou = true;
        } else LOGGER.info(String.format("O monstro \"%s\" venceu.", this.monstro));
    }
}
