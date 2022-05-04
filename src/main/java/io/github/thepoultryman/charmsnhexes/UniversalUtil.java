package io.github.thepoultryman.charmsnhexes;

import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;

import java.util.Random;

public class UniversalUtil {
    private static final Random random = new Random();

    public static String getCharmTranslationKey(String name) {
        return "charm.name." + name;
    }

    public static String getCharmDescription(String name) {
        return new TranslatableText("charm.description." + name).getString();
    }

    public static String getHexDescription(String name) {
        return new TranslatableText("hex.description." + name).getString();
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

    public static Random getRandom() {
        return random;
    }
}
