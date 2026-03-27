package cy.jdkdigital.jumpboat.mixin;

import cy.jdkdigital.jumpboat.Config;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = AbstractBoat.class)
public abstract class MixinAbstractBoat extends Entity implements PlayerRideableJumping
{
    @Shadow(remap = false)
    private AbstractBoat.Status status;
    @Unique
    private boolean jumpboat$hasJumpedFromWater;

    public MixinAbstractBoat(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    public void onPlayerJump(int boostLevel) {
        if (this.status == AbstractBoat.Status.IN_WATER) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, Config.GENERAL.jumpHeightInWater.get() * boostLevel / 100, 0));
            this.jumpboat$hasJumpedFromWater = true;
        } else if (this.status == AbstractBoat.Status.ON_LAND) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, Config.GENERAL.jumpHeightOnLand.get() * boostLevel / 100, 0));
        }
    }

    @Override
    public boolean canJump() {
        return true;
    }

    @Override
    public void handleStartJump(int p_184775_1_) {
        if (this.jumpboat$hasJumpedFromWater) {
            this.playSound(SoundEvents.PLAYER_SPLASH, 0.4F, 1.0F);
            this.jumpboat$hasJumpedFromWater = false;
        }
    }

    @Override
    public void handleStopJump() {}
}
