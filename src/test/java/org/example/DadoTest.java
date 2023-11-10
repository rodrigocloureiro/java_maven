package org.example;


import org.example.util.Dado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DadoTest {
    @Test
    @DisplayName("Deve testar os dados")
    public void testaDado() {
        assertTrue(Dado.rolarD2() > 0 && Dado.rolarD2() < 3);
        assertTrue(Dado.rolarD4() > 0 && Dado.rolarD4() < 5);
        assertTrue(Dado.rolarD6() > 0 && Dado.rolarD6() < 7);
        assertTrue(Dado.rolarD8() > 0 && Dado.rolarD8() < 9);
        assertTrue(Dado.rolarD10() > 0 && Dado.rolarD10() < 11);
    }
}
