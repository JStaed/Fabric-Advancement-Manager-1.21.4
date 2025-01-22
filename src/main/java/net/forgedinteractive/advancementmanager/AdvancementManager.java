package net.forgedinteractive.advancementmanager;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.forgedinteractive.advancementmanager.config.ModConfigs;
import net.forgedinteractive.advancementmanager.eventhandlers.DatapackStarter;
import net.forgedinteractive.advancementmanager.eventhandlers.DatapackStopper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvancementManager implements ModInitializer {
	public static final String MOD_ID = "advancementmanager";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		AdvancementLoader.loadAdvancements();
		ModConfigs.registerConfigs();
		ServerPlayConnectionEvents.JOIN.register(new DatapackStarter());
		ServerLifecycleEvents.SERVER_STOPPED.register(new DatapackStopper());
		LOGGER.info("Successfully Initialized " + AdvancementManager.MOD_ID);
	}


}