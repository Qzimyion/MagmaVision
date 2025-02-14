package com.qzimyion.core.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class MagmaVisionMobEffect extends MobEffect {
    public MagmaVisionMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xcd6f3e);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int i, int j) {
        return true;
    }
}
