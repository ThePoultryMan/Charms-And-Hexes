package io.github.thepoultryman.charmsnhexes.charm;

import io.github.thepoultryman.charmsnhexes.UniversalUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.text.Text;

public class Charm extends Enchantment {
    private final String name;

    public Charm(EnchantmentTarget enchantmentTarget, EquipmentSlot[] equipmentSlots, String name) {
        super(Rarity.RARE, enchantmentTarget, equipmentSlots);
        this.name = name;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public Text getName(int level) {
        return super.getName(level);
    }

    @Override
    protected String getOrCreateTranslationKey() {
        if (this.translationKey == null)
            this.translationKey = UniversalUtil.getCharmTranslationKey(this.name);

        return this.translationKey;
    }
}
