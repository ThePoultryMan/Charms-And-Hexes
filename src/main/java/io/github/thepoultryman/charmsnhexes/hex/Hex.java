package io.github.thepoultryman.charmsnhexes.hex;

import io.github.thepoultryman.charmsnhexes.UniversalUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class Hex extends Enchantment {
    private final String name;

    public Hex(EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots, String name) {
        super(Rarity.RARE, enchantmentTarget, equipmentSlots);
        this.name = name;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    protected String getOrCreateTranslationKey() {
        if (this.translationKey == null)
            this.translationKey = UniversalUtil.getHexTranslationKey(this.name);

        return this.translationKey;
    }
}
