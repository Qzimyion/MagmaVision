package com.qzimyion.magmavision.core;

import com.qzimyion.magmavision.common.registries.ModMobEffects;
import com.qzimyion.magmavision.core.mixin.LevelRendererAccessor;
import com.qzimyion.magmavision.platform.CommonClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluids;

public class ModEvents {
    public static void updateChunks() {
        if (((LevelRendererAccessor) Minecraft.getInstance().levelRenderer).viewArea() != null) {
            int length = ((LevelRendererAccessor) Minecraft.getInstance().levelRenderer).viewArea().chunks.length;
            for (int i = 0; i < length; i++) {
                ((LevelRendererAccessor) Minecraft.getInstance().levelRenderer).viewArea().chunks[i].dirty = true;
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
