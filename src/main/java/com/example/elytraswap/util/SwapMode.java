package com.example.elytraswap.util;

public enum SwapMode {
    ELYTRA("Elytra"),
    SHIELD("Shield");

    private final String name;

    SwapMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
