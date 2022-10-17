package com.csys64.config;

import com.csys64.FPSRenderHandler;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.client.gui.widget.ForgeSlider;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;

import org.jetbrains.annotations.NotNull;

public final class ConfigScreen extends Screen {
    private static final int TITLE_HEIGHT = 18;
    private static int fpsStringX, fpsStringY;

    public ConfigScreen() {
        super(new TranslatableComponent("fpsmod.configGui.title"));
    }

    protected void init() {
        fpsStringX = ModConfig.x.get();
        fpsStringY = ModConfig.y.get();

        ForgeSlider xSlider = new ForgeSlider(
                (this.width / 2) - 100,
                40,
                200,
                20,
                new TextComponent("X:"),
                new TextComponent(""),
                0,
                100,
                ModConfig.x.get(),
                true
        ) {
            @Override
            protected void applyValue() {
                fpsStringX = this.getValueInt();
            }
        };
        ForgeSlider ySlider = new ForgeSlider(
                (this.width / 2) - 100,
                65,
                200,
                20,
                new TextComponent("Y:"),
                new TextComponent(""),
                0,
                100,
                ModConfig.y.get(),
                true
        ) {
            @Override
            protected void applyValue() {
                fpsStringY = this.getValueInt();
            }
        };

        Button done = new Button(
                (this.width / 2) - 50,
                this.height - 40,
                100,
                20,
                new TranslatableComponent("gui.done"),
                button -> {
                    ModConfig.x.set(xSlider.getValueInt());
                    ModConfig.y.set(ySlider.getValueInt());
                    this.onClose();
                }
        );

        addRenderableWidget(xSlider);
        addRenderableWidget(ySlider);
        addRenderableWidget(done);
    }

    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        drawCenteredString(matrixStack, this.font, this.title.getString(), this.width / 2, TITLE_HEIGHT, 0xFFFFFF);

        super.render(matrixStack, mouseX, mouseY, partialTicks);

        int posX = fpsStringX * width / 100;
        int posY = fpsStringY * height / 100;
        FPSRenderHandler.render(matrixStack, posX, posY);

        TranslatableComponent text = new TranslatableComponent("fpsmod.configGui.text");
        int posTextX = 50 * width / 100;
        int fontWidth = Minecraft.getInstance().font.width(text) / 2;
        Minecraft.getInstance().font.draw(matrixStack, text, posTextX - fontWidth, 0, 0xAAAAAA);
    }
}
