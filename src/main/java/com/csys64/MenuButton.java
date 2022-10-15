package com.csys64;

import com.csys64.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class MenuButton {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void eventHandler(ScreenEvent.DrawScreenEvent event) {
        if (event.getScreen() instanceof PauseScreen) {
            int w = event.getScreen().width;
            int h = event.getScreen().height;
            int posX = 80 * w / 100;
            int posY = 5 * h / 100;
            String str = "Hello From Pause Menu:)";
            int fontWidth = Minecraft.getInstance().font.width(str) / 2;
            Minecraft.getInstance().font.draw(event.getPoseStack(), str, posX - fontWidth, posY, 0xFFFFFF);
        }
    }
}
