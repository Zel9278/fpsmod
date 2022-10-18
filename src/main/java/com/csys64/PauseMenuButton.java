package com.csys64;

import com.csys64.config.ConfigScreen;
import com.csys64.config.ModConfig;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class PauseMenuButton {
    private static final Random random = new Random();
    private static int num = 0;
    private static final TranslatableComponent[] strings = {
            new TranslatableComponent("fpsmod.pausescreen.cursed.text1"),
            new TranslatableComponent("fpsmod.pausescreen.cursed.text2"),
            new TranslatableComponent("fpsmod.pausescreen.cursed.text3"),
            new TranslatableComponent("fpsmod.pausescreen.cursed.text4"),
            new TranslatableComponent("fpsmod.pausescreen.cursed.text5"),
            new TranslatableComponent("fpsmod.pausescreen.cursed.text6"),
            new TranslatableComponent("fpsmod.pausescreen.cursed.text7"),
            new TranslatableComponent("fpsmod.pausescreen.cursed.text8")
    };

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
            num = random.nextInt(1000);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void eventHandler(ScreenEvent.DrawScreenEvent event) {
        if (event.getScreen() instanceof PauseScreen) {
            if (!ModConfig.cursedText.get()) return;
            if (num >= 900) {
                Button button = new Button(
                        random.nextInt(event.getScreen().width),
                        random.nextInt(event.getScreen().height),
                        0,
                        0,
                        strings[random.nextInt(strings.length-1)],
                        b -> {}
                );
                button.setFGColor(0xFF0000);
                event.getScreen().renderables.add(0, button);
            }
        }
    }
}
