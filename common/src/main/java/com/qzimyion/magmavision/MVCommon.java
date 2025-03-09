package com.qzimyion.magmavision;

import com.qzimyion.magmavision.common.registries.ModMobEffects;
import com.qzimyion.magmavision.common.registries.ModPotions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MVCommon {
    public static final String MOD_ID = "magmavision";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void init() {
        ModMobEffects.register();
        ModPotions.register();
    }
}
