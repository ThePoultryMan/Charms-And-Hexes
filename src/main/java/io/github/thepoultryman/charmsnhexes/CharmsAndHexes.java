package io.github.thepoultryman.charmsnhexes;

import io.github.thepoultryman.charmsnhexes.registry.HexRegistry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharmsAndHexes implements ModInitializer {
    public static final String MOD_ID = "charmsnhexes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Quilting Charms and Hexes");

        HexRegistry.registerHexes();
	}
}
