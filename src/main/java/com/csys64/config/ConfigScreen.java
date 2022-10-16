package com.csys64.config;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;

import org.jetbrains.annotations.NotNull;

public final class ConfigScreen extends Screen {
    private static final int TITLE_HEIGHT = 8;

    public ConfigScreen() {
        super(new TranslatableComponent("fpsmod.configGui.title"));
    }

    protected void init() {
        Button done = new Button(
                (this.width / 2) - 50,
                this.height - 40,
                100,
                20,
                new TranslatableComponent("gui.done"),
                button -> this.onClose()
        );

        addRenderableWidget(done);
    }

    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        drawCenteredString(matrixStack, this.font, this.title.getString(), this.width / 2, TITLE_HEIGHT, 0xFFFFFF);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
