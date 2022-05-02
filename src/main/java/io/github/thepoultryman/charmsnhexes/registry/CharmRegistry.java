package io.github.thepoultryman.charmsnhexes.registry;

import io.github.thepoultryman.charmsnhexes.CharmsAndHexes;
import io.github.thepoultryman.charmsnhexes.charm.charms.HealingGrasp;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class CharmRegistry {
    public static final List<String> CHARMS = new ArrayList<>();

    public static HealingGrasp HEALING_GRASP;

    public static void registerCharms() {
        HEALING_GRASP = Registry.register(Registry.ENCHANTMENT, new Id("healing_grasp"), new HealingGrasp());
    }

    private static class Id extends Identifier {
        public Id(String name) {
            super(CharmsAndHexes.MOD_ID, name);
            CHARMS.add(name);
        }
    }
}
