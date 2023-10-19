package org.example.util;

import lombok.Getter;

import java.time.LocalDateTime;

public class Linha {
    @Getter
    private int rodada;
    @Getter
    private boolean ganhou;
    @Getter
    private LocalDateTime dataHoraPartida;
    @Getter
    private String heroi;
    @Getter
    private String monstro;

    public Linha(LocalDateTime dataHoraPartida, String heroi, boolean ganhou, String monstro, int rodada) {
        this.dataHoraPartida = dataHoraPartida;
        this.heroi = heroi;
        this.ganhou = ganhou;
        this.monstro = monstro;
        this.rodada = rodada;
    }
}
