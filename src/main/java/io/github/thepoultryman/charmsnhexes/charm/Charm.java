package io.github.thepoultryman.charmsnhexes.charm;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class Charm extends Enchantment {
    public Charm(EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
        super(Rarity.RARE, enchantmentTarget, equipmentSlots);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}
