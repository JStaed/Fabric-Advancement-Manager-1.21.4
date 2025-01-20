package net.forgedinteractive.advancementmanager.mixin;

import net.forgedinteractive.advancementmanager.config.ModConfigs;
import net.minecraft.client.toast.AdvancementToast;
import net.minecraft.client.toast.RecipeToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ToastManager.class)
public abstract class MixinToastManager {

    // Prevent advancement popups
    @Inject(method = "add", at = @At("HEAD"), cancellable = true)
    public void add(Toast toast, CallbackInfo cb) {
        if ((toast instanceof AdvancementToast && ModConfigs.ENABLE_ADVANCEMENT_TOASTS == 0)
                || (toast instanceof RecipeToast && ModConfigs.ENABLE_RECIPE_TOASTS == 0)) {
            cb.cancel();
        }
    }
}
