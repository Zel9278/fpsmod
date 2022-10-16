package com.csys64;

import com.csys64.config.ModConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;

public class FPSRenderHandler {
    public static void render(PoseStack poseStack, int w, int h) {
        int posX = ModConfig.x.get() * w / 100;
        int posY = ModConfig.y.get() * h / 100;
        String fps = Minecraft.getInstance().fpsString.split(" ")[0];
        String fpsString = fps + " fps";
        int fpsInt = Integer.parseInt(fps);
        int fontWidth = Minecraft.getInstance().font.width(fpsString) / 2;
        int fontColor = 0xFFFFFF;

        if (fpsInt < 32) {
            fontColor = 0xFFFF00;
        }

        if (fpsInt < 16) {
            fontColor = 0xFF0000;
        }

        Minecraft.getInstance().font.draw(poseStack, fpsString, posX - fontWidth, posY, fontColor);
    }
}
