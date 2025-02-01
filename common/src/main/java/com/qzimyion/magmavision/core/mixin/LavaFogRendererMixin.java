package com.qzimyion.magmavision.core.mixin;

import com.qzimyion.magmavision.common.registries.ModMobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.client.renderer.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FogRenderer.class)
public class LavaFogRendererMixin {

    @Redirect(method = "setupFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isSpectator()Z", ordinal = 0))
    private static boolean setupFog(Entity entity) {
        if (entity instanceof Player player) {
            if (player.hasEffect(ModMobEffects.MAGMA_VISION.get())) {
                return true;
            }
        }
        return entity.isSpectator();
    }
}
