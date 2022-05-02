package io.github.thepoultryman.charmsnhexes.hex;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class Hex extends Enchantment {
    public Hex(EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots) {
        super(Rarity.RARE, enchantmentTarget, equipmentSlots);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}
