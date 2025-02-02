package com.qzimyion.magmavision.forge;

import com.qzimyion.magmavision.common.registries.ModMobEffects;
import com.qzimyion.magmavision.core.mixin.BlockRenderDispatcherAccessor;
import com.qzimyion.magmavision.core.mixin.LevelRendererAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEvents {

    private static void updateChunks() {
        if (((LevelRendererAccessor) Minecraft.getInstance().levelRenderer).viewArea() != null) {
            int length =((LevelRendererAccessor) Minecraft.getInstance().levelRenderer).viewArea().chunks.length;
            for (int i = 0; i < length; i++) {
                ((LevelRendererAccessor) Minecraft.getInstance().levelRenderer).viewArea().chunks[i].dirty = true;
            }
        }
    }

    private static boolean previousMagmaVision = false;
    private LiquidBlockRenderer previousFluidRenderer;
    public LiquidBlockRenderer liquidBlockRenderer;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    @OnlyIn(Dist.CLIENT)
    public void onRenderWorldLastEvent(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            if (Minecraft.getInstance().player == null) return;
            if (Minecraft.getInstance().player.hasEffect(ModMobEffects.MAGMA_VISION.get())) {
                if (!previousMagmaVision) {
                    liquidBlockRenderer = new MagmaVisionFluidRenderer();
                    updateChunks();
                }
            } else {
                if (previousMagmaVision) {
                    if (previousFluidRenderer != null) {
                         liquidBlockRenderer = previousFluidRenderer;
                    }
                    updateChunks();
                }
            }
            previousFluidRenderer = ((BlockRenderDispatcherAccessor) Minecraft.getInstance().getBlockRenderer()).liquidBlockRenderer();
            previousMagmaVision = Minecraft.getInstance().player.hasEffect(ModMobEffects.MAGMA_VISION.get());
        }
    }

}
