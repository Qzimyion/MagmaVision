package com.qzimyion;

import com.qzimyion.core.effects.ModMobEffects;
import com.qzimyion.core.potions.ModPotions;
import com.qzimyion.core.potions.ModPotionRecipes;
import com.qzimyion.mixin.LevelRendererAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ViewArea;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.client.renderer.chunk.SectionRenderDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagmaVision implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "magma-vision";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModMobEffects.register();
		ModPotions.register();
		ModPotionRecipes.register();
	}

	@Override
	public void onInitializeClient() {
		ClientTickEvents.START_CLIENT_TICK.register((minecraft) -> {
			if (minecraft.player == null) return;
			if (minecraft.player.hasEffect(ModMobEffects.MAGMA_VISION)){
				if (!previousMagmaVision){
					BlockRenderLayerMap.INSTANCE.putFluids(RenderType.translucent(), Fluids.FLOWING_LAVA, Fluids.LAVA);
					updateChunks();
				}
			} else {
				if (previousMagmaVision){
					BlockRenderLayerMap.INSTANCE.putFluids(RenderType.solid(), Fluids.FLOWING_LAVA, Fluids.LAVA);
					updateChunks();
				}
			}
			previousMagmaVision = minecraft.player.hasEffect(ModMobEffects.MAGMA_VISION);
		});
	}

	public static void updateChunks() {
		if (FabricLoader.getInstance().isModLoaded("sodium")){
			Minecraft.getInstance().levelRenderer.allChanged();
		} else {
			Minecraft minecraft = Minecraft.getInstance();
			ClientLevel world = minecraft.level;
			LevelRenderer levelRenderer = minecraft.levelRenderer;
			if (world != null) {
				ViewArea chunkMap = ((LevelRendererAccessor) levelRenderer).viewArea();
				if (chunkMap != null) {
					for (SectionRenderDispatcher.RenderSection chunk : chunkMap.sections) {
						if (chunk != null) {
							chunk.setDirty(true);
						}
					}
				}
			}
		}
	}
	public static boolean previousMagmaVision = false;
}