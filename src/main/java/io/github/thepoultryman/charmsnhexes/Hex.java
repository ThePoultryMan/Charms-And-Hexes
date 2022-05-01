package io.github.thepoultryman.charmsnhexes;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.text.Text;

public class Hex extends Enchantment {
    public Hex() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public Text getName(int level) {
        return super.getName(level);
    }
}
