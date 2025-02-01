package com.qzimyion.magmavision.core;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MagmaVision extends MobEffect {

    public MagmaVision() {
        super(MobEffectCategory.BENEFICIAL, 0xcd6f3e);
    }

    @Override
    public boolean isDurationEffectTick(int i, int j) {
        return true;
    }
}
