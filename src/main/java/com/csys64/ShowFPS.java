package com.csys64;

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
        int w = event.getScreen().width;
        int h = event.getScreen().height;
        FPSRenderHandler.render(event.getPoseStack(), w, h);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void eventHandler(RenderGameOverlayEvent event) {
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        FPSRenderHandler.render(event.getMatrixStack(), w, h);
    }
}
