package com.csys64;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;

import com.csys64.config.ModConfig;
import com.csys64.config.ConfigScreen;

@Mod("fpsmod")
public class FPSMod {
    private static final Logger LOGGER = LogUtils.getLogger();

    public FPSMod() {
        ModLoadingContext.get().registerConfig(Type.COMMON, ModConfig.GENERAL_SPEC, "FPSMod.toml");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(FPSMod::setup);
    }

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        LOGGER.info("[INFO] Setup FPSMod");
        MinecraftForge.EVENT_BUS.register(ShowFPS.class);
        MinecraftForge.EVENT_BUS.register(MenuButton.class);
        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class,
            () -> new ConfigGuiHandler.ConfigGuiFactory((mc, screen) -> new ConfigScreen()));
    }
}
