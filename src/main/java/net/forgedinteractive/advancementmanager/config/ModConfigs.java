package net.forgedinteractive.advancementmanager.config;

import com.mojang.datafixers.util.Pair;
import net.forgedinteractive.advancementmanager.AdvancementManager;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static int ENABLE_ADVANCEMENT_TOASTS;
    public static int ENABLE_RECIPE_TOASTS;
    public static int ENABLE_RECIPE_BOOK;
    public static int ENABLE_VANILLA_ADVANCEMENTS;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(AdvancementManager.MOD_ID).provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("advancements.vanilla", 0), "int 0/1");
        configs.addKeyValuePair(new Pair<>("advancements.recipe-book", 1), "int 0/1");
        configs.addKeyValuePair(new Pair<>("toasts.advancements", 1), "int 0/1");
        configs.addKeyValuePair(new Pair<>("toasts.recipe-book", 1), "int 0/1");
    }

    private static void assignConfigs() {

        ENABLE_RECIPE_BOOK = CONFIG.getOrDefault("advancements.recipe-book", 1);
        ENABLE_VANILLA_ADVANCEMENTS = CONFIG.getOrDefault("advancements.vanilla", 0);
        ENABLE_ADVANCEMENT_TOASTS = CONFIG.getOrDefault("toasts.advancements", 1);
        ENABLE_RECIPE_TOASTS = CONFIG.getOrDefault("toasts.recipe-book", 1);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
