package org.example;

import org.example.core.Relatorio;
import org.example.exceptions.PlayerNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class RelatorioTest {
    @Test
    @DisplayName("Deve emitir o relatorio com sucesso")
    public void testaRelatorio() throws PlayerNotFoundException, IOException {
        assertDoesNotThrow(() -> {
            new Relatorio("rodr1go").gerarRelatorio();
        });
    }

    @Test
    @DisplayName("Deve retornar uma exceção ao tentar eminir o relatorio com um player nao existente")
    public void testaRelatorioErro() throws PlayerNotFoundException, IOException {
        assertThrows(PlayerNotFoundException.class, () -> {
            new Relatorio("rodrigo").gerarRelatorio();
        });
    }
}
