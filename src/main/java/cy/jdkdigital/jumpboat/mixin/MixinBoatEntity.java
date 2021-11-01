package cy.jdkdigital.jumpboat.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BoatEntity.class)
public abstract class MixinBoatEntity extends Entity implements IJumpingMount
{
    @Shadow
    private BoatEntity.Status status;

    private boolean hasJumpedFromWater;

    protected MixinBoatEntity(EntityType<?> type, World level) {
        super(type, level);
    }

    @Override
    public void onPlayerJump(int p_110206_1_) {
        if (this.status == BoatEntity.Status.IN_WATER) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, .3, 0));
            this.hasJumpedFromWater = true;
        } else {
            this.setDeltaMovement(this.getDeltaMovement().add(0, .25, 0));
        }
    }

    @Override
    public boolean canJump() {
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
