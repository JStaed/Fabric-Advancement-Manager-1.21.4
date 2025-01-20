package net.forgedinteractive.advancementmanager;

import net.fabricmc.api.ModInitializer;
import net.minecraft.advancement.PlacedAdvancement;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AdvancementManager implements ModInitializer {
	public static final String MOD_ID = "advancementmanager";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final net.minecraft.advancement.AdvancementManager advManager = new net.minecraft.advancement.AdvancementManager();

	@Override
	public void onInitialize() {
		Collection<PlacedAdvancement> advancements = advManager.getAdvancements();
		Set<Identifier> advancementIDs = new HashSet<Identifier>();
		for(PlacedAdvancement adv : advancements) {
			advancementIDs.add(adv.getAdvancementEntry().id());
		}
		advManager.removeAll(advancementIDs);
		LOGGER.info("Hello Fabric world!");
	}
}