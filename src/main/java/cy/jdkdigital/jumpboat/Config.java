package cy.jdkdigital.jumpboat;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec CONFIG;
    public static final General GENERAL = new General(BUILDER);

    static {
        CONFIG = BUILDER.build();
    }

    public static class General
    {
        public final ModConfigSpec.DoubleValue jumpHeightInWater;
        public final ModConfigSpec.DoubleValue jumpHeightOnLand;

        public General(ModConfigSpec.Builder builder) {
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
