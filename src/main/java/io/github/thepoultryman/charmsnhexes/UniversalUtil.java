package io.github.thepoultryman.charmsnhexes;

import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;

public class UniversalUtil {
    public static String getCharmTranslationKey(String name) {
        return "charm.name." + name;
    }

    public static String getHexTranslationKey(String name) {
        return "hex.name." + name;
    }

    public static boolean isCharm(ItemStack itemStack, String charmName) {
        return itemStack.getItem() instanceof EnchantedBookItem
            && EnchantedBookItem.getEnchantmentNbt(itemStack).get(0).asString().contains(charmName);
    }

    public static boolean isHex(ItemStack itemStack, String hexName) {
        return itemStack.getItem() instanceof EnchantedBookItem
            && EnchantedBookItem.getEnchantmentNbt(itemStack).get(0).asString().contains(hexName);
    }
}
