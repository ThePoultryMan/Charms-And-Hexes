package io.github.thepoultryman.charmsnhexes.mixin;

import io.github.thepoultryman.charmsnhexes.CharmsAndHexes;
import io.github.thepoultryman.charmsnhexes.registry.HexRegistry;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(InGameHud.class)
public class EnchantmentBookColorMixin {
    @Shadow
    private ItemStack currentStack;

    @ModifyVariable(at = @At("STORE"), method = "renderHeldItemTooltip", ordinal = 0)
    public MutableText changeEnchantmentBookColor(MutableText mutableText) {
        if (!EnchantedBookItem.getEnchantmentNbt(currentStack).isEmpty()) {
            for (String hexName : HexRegistry.HEXES) {
                if (EnchantedBookItem.getEnchantmentNbt(currentStack).get(0).asString().contains(hexName)) {
                    mutableText = new LiteralText("").append("Hex");
                    return mutableText.formatted(Formatting.RED);
                }
            }
        }

        return mutableText;
    }
}
