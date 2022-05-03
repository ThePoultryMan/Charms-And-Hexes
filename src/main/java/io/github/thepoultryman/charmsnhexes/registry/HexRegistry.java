package io.github.thepoultryman.charmsnhexes.registry;

import io.github.thepoultryman.charmsnhexes.CharmsAndHexes;
import io.github.thepoultryman.charmsnhexes.hex.hexes.BloodyDamage;
import io.github.thepoultryman.charmsnhexes.hex.hexes.ShockingBlade;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class HexRegistry {
    public static final List<String> HEXES = new ArrayList<>();

    public static BloodyDamage BLOODY_DAMAGE;
    public static ShockingBlade SHOCKING_BLADE;

    public static void registerHexes() {
        BLOODY_DAMAGE = Registry.register(Registry.ENCHANTMENT, new Id("bloody_damage"), new BloodyDamage());
        SHOCKING_BLADE = Registry.register(Registry.ENCHANTMENT, new Id("shocking_blade"), new ShockingBlade());
    }

    private static class Id extends Identifier {
        public Id(String name) {
            super(CharmsAndHexes.MOD_ID, name);
            HEXES.add(name);
        }
    }
}
