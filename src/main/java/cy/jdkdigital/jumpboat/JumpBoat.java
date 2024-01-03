package cy.jdkdigital.jumpboat;

import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod("jumpboat")
public class JumpBoat
{
    public JumpBoat() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.CONFIG);
    }
}
