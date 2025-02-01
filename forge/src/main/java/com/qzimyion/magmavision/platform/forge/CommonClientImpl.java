package com.qzimyion.magmavision.platform.forge;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class CommonClientImpl {
    public static <T extends Fluid> void setRenderLayer(Supplier<T> fluid, RenderType renderType){
        ItemBlockRenderTypes.setRenderLayer(fluid.get(), renderType);
    }
}
