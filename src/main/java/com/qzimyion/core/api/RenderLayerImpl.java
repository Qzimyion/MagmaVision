package com.qzimyion.core.api;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class RenderLayerImpl implements IRenderLayer{
    public RenderLayerImpl() {}

    private static final Map<Fluid, RenderType> FLUID_RENDER_LAYER_MAP = new HashMap<>();
    private static BiConsumer<Fluid, RenderType> fluidHandler = FLUID_RENDER_LAYER_MAP::put;

    @Override
    public void putFluid(Fluid fluid, RenderType renderLayer) {
        if (fluid == null) throw new IllegalArgumentException("Request to map null fluid to RenderLayer");
        if (renderLayer == null) throw new IllegalArgumentException("Request to map fluid " + fluid + " to null RenderLayer");
        fluidHandler.accept(fluid, renderLayer);
    }

    @Override
    public void putFluids(RenderType renderLayer, Fluid... fluids) {
        for (Fluid fluid : fluids) {
            putFluid(fluid, renderLayer);
        }
    }

    public static void initialize(BiConsumer<Fluid, RenderType> fluidHandlerIn) {
        FLUID_RENDER_LAYER_MAP.forEach(fluidHandlerIn);
        fluidHandler = fluidHandlerIn;
    }
}
