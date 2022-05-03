package io.github.thepoultryman.charmsnhexes.charm.charms;

import io.github.thepoultryman.charmsnhexes.charm.Charm;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class HealingGrasp extends Charm {
    public HealingGrasp() {
        super(EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}, "healing_grasp");
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return switch (level) {
            case 1 -> -3f;
            case 2 -> -2f;
            case 3 -> -1.5f;
            default -> (-1.5f + (level * 0.07f));
        };
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        switch (level)  {
            case 1 -> user.heal(0.5f);
            case 2 -> user.heal(1f);
            case 3 -> user.heal(2f);
            default -> user.heal((level / 2f) + (level * 0.25f));
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
