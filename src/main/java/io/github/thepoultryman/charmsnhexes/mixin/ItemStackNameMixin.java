package io.github.thepoultryman.charmsnhexes.mixin;

import io.github.thepoultryman.charmsnhexes.UniversalUtil;
import io.github.thepoultryman.charmsnhexes.registry.CharmRegistry;
import io.github.thepoultryman.charmsnhexes.registry.HexRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
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
        if (currentStack.getItem() instanceof EnchantedBookItem) {
            NbtList enchantments = EnchantedBookItem.getEnchantmentNbt(currentStack);
            for (int i = 0; i < enchantments.size(); ++i) {
                NbtCompound nbtCompound = enchantments.getCompound(i);
                Registry.ENCHANTMENT.getOrEmpty(EnchantmentHelper.getIdFromNbt(nbtCompound)).ifPresent((e) -> {
                    String name = e.getTranslationKey();
                    name = name.substring(name.indexOf(".", name.indexOf(".") + 1) + 1);
                    if (CharmRegistry.CHARMS.contains(name)) {
                        cir.setReturnValue(new TranslatableText(UniversalUtil.getCharmTranslationKey(name)).append(" ")
                            .append(new TranslatableText("charm.name")).formatted(Formatting.GREEN));
                    } else if (HexRegistry.HEXES.contains(name)) {
                        cir.setReturnValue(new TranslatableText(UniversalUtil.getHexTranslationKey(name)).append(" ")
                            .append(new TranslatableText("hex.name")).formatted(Formatting.DARK_PURPLE));
                    }
                });
            }
        }
    }
}
