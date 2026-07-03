package com.example.elytraswap.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.slot.SlotActionType;

public class SwapUtil {

    private static final int CHESTPLATE_SLOT = 38; // Armor slot for chestplate
    private static final int OFFHAND_SLOT = 45;     // Offhand slot

    /**
     * Swap elytra from chestplate slot with item in offhand
     */
    public static void swapElytra(PlayerEntity player) {
        if (player == null) return;

        ItemStack chestplate = player.inventory.getArmorStack(1); // Chestplate slot
        ItemStack offhand = player.getOffHandStack();

        // Check if chestplate is elytra
        if (chestplate.getItem() == Items.ELYTRA && !offhand.isEmpty()) {
            performSwap(player, CHESTPLATE_SLOT, OFFHAND_SLOT);
        } else if (offhand.getItem() == Items.ELYTRA && !chestplate.isEmpty()) {
            performSwap(player, OFFHAND_SLOT, CHESTPLATE_SLOT);
        }
    }

    /**
     * Swap shield from offhand with item in chestplate slot
     */
    public static void swapShield(PlayerEntity player) {
        if (player == null) return;

        ItemStack offhand = player.getOffHandStack();
        ItemStack chestplate = player.inventory.getArmorStack(1); // Chestplate slot

        // Check if offhand is shield
        if (offhand.getItem() == Items.SHIELD && !chestplate.isEmpty()) {
            performSwap(player, OFFHAND_SLOT, CHESTPLATE_SLOT);
        } else if (chestplate.getItem() == Items.SHIELD && !offhand.isEmpty()) {
            performSwap(player, CHESTPLATE_SLOT, OFFHAND_SLOT);
        }
    }

    /**
     * Perform the actual swap between two inventory slots
     */
    private static void performSwap(PlayerEntity player, int slot1, int slot2) {
        if (player == null || player.currentScreenHandler == null) return;

        // Click on first slot
        player.currentScreenHandler.onSlotClick(
                slot1,
                0,
                SlotActionType.PICKUP,
                player
        );

        // Click on second slot
        player.currentScreenHandler.onSlotClick(
                slot2,
                0,
                SlotActionType.PICKUP,
                player
        );

        // Click on first slot again to place the item
        player.currentScreenHandler.onSlotClick(
                slot1,
                0,
                SlotActionType.PICKUP,
                player
        );
    }
}
