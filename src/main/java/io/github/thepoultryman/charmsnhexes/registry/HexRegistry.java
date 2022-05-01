package io.github.thepoultryman.charmsnhexes.registry;

import io.github.thepoultryman.charmsnhexes.CharmsAndHexes;
import io.github.thepoultryman.charmsnhexes.Hex;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class HexRegistry {
    public static final List<Hex> HEXES = new ArrayList<>();

    public static Hex BLOODY_DAMAGE;

    public static void registerHexes() {
        BLOODY_DAMAGE = register("bloody_damage", new Hex());
    }

    private static Hex register(String name, Hex hex) {
        HEXES.add(hex);
        return Registry.register(Registry.ENCHANTMENT, new Identifier(CharmsAndHexes.MOD_ID, name), hex);
    }
}
