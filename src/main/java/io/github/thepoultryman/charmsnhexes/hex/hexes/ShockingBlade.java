package io.github.thepoultryman.charmsnhexes.hex.hexes;

import io.github.thepoultryman.charmsnhexes.hex.Hex;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ShockingBlade extends Hex {
    private boolean hackyCodeStopper = false;

    public ShockingBlade() {
        super(EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}, "shocking_blade");
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        World world = target.getWorld();
        hackyCodeStopper = !hackyCodeStopper;
        if (!world.isClient() && !hackyCodeStopper) {
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(world);
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(target.getBlockPos()));
            world.spawnEntity(lightningEntity);
        }
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
