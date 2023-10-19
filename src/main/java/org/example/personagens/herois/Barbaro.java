package org.example.personagens.herois;

import org.example.personagens.Personagem;
import org.example.util.Dado;

public class Barbaro extends Personagem {
    public Barbaro(int pontosDeVida, int forca, int defesa, int agilidade) {
        super(pontosDeVida, forca, defesa, agilidade);
    }

    private int calcularFatorDeDano() {
        return Dado.rolarD6() + Dado.rolarD6();
    }

    @Override
    public int calcularDano() {
        return calcularFatorDeDano() + getForca();
    }

    @Override
    public String toString() {
        return "Bárbaro";
    }
}
