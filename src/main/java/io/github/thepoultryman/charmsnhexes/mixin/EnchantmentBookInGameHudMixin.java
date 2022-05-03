package io.github.thepoultryman.charmsnhexes.mixin;

import io.github.thepoultryman.charmsnhexes.UniversalUtil;
import io.github.thepoultryman.charmsnhexes.registry.CharmRegistry;
import io.github.thepoultryman.charmsnhexes.registry.HexRegistry;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(InGameHud.class)
public class EnchantmentBookInGameHudMixin {
    @Shadow
    private ItemStack currentStack;

    @ModifyVariable(at = @At("STORE"), method = "renderHeldItemTooltip", ordinal = 0)
    public MutableText charmsnhexes$changeEnchantmentBookColor(MutableText mutableText) {
        if (!EnchantedBookItem.getEnchantmentNbt(currentStack).isEmpty()) {
            for (String hexName : HexRegistry.HEXES) {
                if (EnchantedBookItem.getEnchantmentNbt(currentStack).get(0).asString().contains(hexName)) {
                    return new TranslatableText(UniversalUtil.getHexTranslationKey(hexName)).append(" ")
                        .append(new TranslatableText("charm.name")).formatted(Formatting.DARK_PURPLE);
                }
            }

            for (String charmName : CharmRegistry.CHARMS) {
                if (EnchantedBookItem.getEnchantmentNbt(currentStack).get(0).asString().contains(charmName)) {
                    return new TranslatableText(UniversalUtil.getCharmTranslationKey(charmName)).append(" ")
                        .append(new TranslatableText("charm.name")).formatted(Formatting.GREEN);
                }
            }
        }

        return mutableText;
    }
}
