package io.github.thepoultryman.charmsnhexes.mixin;

import io.github.thepoultryman.charmsnhexes.UniversalUtil;
import io.github.thepoultryman.charmsnhexes.registry.CharmRegistry;
import io.github.thepoultryman.charmsnhexes.registry.HexRegistry;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackNameMixin {
    @Shadow
    public abstract Item getItem();

    @Inject(at = @At("RETURN"), method = "getName", cancellable = true)
    private void charmsnhexes$changeEnchantmentName(CallbackInfoReturnable<Text> cir) {
        ItemStack currentStack = ((ItemStack)(Object)this);
        if (currentStack.getItem() instanceof EnchantedBookItem)
            if (!EnchantedBookItem.getEnchantmentNbt(currentStack).isEmpty()) {
                for (String charmName : CharmRegistry.CHARMS) {
                    if (EnchantedBookItem.getEnchantmentNbt(currentStack).get(0).asString().contains(charmName)) {
                        cir.setReturnValue(new TranslatableText(UniversalUtil.getCharmTranslationKey(charmName)).append(" ")
                            .append(new TranslatableText("charm.name")).formatted(Formatting.GREEN));
                    }
                }

                for (String hexName : HexRegistry.HEXES) {
                    if (EnchantedBookItem.getEnchantmentNbt(currentStack).get(0).asString().contains(hexName)) {
                        cir.setReturnValue(new TranslatableText(UniversalUtil.getHexTranslationKey(hexName)).append(" ")
                            .append(new TranslatableText("hex.name")).formatted(Formatting.DARK_PURPLE));
                    }
                }
            }
    }
}
