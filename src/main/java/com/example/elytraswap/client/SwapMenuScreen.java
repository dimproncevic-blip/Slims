package com.example.elytraswap.client;

import com.example.elytraswap.util.SwapMode;
import com.example.elytraswap.util.SwapUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class SwapMenuScreen extends Screen {

    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 20;
    private static final int SPACING = 10;

    public SwapMenuScreen() {
        super(new LiteralText("Elytra Swap Menu"));
    }

    @Override
    protected void init() {
        super.init();

        // Title
        int centerX = this.width / 2;
        int startY = this.height / 2 - 50;

        // Elytra Swap Button
        this.addButton(new ButtonWidget(
                centerX - BUTTON_WIDTH / 2,
                startY,
                BUTTON_WIDTH,
                BUTTON_HEIGHT,
                new LiteralText("Swap Elytra"),
                button -> {
                    SwapUtil.swapElytra(this.client.player);
                    this.client.openScreen(null);
                }
        ));

        // Shield Swap Button
        this.addButton(new ButtonWidget(
                centerX - BUTTON_WIDTH / 2,
                startY + BUTTON_HEIGHT + SPACING,
                BUTTON_WIDTH,
                BUTTON_HEIGHT,
                new LiteralText("Swap Shield"),
                button -> {
                    SwapUtil.swapShield(this.client.player);
                    this.client.openScreen(null);
                }
        ));

        // Cancel Button
        this.addButton(new ButtonWidget(
                centerX - BUTTON_WIDTH / 2,
                startY + (BUTTON_HEIGHT + SPACING) * 2,
                BUTTON_WIDTH,
                BUTTON_HEIGHT,
                new LiteralText("Cancel"),
                button -> this.client.openScreen(null)
        ));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.fillGradient(matrices, 0, 0, this.width, this.height, -1072689136, -804253680);
        super.render(matrices, mouseX, mouseY, delta);

        // Draw title
        this.textRenderer.draw(
                matrices,
                "Select Swap Option",
                this.width / 2 - this.textRenderer.getWidth("Select Swap Option") / 2,
                this.height / 2 - 70,
                16777215
        );
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
