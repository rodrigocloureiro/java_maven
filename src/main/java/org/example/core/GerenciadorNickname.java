package org.example.core;

import org.example.exceptions.InvalidNicknameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class GerenciadorNickname {
    private static Logger LOGGER = LoggerFactory.getLogger(GerenciadorNickname.class);

    public static String nickname(Scanner sc) {
        String nickname = null;
        do {
            try {
                LOGGER.info("Informe o seu nickname (entre 3 e 8 caracteres): ");
                nickname = sc.nextLine();
                if (nickname.length() < 3 || nickname.length() > 8) {
                    nickname = null;
                    throw new InvalidNicknameException("Informe um nickname válido.");
                }
            } catch (InvalidNicknameException ex) {
                LOGGER.error(ex.getMessage());
            }
        } while (nickname == null);
        return nickname.toLowerCase();
    }
}
