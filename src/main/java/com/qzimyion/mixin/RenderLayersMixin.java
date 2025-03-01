package com.qzimyion.mixin;

import com.qzimyion.core.api.RenderLayerImpl;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(ItemBlockRenderTypes.class)
public class RenderLayersMixin {

    @Shadow
    @Final
    private static Map<Fluid, RenderType> TYPE_BY_FLUID;


    @Inject(method = "<clinit>*", at = @At("RETURN"))
    private static void onInitialize(CallbackInfo info) {
        RenderLayerImpl.initialize(TYPE_BY_FLUID::put);
    }
}
