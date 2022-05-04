package io.github.thepoultryman.charmsnhexes.mixin;

import io.github.thepoultryman.charmsnhexes.UniversalUtil;
import io.github.thepoultryman.charmsnhexes.registry.CharmRegistry;
import io.github.thepoultryman.charmsnhexes.registry.HexRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EnchantedBookItem.class)
public abstract class EnchantedBookToolTipMixin {
    @Inject(at = @At("TAIL"), method = "appendTooltip")
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        NbtList enchantments = EnchantedBookItem.getEnchantmentNbt(stack);
        for (int i = 0; i < enchantments.size(); ++i) {
            NbtCompound nbtCompound = enchantments.getCompound(i);
            Registry.ENCHANTMENT.getOrEmpty(EnchantmentHelper.getIdFromNbt(nbtCompound)).ifPresent((e) -> {
                String name = e.getTranslationKey();
                name = name.substring(name.indexOf(".", name.indexOf(".") + 1) + 1);
                if (CharmRegistry.CHARMS.contains(name)) {
                    tooltip.add(new LiteralText("---").formatted(Formatting.DARK_GRAY));
                    for (String descriptionLine : UniversalUtil.getCharmDescription(name).split("\\n")) {
                        tooltip.add(new LiteralText(descriptionLine).formatted(Formatting.ITALIC, Formatting.GRAY));
                    }
                }
                else if (HexRegistry.HEXES.contains(name)) {
                    tooltip.add(new LiteralText("---").formatted(Formatting.DARK_GRAY));
                    for (String descriptionLine : UniversalUtil.getHexDescription(name).split("\\n")) {
                        tooltip.add(new LiteralText(descriptionLine).formatted(Formatting.ITALIC, Formatting.GRAY));
                    }
                }
            });
        }
    }
}
