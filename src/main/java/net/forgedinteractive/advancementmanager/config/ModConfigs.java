package net.forgedinteractive.advancementmanager.config;

import com.mojang.datafixers.util.Pair;
import net.forgedinteractive.advancementmanager.AdvancementManager;
import net.forgedinteractive.advancementmanager.config.ModConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static int ADVANCEMENT_OVERRIDE_ALL;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(AdvancementManager.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("advancements.override.all", 1), "int 0/1");
    }

    private static void assignConfigs() {
        ADVANCEMENT_OVERRIDE_ALL = CONFIG.getOrDefault("advancements.override.all", 1);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
