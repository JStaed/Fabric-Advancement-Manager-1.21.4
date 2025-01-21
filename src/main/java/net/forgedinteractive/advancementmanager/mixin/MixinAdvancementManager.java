package net.forgedinteractive.advancementmanager.mixin;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.forgedinteractive.advancementmanager.AdvancementLoader;
import net.forgedinteractive.advancementmanager.AdvancementManager;
import net.forgedinteractive.advancementmanager.config.ModConfigs;
import net.forgedinteractive.advancementmanager.eventhandlers.DatapackStarter;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.block.entity.VaultBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.level.storage.LevelStorage;
import org.apache.commons.io.FileUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oshi.util.FileUtil;

import java.io.File;

@Mixin(PlayerAdvancementTracker.class)
public abstract class MixinAdvancementManager {

    @Shadow
    protected abstract void beginTracking(AdvancementEntry advancement);

    // Remove vanilla advancement tracker
    @Inject(method = "beginTrackingAllAdvancements", at = @At("HEAD"), cancellable = true)
    public void beginTrackingAllAdvancements(ServerAdvancementLoader advancementLoader, CallbackInfo cb) {
        File savesDir = new File("saves");
        for (String s : savesDir.list()) {
            String targetPath = savesDir.getAbsolutePath() + "\\" + s + "\\datapacks";
            try {
                FileUtils.copyDirectory(new File("datapacks"), new File(targetPath));
            } catch (Exception ignore) {}
        }
        if (ModConfigs.ENABLE_VANILLA_ADVANCEMENTS == 0) {
            if (ModConfigs.ENABLE_RECIPE_BOOK == 1) {
                for (AdvancementEntry advancement : advancementLoader.getAdvancements()) {
                    if (advancement.toString().contains("advancementmanager")) {
                        DatapackStarter.datapackLoaded = true;
                    }
                    if (advancement.toString().contains(":recipes") || advancement.toString().contains("advancementmanager")) {
                        beginTracking(advancement);
                    }
                }
            }
            cb.cancel();
        }
    }


}
