package com.qzimyion.magmavision.common.registries;

import com.qzimyion.magmavision.core.MagmaVision;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

import static com.qzimyion.magmavision.MVCommon.MOD_ID;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(MOD_ID, Registries.MOB_EFFECT);

    public static final RegistrySupplier<MobEffect> MAGMA_VISION = EFFECTS.register("magma_vision", MagmaVision::new);

    public static void register(){
        EFFECTS.register();
    }
}
