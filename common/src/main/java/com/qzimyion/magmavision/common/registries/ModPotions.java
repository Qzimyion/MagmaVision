package com.qzimyion.magmavision.common.registries;

import com.qzimyion.magmavision.MVCommon;
import com.qzimyion.magmavision.core.mixin.PotionBrewingInvoker;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

import static com.qzimyion.magmavision.MVCommon.MOD_ID;

public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(MOD_ID, Registries.POTION);

    public static final RegistrySupplier<Potion> MV_SHORT = POTIONS.register("magma_vision",
            ()-> new Potion(new MobEffectInstance(ModMobEffects.MAGMA_VISION.get(), 3600, 0)));
    public static final RegistrySupplier<Potion> MV_LONG = POTIONS.register("magma_vision_long",
            ()-> new Potion(new MobEffectInstance(ModMobEffects.MAGMA_VISION.get(), 9600, 0)));

    public static void register(){
        POTIONS.register();
        MVCommon.LOGGER.info("Registering mod potions");
    }

}
