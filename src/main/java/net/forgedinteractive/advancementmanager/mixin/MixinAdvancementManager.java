package net.forgedinteractive.advancementmanager.mixin;

import net.forgedinteractive.advancementmanager.config.ModConfigs;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.ServerAdvancementLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerAdvancementTracker.class)
public abstract class MixinAdvancementManager {

    @Shadow
    protected abstract void beginTracking(AdvancementEntry advancement);

    // Remove vanilla advancement tracker
    @Inject(method = "beginTrackingAllAdvancements", at = @At("HEAD"), cancellable = true)
    public void beginTrackingAllAdvancements(ServerAdvancementLoader advancementLoader, CallbackInfo cb) {
        if (ModConfigs.ENABLE_VANILLA_ADVANCEMENTS == 0) {
            if (ModConfigs.ENABLE_RECIPE_BOOK == 1) {
                for (AdvancementEntry advancement : advancementLoader.getAdvancements()) {
                    if (advancement.toString().contains(":recipes") || advancement.toString().contains("advancementmanager")) {
                        beginTracking(advancement);
                    }
                }
            }
            cb.cancel();
        }
    }
}
