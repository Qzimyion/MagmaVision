package com.qzimyion;

import com.qzimyion.core.effects.ModMobEffects;
import com.qzimyion.core.potions.ModPotions;
import com.qzimyion.core.potions.ModPotionRecipes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagmaVision implements ModInitializer {
	public static final String MOD_ID = "magma-vision";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModMobEffects.register();
		ModPotions.register();
		ModPotionRecipes.register();
	}


}