package com.qzimyion.core.api;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;

public interface IRenderLayer {

    IRenderLayer INSTANCE = new RenderLayerImpl();

    void putFluid(Fluid fluid, RenderType renderLayer);

    void putFluids(RenderType renderLayer, Fluid... fluids);
}
