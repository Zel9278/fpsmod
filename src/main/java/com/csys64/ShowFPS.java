package com.csys64;

import com.csys64.config.ConfigScreen;
import com.csys64.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class ShowFPS {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void eventHandler(ScreenEvent.DrawScreenEvent event) {
        if (event.getScreen() instanceof ConfigScreen) return;
        int w = event.getScreen().width;
        int h = event.getScreen().height;
        int posX = ModConfig.x.get() * w / 100;
        int posY = ModConfig.y.get() * h / 100;

        FPSRenderHandler.render(event.getPoseStack(), posX, posY);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void eventHandler(RenderGameOverlayEvent event) {
        if (Minecraft.getInstance().isPaused()) return;
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        int posX = ModConfig.x.get() * w / 100;
        int posY = ModConfig.y.get() * h / 100;

        FPSRenderHandler.render(event.getMatrixStack(), posX, posY);
    }
}
