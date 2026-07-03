package com.example.elytraswap.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ElytraSwapClient implements ClientModInitializer {

    private static KeyBinding swapKeybind;

    @Override
    public void onInitializeClient() {
        swapKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.elytra_swap.swap",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.elytra_swap"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (swapKeybind.wasPressed()) {
                onSwapKeyPressed(client);
            }
        });
    }

    private static void onSwapKeyPressed(net.minecraft.client.MinecraftClient client) {
        if (client.player == null) return;

        client.openScreen(new SwapMenuScreen());
    }
}
