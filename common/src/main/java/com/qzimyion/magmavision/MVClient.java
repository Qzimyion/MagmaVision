package com.qzimyion.magmavision;

import com.qzimyion.magmavision.common.registries.ModMobEffects;
import com.qzimyion.magmavision.core.mixin.LevelRendererAccessor;
import com.qzimyion.magmavision.platform.CommonClient;
import dev.architectury.event.events.client.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ViewArea;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;

public class MVClient {

    private static void updateChunks() {
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
    private static boolean previousMagmaVision = false;
    private static LiquidBlockRenderer previousRenderer;

    public static void init(){
        ClientTickEvent.CLIENT_POST.register(minecraft -> {
            if (minecraft.player == null) return;
            if (minecraft.player.hasEffect(ModMobEffects.MAGMA_VISION.get())){
                if (!previousMagmaVision){
                    previousRenderer = Minecraft.getInstance().getBlockRenderer().liquidBlockRenderer;
                    Minecraft.getInstance().getBlockRenderer().liquidBlockRenderer = new MagmaVisionRenderer();
                    updateChunks();
                }
            } else {
                if (previousMagmaVision && previousRenderer != null){
                    Minecraft.getInstance().getBlockRenderer().liquidBlockRenderer = previousRenderer;
                    updateChunks();
                }
            }
            previousMagmaVision = minecraft.player.hasEffect(ModMobEffects.MAGMA_VISION.get());
        });

        MVCommon.LOGGER.info("Registering mod events");
    }
}
