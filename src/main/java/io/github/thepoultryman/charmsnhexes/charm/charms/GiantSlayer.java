package io.github.thepoultryman.charmsnhexes.charm.charms;

import io.github.thepoultryman.charmsnhexes.UniversalUtil;
import io.github.thepoultryman.charmsnhexes.charm.Charm;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;

public class GiantSlayer extends Charm {
    public GiantSlayer() {
        super(EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}, "giant_slayer");
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof WitherEntity) {
            target.damage(DamageSource.MAGIC, UniversalUtil.getRandom().nextFloat(level * 2.5f));
        } else if (target instanceof LivingEntity livingEntity) {
            switch (level) {
                case 1, 2 -> livingEntity.heal(level);
                case 3, 4 -> livingEntity.heal(2.5f);
                case 5 -> livingEntity.heal(1.5f);
                default -> livingEntity.heal(0.5f);
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
