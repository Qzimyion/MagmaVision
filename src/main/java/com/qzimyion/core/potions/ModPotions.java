package com.qzimyion.core.potions;

import com.qzimyion.MagmaVision;
import com.qzimyion.core.effects.ModMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {

    public static final Holder<Potion> MAGMA_VISION = register("magma_vision", new Potion(new MobEffectInstance(ModMobEffects.MAGMA_VISION, 3600, 0)));
    public static final Holder<Potion> LONG_MAGMA_VISION = register("long_magma_vision", new Potion(new MobEffectInstance(ModMobEffects.MAGMA_VISION, 9600, 0)));

    private static Holder<Potion> register(String id, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, ResourceLocation.tryBuild(MagmaVision.MOD_ID, id), potion);
    }

    public static void register(){
        MagmaVision.LOGGER.info("Registering mod Potions");
    }
}
