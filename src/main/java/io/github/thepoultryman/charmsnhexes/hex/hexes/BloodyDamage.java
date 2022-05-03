package io.github.thepoultryman.charmsnhexes.hex.hexes;

import io.github.thepoultryman.charmsnhexes.hex.Hex;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class BloodyDamage extends Hex {
    public BloodyDamage() {
        super(EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND}, "bloody_damage");
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return switch (level) {
            case 1 -> 1f;
            case 2 -> 1.5f;
            case 3 -> 2.5f;
            default -> (2.5f + (level * 0.1f));
        };
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        switch (level) {
            case 1 -> user.damage(DamageSource.MAGIC, 2f);
            case 2 -> user.damage(DamageSource.MAGIC, 1.5f);
            case 3 -> user.damage(DamageSource.MAGIC, 1f);
            default -> user.damage(DamageSource.MAGIC, (1f - (level * 0.1f)));
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
