package com.qzimyion.magmavision.forge;

import com.qzimyion.magmavision.common.registries.ModMobEffects;
import com.qzimyion.magmavision.core.ModEvents;
import com.qzimyion.magmavision.core.mixin.LevelRendererAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeEvents {

    private boolean previousLavaVision = false;
    private LiquidBlockRenderer previousFluidRenderer;

//    public static void updateChunks() {
//        if (((LevelRendererAccessor) Minecraft.getInstance().levelRenderer).viewArea() != null) {
//            int length = ((LevelRendererAccessor) Minecraft.getInstance().levelRenderer).viewArea().chunks.length;
//            for (int i = 0; i < length; i++) {
//                ((LevelRendererAccessor) Minecraft.getInstance().levelRenderer).viewArea().chunks[i].dirty = true;
//            }
//        }
//    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRenderWorldLastEvent(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
//            if (Minecraft.getInstance().player.hasEffect(ModMobEffects.MAGMA_VISION.get())) {
//                if (!previousLavaVision) {
//                    previousFluidRenderer = Minecraft.getInstance().getBlockRenderer().liquidBlockRenderer;
//                    Minecraft.getInstance().getBlockRenderer().liquidBlockRenderer = new LavaFluidRenderer();
//                    updateChunks();
//                }
//            } else {
//                if (previousLavaVision) {
//                    if (previousFluidRenderer != null) {
//                        Minecraft.getInstance().getBlockRenderer().liquidBlockRenderer = previousFluidRenderer;
//                    }
//                    updateChunks();
//                }
//            }
//            previousLavaVision = Minecraft.getInstance().player.hasEffect(ModMobEffects.MAGMA_VISION.get());
        }
    }
}
