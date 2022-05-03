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
        return Math.round(Math.pow(0.65f, -level));
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        user.damage(DamageSource.MAGIC, (float) Math.floor((Math.pow(0.55f, level) + 1f)));
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
