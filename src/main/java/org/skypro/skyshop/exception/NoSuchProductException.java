package org.skypro.skyshop.exception;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(UUID id) {
        super("Несуществующий товар с id: " + id);
    }
}
