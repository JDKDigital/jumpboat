package cy.jdkdigital.jumpboat;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;
    public static final General GENERAL = new General(BUILDER);

    static {
        CONFIG = BUILDER.build();
    }

    public static class General
    {
        public final ForgeConfigSpec.DoubleValue jumpHeightInWater;
        public final ForgeConfigSpec.DoubleValue jumpHeightOnLand;

        public General(ForgeConfigSpec.Builder builder) {
            builder.push("Common");

            jumpHeightInWater = builder
                    .comment("Jump height modifier when in water.")
                    .defineInRange("jumpHeightInWater", .4, 0, Integer.MAX_VALUE);

            jumpHeightOnLand = builder
                    .comment("Jump height modifier when on land.")
                    .defineInRange("jumpHeightOnLand", .3, 0, Integer.MAX_VALUE);

            builder.pop();
        }
    }
}
