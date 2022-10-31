package ru.yurannnzzz.ic2cezmode.mixin;

import ic2.api.items.readers.IWrenchTool;
import ic2.probeplugin.info.WrenchComponent;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = WrenchComponent.class, remap = false)
public abstract class WrenchComponentMixin {
    @Redirect(
            method = "addInfo(Lmcjty/theoneprobe/api/IProbeInfo;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/Direction;Lic2/core/block/base/features/IWrenchableTile;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lic2/api/items/readers/IWrenchTool;getActualLoss(Lnet/minecraft/world/item/ItemStack;D)D"
            )
    )
    public double getFixedLoss(IWrenchTool instance, ItemStack itemStack, double v) {
        return 1.0;
    }
}
