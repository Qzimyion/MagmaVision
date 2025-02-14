package com.qzimyion.core.potions;

import com.qzimyion.MagmaVision;
import com.qzimyion.core.effects.ModMobEffects;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;

public class ModPotions {

    public static final Potion MAGMA_VISION = Registry.register(BuiltInRegistries.POTION, ResourceLocation.fromNamespaceAndPath(MagmaVision.MOD_ID, "magma_vision"), new Potion("magma_vision", new MobEffectInstance(ModMobEffects.MAGMA_VISION, 3600, 0)));
    public static final Potion LONG_MAGMA_VISION = Registry.register(BuiltInRegistries.POTION, ResourceLocation.fromNamespaceAndPath(MagmaVision.MOD_ID, "long_magma_vision"), new Potion("long_magma_vision", new MobEffectInstance(ModMobEffects.MAGMA_VISION, 9600, 0)));

    public static void register(){
        MagmaVision.LOGGER.info("Registering mod Potions");
    }
}
