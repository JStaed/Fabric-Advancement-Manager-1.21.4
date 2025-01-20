package net.forgedinteractive.advancementmanager;

import net.fabricmc.api.ModInitializer;
import net.forgedinteractive.advancementmanager.config.ModConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvancementManager implements ModInitializer {
	public static final String MOD_ID = "advancementmanager";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();

		if (ModConfigs.ADVANCEMENT_OVERRIDE_ALL == 1) {
			LOGGER.info("ADVANCEMENTS OVERRIDDEN!!!!");
		}

		LOGGER.info("Successfully Initialized " + AdvancementManager.MOD_ID);
	}
}