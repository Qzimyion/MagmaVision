package com.qzimyion.magmavision.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Supplier;

public class CommonClient {

    @ExpectPlatform
    public static void setRenderLayer(Fluid fluid, RenderType renderType) {
        throw new AssertionError();
    }


    @ExpectPlatform
    public static boolean isModLoaded(String name) {
        throw new AssertionError();
    }
}
