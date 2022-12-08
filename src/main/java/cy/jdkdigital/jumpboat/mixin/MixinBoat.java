package cy.jdkdigital.jumpboat.mixin;

import cy.jdkdigital.jumpboat.Config;
import cy.jdkdigital.jumpboat.JumpBoat;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Boat.class)
public abstract class MixinBoat extends Entity implements PlayerRideableJumping
{
    @Shadow
    private Boat.Status status;
    private boolean hasJumpedFromWater;

    public MixinBoat(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    public void onPlayerJump(int boostLevel) {
        if (this.status == Boat.Status.IN_WATER) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, Config.GENERAL.jumpHeightInWater.get() * boostLevel / 100, 0));
            this.hasJumpedFromWater = true;
        } else if (this.status == Boat.Status.ON_LAND) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, Config.GENERAL.jumpHeightOnLand.get() * boostLevel / 100, 0));
        }
    }

    @Override
    public boolean canJump(Player player) {
        return true;
    }

    @Override
    public void handleStartJump(int p_184775_1_) {
        if (this.hasJumpedFromWater) {
            this.playSound(SoundEvents.PLAYER_SPLASH, 0.4F, 1.0F);
            this.hasJumpedFromWater = false;
        }
    }

    @Override
    public void handleStopJump() {}
}
