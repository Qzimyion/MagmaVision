package com.qzimyion.magmavision.forge.api;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BlockRenderLayerMapImplForForge implements BlockRenderLayerMapForForge {
    @Override
    public void putFluid(Fluid fluid, RenderType renderLayer) {
        if (fluid == null) throw new IllegalArgumentException("Request to map null fluid to BlockRenderLayer");
        if (renderLayer == null) throw new IllegalArgumentException("Request to map fluid " + fluid.toString() + " to null BlockRenderLayer");

        fluidHandler.accept(fluid, renderLayer);
    }
    private static Map<Fluid, RenderType> fluidRenderLayerMap = new HashMap<>();
    private static BiConsumer<Fluid, RenderType> fluidHandler = (f, b) -> fluidRenderLayerMap.put(f, b);

    public static void initialize(BiConsumer<Fluid, RenderType> fluidHandlerIn) {
        fluidRenderLayerMap.forEach(fluidHandlerIn);
        fluidHandler = fluidHandlerIn;
    }
}
