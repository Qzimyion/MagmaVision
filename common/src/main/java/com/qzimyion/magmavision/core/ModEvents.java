package com.qzimyion.magmavision.core;

import com.qzimyion.magmavision.common.registries.ModMobEffects;
import com.qzimyion.magmavision.core.mixin.LevelRendererAccessor;
import com.qzimyion.magmavision.platform.CommonClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ViewArea;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.world.level.material.Fluids;

public class ModEvents {
    public static void updateChunks() {
        if (CommonClient.isModLoaded("sodium")){
            Minecraft.getInstance().levelRenderer.allChanged();
        } else {
            Minecraft minecraft = Minecraft.getInstance();
            ClientLevel world = minecraft.level;
            LevelRenderer levelRenderer = minecraft.levelRenderer;
            if (world != null) {
                ViewArea chunkMap = ((LevelRendererAccessor) levelRenderer).viewArea();
                if (chunkMap != null) {
                    for (ChunkRenderDispatcher.RenderChunk chunk : chunkMap.chunks) {
                        if (chunk != null) {
                            chunk.setDirty(true);
                        }
                    }
                }
            }
        }
    }
    public static boolean previousMagmaVision = false;

    public static void lavaRenderingEvent(Minecraft minecraft){
        if (minecraft.player == null) return;
        if (minecraft.player.hasEffect(ModMobEffects.MAGMA_VISION.get())){
            if (!previousMagmaVision){
                CommonClient.setRenderLayer(()-> Fluids.LAVA, RenderType.translucent());
                CommonClient.setRenderLayer(()-> Fluids.FLOWING_LAVA, RenderType.translucent());
                updateChunks();
            }
        } else {
            if (previousMagmaVision){
                CommonClient.setRenderLayer(()-> Fluids.LAVA, RenderType.solid());
                CommonClient.setRenderLayer(()-> Fluids.FLOWING_LAVA, RenderType.solid());
                updateChunks();
            }
        }
        previousMagmaVision = minecraft.player.hasEffect(ModMobEffects.MAGMA_VISION.get());
    }
}
