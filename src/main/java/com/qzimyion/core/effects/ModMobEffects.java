package com.qzimyion.core.effects;

import com.qzimyion.MagmaVision;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

public class ModMobEffects {

    public static final Holder<MobEffect> MAGMA_VISION;

    static {
        MAGMA_VISION = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.tryBuild(MagmaVision.MOD_ID, "magma_vision"), new MagmaVisionMobEffect());
    }

    public static void register(){
        MagmaVision.LOGGER.info("Registering mod Status Effects");
    }
}
