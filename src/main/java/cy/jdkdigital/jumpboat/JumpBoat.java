package cy.jdkdigital.jumpboat;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod("jumpboat")
public class JumpBoat
{
    public JumpBoat() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.CONFIG);
    }
}
