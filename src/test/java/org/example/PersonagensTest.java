package org.example;

import org.example.personagens.Personagem;
import org.example.personagens.herois.Barbaro;
import org.example.personagens.herois.Guerreiro;
import org.example.personagens.herois.Paladino;
import org.example.personagens.monstros.Kobold;
import org.example.personagens.monstros.MortoVivo;
import org.example.personagens.monstros.Orc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonagensTest {
    @Test
    @DisplayName("Personagem não pode ser null")
    public void testaPersonagem() {
        Personagem guerreiro = new Guerreiro(12, 4, 3, 3);
        Personagem barbaro = new Barbaro(13, 6, 1, 3);
        Personagem paladino = new Paladino(15, 2, 5, 1);
        Personagem orc = new Orc(20, 6, 2, 2);
        Personagem mortoVivo = new MortoVivo(25, 4, 0, 1);
        Personagem kobold = new Kobold(20, 4, 2, 4);

        assertNotNull(guerreiro);
        assertNotNull(barbaro);
        assertNotNull(paladino);
        assertNotNull(orc);
        assertNotNull(mortoVivo);
        assertNotNull(kobold);
    }

    @Test
    @DisplayName("Deve testar os calculos envolvendo o Barbaro")
    public void testaBarbaro() {
        Personagem barbaro = new Barbaro(13, 6, 1, 3);
        assertTrue(barbaro.calcularIniciativa() >= 4 && barbaro.calcularIniciativa() <= 13);
        assertTrue(barbaro.calcularFatorAtaque() >= 10 && barbaro.calcularFatorAtaque() <= 19);
        assertTrue(barbaro.calcularFatorDefesa() >= 5 && barbaro.calcularFatorDefesa() <= 14);
        assertTrue(barbaro.calcularDano() >= 8 && barbaro.calcularDano() <= 18);
        barbaro.receberDano(10);
        assertEquals(3, barbaro.getPontosDeVida());
    }

    @Test
    @DisplayName("Deve testar os calculos envolvendo o Orc")
    public void testaOrc() {
        Personagem orc = new Orc(20, 6, 2, 2);
        assertTrue(orc.calcularIniciativa() >= 3 && orc.calcularIniciativa() <= 12);
        assertTrue(orc.calcularFatorAtaque() >= 9 && orc.calcularFatorAtaque() <= 18);
        assertTrue(orc.calcularFatorDefesa() >= 5 && orc.calcularFatorDefesa() <= 14);
        assertTrue(orc.calcularDano() >= 7 && orc.calcularDano() <= 14);
        orc.receberDano(7);
        assertEquals(13, orc.getPontosDeVida());
    }
}
