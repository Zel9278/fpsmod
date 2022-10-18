package com.csys64.config;

import com.csys64.FPSRenderHandler;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.client.gui.widget.ForgeSlider;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public final class ConfigScreen extends Screen {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int TITLE_HEIGHT = 18;
    private static int fpsStringX, fpsStringY;
    private static boolean isCursed;

    public ConfigScreen() {
        super(new TranslatableComponent("fpsmod.configGui.title"));
    }

    protected void init() {
        fpsStringX = ModConfig.x.get();
        fpsStringY = ModConfig.y.get();
        isCursed = ModConfig.cursedText.get();

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
        Checkbox cursed = new Checkbox(
                (this.width / 2) - 100,
                90,
                200,
                20,
                new TextComponent("縺薙?繝?く繧ｹ繝医?蜑企勁縺輔ｌ縺ｾ縺励◆縲"),
                ModConfig.cursedText.get()
        ) {
            @Override
            public void onPress() {
                isCursed = !isCursed;
            }

            @Override
            public void renderButton(@NotNull PoseStack p_93843_, int p_93844_, int p_93845_, float p_93846_) {
                Minecraft minecraft = Minecraft.getInstance();
                RenderSystem.setShaderTexture(0, new ResourceLocation("textures/gui/checkbox.png"));
                RenderSystem.enableDepthTest();
                Font font = minecraft.font;
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                blit(p_93843_, this.x, this.y, this.isFocused() ? 20.0F : 0.0F, isCursed ? 20.0F : 0.0F, 20, this.height, 64, 64);
                this.renderBg(p_93843_, minecraft, p_93844_, p_93845_);
                drawString(p_93843_, font, this.getMessage(), this.x + 24, this.y + (this.height - 8) / 2, 14737632 | Mth.ceil(this.alpha * 255.0F) << 24);
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
                    ModConfig.cursedText.set(isCursed);
                    this.onClose();
                }
        );

        addRenderableWidget(xSlider);
        addRenderableWidget(ySlider);
        addRenderableWidget(cursed);
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
        Minecraft.getInstance().font.draw(matrixStack, text, posTextX - fontWidth, 5, 0xAAAAAA);
    }
}
