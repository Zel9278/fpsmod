package com.csys64.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class ModConfig {
    public static final ForgeConfigSpec GENERAL_SPEC;
    public static ForgeConfigSpec.IntValue x;
    public static ForgeConfigSpec.IntValue y;
    public static ForgeConfigSpec.BooleanValue cursedText;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        GENERAL_SPEC = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("This is FPSMod Config");
        builder.push("FPS Config Options");

        x = builder.comment("This is x value, default is 50").defineInRange("x", 50, 0, 100);
        y = builder.comment("This is y value, default is 10").defineInRange("y", 12, 0, 100);
        cursedText = builder.comment("This is [] value, default is true").define("[]", true);

        builder.pop();
    }
}
