package org.example.personagens.herois;

import org.example.personagens.Personagem;
import org.example.util.Dado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Guerreiro extends Personagem {
    private static Logger LOGGER = LoggerFactory.getLogger(Guerreiro.class);

    public Guerreiro(int pontosDeVida, int forca, int defesa, int agilidade) {
        super(pontosDeVida, forca, defesa, agilidade);
        LOGGER.debug(String.format("PV: %d, F: %d, DEF: %d, AG: %d", pontosDeVida, forca, defesa, agilidade));
    }

    private int calcularFatorDeDano() {
        return Dado.rolarD4() + Dado.rolarD4();
    }

    @Override
    public int calcularDano() {
        return calcularFatorDeDano() + getForca();
    }

    @Override
    public String toString() {
        return "Guerreiro";
    }
}
