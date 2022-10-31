package ru.yurannnzzz.ic2cezmode.mixin;

import ic2.api.crops.ICrop;
import ic2.api.crops.ICropTile;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ICrop.class, remap = false)
public abstract class ICropMixin {
    @Inject(
            method = "onEntityCollision(Lic2/api/crops/ICropTile;Lnet/minecraft/world/entity/Entity;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    public void preventCropTrampling(ICropTile cropTile, Entity entity, CallbackInfo ci) {
        ci.cancel();
    }
}
