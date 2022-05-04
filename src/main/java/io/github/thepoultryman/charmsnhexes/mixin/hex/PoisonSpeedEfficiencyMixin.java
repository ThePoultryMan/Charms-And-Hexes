package io.github.thepoultryman.charmsnhexes.mixin.hex;

import io.github.thepoultryman.charmsnhexes.registry.HexRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class PoisonSpeedEfficiencyMixin {

    @Inject(at = @At("RETURN"), method = "getEfficiency", cancellable = true)
    private static void charmsnhexes$getEfficiencyOrPoisonSpeed(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        int equipmentLevel = EnchantmentHelper.getEquipmentLevel(Enchantments.EFFICIENCY, entity);
        if (equipmentLevel <= 0)
            cir.setReturnValue(EnchantmentHelper.getEquipmentLevel(HexRegistry.POISONOUS_SPEED, entity));
    }
}
