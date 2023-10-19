package org.example.personagens;

import lombok.Getter;
import org.example.util.Dado;

public abstract class Personagem {
    @Getter
    private int pontosDeVida;
    @Getter
    private int forca;
    @Getter
    private int defesa;
    @Getter
    private int agilidade;

    public Personagem(int pontosDeVida, int forca, int defesa, int agilidade) {
        this.pontosDeVida = pontosDeVida;
        this.forca = forca;
        this.defesa = defesa;
        this.agilidade = agilidade;
    }

    public int calcularIniciativa() {
        return Dado.rolarD10() + this.getAgilidade();
    }

    public int calcularFatorAtaque() {
        return Dado.rolarD10() + this.getAgilidade() + this.getForca();
    }

    public int calcularFatorDefesa() {
        return Dado.rolarD10() + this.getAgilidade() + this.getDefesa();
    }

    public abstract int calcularDano();

    public void receberDano(int dano) {
        this.pontosDeVida -= dano;
    }
}
