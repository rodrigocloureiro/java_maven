package org.example.personagens.monstros;

import org.example.personagens.Personagem;
import org.example.util.Dado;

public class MortoVivo extends Personagem {
    public MortoVivo(int pontosDeVida, int forca, int defesa, int agilidade) {
        super(pontosDeVida, forca, defesa, agilidade);
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
        return "Morto-Vivo";
    }
}
