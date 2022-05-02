package io.github.thepoultryman.charmsnhexes.hex.hexes;

import io.github.thepoultryman.charmsnhexes.hex.Hex;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class BloodyDamage extends Hex {
    public BloodyDamage() {
        super(EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }
}
