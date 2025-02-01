package com.qzimyion.magmavision;

import com.qzimyion.magmavision.common.registries.ModMobEffects;
import com.qzimyion.magmavision.common.registries.ModPotions;

public final class MVCommon {
    public static final String MOD_ID = "magmavision";

    public static void init() {
        ModMobEffects.register();
        ModPotions.register();
    }
}
