package ru.yurannnzzz.ic2cezmode.mixin;

import ic2.api.blocks.IWrenchable;
import ic2.core.item.tool.WrenchTool;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WrenchTool.class)
public class WrenchToolMixin {
    @Redirect(
            method = "onItemUseFirst(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/context/UseOnContext;)Lnet/minecraft/world/InteractionResult;",
            at = @At(
                    value = "INVOKE",
                    target = "Lic2/api/blocks/IWrenchable;getDropRate(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;)D"
            ),
            remap = false
    )
    public double getFixedDropRate(IWrenchable instance, BlockState blockState, Level level, BlockPos blockPos, Player player) {
        return 1.0;
    }
}
