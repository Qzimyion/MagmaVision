package com.qzimyion.magmavision.forge.api;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;

public interface BlockRenderLayerMapForForge {
    BlockRenderLayerMapForForge INSTANCE = new BlockRenderLayerMapImplForForge();

    void putFluid(Fluid fluid, RenderType renderLayer);
}
