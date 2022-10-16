package com.csys64;

import com.csys64.config.ConfigScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class PauseMenuButton {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void eventHandler(ScreenEvent.InitScreenEvent event) {
        if (event.getScreen() instanceof PauseScreen) {
            Button button = new Button(
                    (event.getScreen().width / 2) - 50,
                    event.getScreen().height - 40,
                    100,
                    20,
                    new TranslatableComponent("fpsmod.pausescreen.button.title"),
                    b -> event.getScreen().getMinecraft().setScreen(new ConfigScreen())
            );

            event.addListener(button);
        }
    }
}
