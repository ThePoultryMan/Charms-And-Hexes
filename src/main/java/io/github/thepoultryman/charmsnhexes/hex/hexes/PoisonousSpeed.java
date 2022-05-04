package io.github.thepoultryman.charmsnhexes.hex.hexes;

import io.github.thepoultryman.charmsnhexes.hex.Hex;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class PoisonousSpeed extends Hex {
    public PoisonousSpeed() {
        super(EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND}, "poisonous_speed");
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        int time = switch (level) {
            case 1 -> 160;
            case 2 -> 140;
            case 3 -> 100;
            default -> Math.max(20, 100 - (level * 20));
        };
        user.setStatusEffect(new StatusEffectInstance(StatusEffects.POISON, time), user);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
