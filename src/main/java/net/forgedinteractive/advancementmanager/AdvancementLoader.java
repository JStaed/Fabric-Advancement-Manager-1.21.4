package net.forgedinteractive.advancementmanager;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.forgedinteractive.advancementmanager.config.ModConfigs;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.FileUtils;
import org.spongepowered.asm.mixin.Interface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public abstract class AdvancementLoader implements ServerLifecycleEvents.ServerStarting {

    private static final Path configDir = FabricLoader.getInstance().getConfigDir();

    public static void loadAdvancements() {
        File f = new File(configDir.toAbsolutePath() + "\\AdvancementManager");
        if (f.mkdir()) {return;}
        for (File advancement : f.listFiles()) {
            try {
                FileUtils.copyFile(advancement, new File("datapacks\\advancementmanager\\data\\advancementmanager\\advancement\\" + advancement.getName()));
            } catch (Exception e) {
                AdvancementManager.LOGGER.error(e.toString());
            }
        }
        try {
            FileWriter mcMeta = new FileWriter("datapacks\\advancementmanager\\pack.mcmeta");
            mcMeta.write("{\"pack\": {\"pack_format\": 61,\"description\": \"Advancement Manager\"}}");
            mcMeta.close();
        } catch (IOException e) {
            AdvancementManager.LOGGER.error(e.toString());
        }

    }
}
