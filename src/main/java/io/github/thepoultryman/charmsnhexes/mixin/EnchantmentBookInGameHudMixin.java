package io.github.thepoultryman.charmsnhexes.mixin;

import io.github.thepoultryman.charmsnhexes.UniversalUtil;
import io.github.thepoultryman.charmsnhexes.registry.CharmRegistry;
import io.github.thepoultryman.charmsnhexes.registry.HexRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class EnchantmentBookInGameHudMixin {
    @Shadow
    private ItemStack currentStack;

    @Shadow
    private int heldItemTooltipFade;

    @Shadow
    @Final
    private MinecraftClient client;

    private String oldName;

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

    @Inject(at = @At("HEAD"), method = "tickPausable")
    private void charmsnhexes$setOldName(CallbackInfo ci) {
        boolean breakLoop = false;
        if (this.client.player != null) {
            for (String charmName : CharmRegistry.CHARMS) {
                if (UniversalUtil.isCharm(currentStack, charmName)) {
                    this.oldName = UniversalUtil.getCharmTranslationKey(charmName);
                    breakLoop = true;
                    break;
                }
            }

            if (!breakLoop)
                for (String hexName : HexRegistry.HEXES) {
                    if (UniversalUtil.isHex(currentStack, hexName)) {
                        this.oldName = UniversalUtil.getHexTranslationKey(hexName);
                        break;
                    }
                }
        }
    }

    @Inject(at = @At("TAIL"), method = "tickPausable")
    private void charmsnhexes$itemNameFadeFix(CallbackInfo ci) {
        if (this.client.player != null) {
            ItemStack itemStack = this.client.player.getInventory().getMainHandStack();
            boolean breakLoop = false;
            if (oldName != null) {
                for (String charmName : CharmRegistry.CHARMS) {
                    if (UniversalUtil.isCharm(currentStack, charmName) && !oldName.equals(UniversalUtil.getCharmTranslationKey(charmName))) {
                        this.heldItemTooltipFade = 40;
                        breakLoop = true;
                        break;
                    }
                }

                if (!breakLoop)
                    for (String hexName : HexRegistry.HEXES) {
                        if (UniversalUtil.isHex(currentStack, hexName) && !oldName.equals(UniversalUtil.getHexTranslationKey(hexName))) {
                            this.heldItemTooltipFade = 40;
                            break;
                        }
                    }
            }
        }
    }
}
