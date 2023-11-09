package org.example.core;

import org.example.App;
import org.example.exceptions.PlayerNotFoundException;
import org.example.util.Linha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class Relatorio {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private List<Linha> logs;
    private String nickname;

    public Relatorio(String nickname) throws IOException, PlayerNotFoundException {
        this.logs = logJogador(nickname);
        this.nickname = nickname;
    }

    private HashMap<String, Integer> mapear(List<Linha> logs, Function<Linha, String> keyExtractor) {
        HashMap<String, Integer> map = new HashMap<>();
        for (Linha log : logs) {
            String key = keyExtractor.apply(log);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return map;
    }

    private String formatarAparicoes(String str) {
        int ultimaOcorrencia = str.lastIndexOf(" e ");
        if (ultimaOcorrencia != -1) {
            String prev = str.substring(0, ultimaOcorrencia).replace(" e ", ", ");
            String next = str.substring(ultimaOcorrencia);
            return prev + next;
        } else {
            return str;
        }
    }

    private String maisAparicoes(HashMap<String, Integer> map) {
        String personagem = null;
        int count = 0;
        for (String key : map.keySet()) {
            if (map.get(key) > count) {
                personagem = key;
                count = map.get(key);
            } else if (map.get(key) == count) {
                personagem = String.format("%s e %s", personagem, key);
            }
        }
        return formatarAparicoes(personagem);
    }

    private String heroiMaisJogado() {
        return maisAparicoes(mapear(this.logs, Linha::getHeroi));
    }

    private String monstroMaisEnfrentado() {
        return maisAparicoes(mapear(this.logs, Linha::getMonstro));
    }

    private int calcularPontuacao() {
        int pontuacao = 0;
        for (Linha linha : this.logs) {
            if (linha.isGanhou()) pontuacao += 100 - linha.getRodada();
        }
        return pontuacao;
    }

    private int batalhaMaisLonga() {
        int numRodadas = 0;
        for (Linha linha : this.logs) {
            if (linha.getRodada() > numRodadas) numRodadas = linha.getRodada();
        }
        return numRodadas;
    }

    private int batalhaMaisCurta() {
        int numRodadas = Integer.MAX_VALUE;
        for (Linha linha : this.logs) {
            if (linha.getRodada() < numRodadas) numRodadas = linha.getRodada();
        }
        return numRodadas;
    }

    private HashMap<LocalDateTime, Boolean> mapearVitorias() {
        HashMap<LocalDateTime, Boolean> map = new HashMap<>();
        for (Linha log : logs) {
            map.put(log.getDataHoraPartida(), log.isGanhou());
        }
        return map;
    }

    private int numeroVitorias() {
        int vitorias = 0;
        var map = mapearVitorias();
        for (LocalDateTime key : map.keySet()) {
            if (map.get(key)) vitorias++;
        }
        return vitorias;
    }

    private double calcularAproveitamento() {
        return (double) numeroVitorias() / this.logs.size() * 100;
    }

    private List<Linha> logJogador(String nickname) throws IOException, PlayerNotFoundException {
        Path programDir = Paths.get(System.getProperty("user.dir"), "/temp");
        Path file = programDir.resolve(Paths.get(nickname + ".csv"));

        if (Files.exists(file)) {
            List<String> linhas = Files.readAllLines(file);
            List<Linha> logs = new ArrayList<>();

            for (String linha : linhas) {
                String[] parte = linha.split(";");
                logs.add(new Linha(
                        LocalDateTime.parse(parte[0], DateTimeFormatter.ofPattern("dd/MM/yyyy_HH:mm:ss")),
                        parte[1],
                        parte[2].equals("GANHOU"),
                        parte[3],
                        Integer.parseInt(parte[4])
                ));
            }
            return logs;
        } else throw new PlayerNotFoundException("O jogador não existe.");
    }

    public void gerarRelatorio() {
        LOGGER.info(String.format("<<<<< Relatório: %s >>>>>", this.nickname));
        LOGGER.info(String.format("Herói mais jogado: %s", heroiMaisJogado()));
        LOGGER.info(String.format("Monstro mais enfrentado: %s", monstroMaisEnfrentado()));
        LOGGER.info(String.format("Pontuação total: %d", calcularPontuacao()));
        LOGGER.info(String.format("Batalha mais longa: %d rodadas", batalhaMaisLonga()));
        LOGGER.info(String.format("Batalha mais curta: %d rodadas", batalhaMaisCurta()));
        LOGGER.info(String.format("Número de partidas: %d", this.logs.size()));
        LOGGER.info(String.format("Número de vitórias: %d", numeroVitorias()));
        LOGGER.info(String.format("Aproveitamento: %.1f%%", calcularAproveitamento()));
    }
}
