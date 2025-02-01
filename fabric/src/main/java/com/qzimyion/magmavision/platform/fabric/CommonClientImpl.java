package com.qzimyion.magmavision.platform.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class CommonClientImpl {
    public static <T extends Fluid> void setRenderLayer(Supplier<T> fluid, RenderType renderType) {
        BlockRenderLayerMap.INSTANCE.putFluid(fluid.get(), renderType);
    }
}
