package com.csys64;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;

import com.csys64.config.ModConfig;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class ShowFPS {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void eventHandler(ScreenEvent.DrawScreenEvent event) {
        int w = event.getScreen().width;
        int h = event.getScreen().height;
        renderHandler(event.getPoseStack(), w, h);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void eventHandler(RenderGameOverlayEvent event) {
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        renderHandler(event.getMatrixStack(), w, h);
    }

    private static void renderHandler(PoseStack poseStack, int w, int h) {
        int posX = ModConfig.x.get() * w / 100;
        int posY = ModConfig.y.get() * h / 100;
        String fps = Minecraft.getInstance().fpsString.split(" ")[0];
        String fpsString = fps + " fps";
        int fpsInt = Integer.parseInt(fps);
        int fontWidth = Minecraft.getInstance().font.width(fpsString) / 2;
        int color = 0xFFFFFF;

        if (fpsInt < 32) {
            color = 0xFFFF00;
        }

        if (fpsInt < 16) {
            color = 0xFF0000;
        }

        Minecraft.getInstance().font.draw(poseStack, fpsString, posX - fontWidth, posY, color);
    }
}
