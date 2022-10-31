package ru.yurannnzzz.ic2cezmode.mixin;

import ic2.api.energy.tile.IEnergyTile;
import ic2.core.IC2;
import ic2.core.audio.AudioManager;
import ic2.core.audio.providers.SimplePosition;
import ic2.core.energy.EnergyNetLocal;
import ic2.core.entity.explosion.IC2Explosion;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EnergyNetLocal.class, remap = false)
public abstract class EnergyNetLocalMixin {
    @Shadow public abstract void removeTile(IEnergyTile tile);

    @Shadow Level world;

    @Inject(
            method = "explodeTiles(Lic2/api/energy/tile/IEnergyTile;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void doNotExplodeTiles(IEnergyTile tile, CallbackInfo ci) {
        IC2.AUDIO.playSound(new SimplePosition(this.world, tile.getPosition()), IC2Explosion.ELECTRIC_EXPLOSION, AudioManager.SoundType.STATIC, 1.0f, 1.0f);

        this.removeTile(tile);
        this.world.destroyBlock(tile.getPosition(), true);

        ci.cancel();
    }
}
