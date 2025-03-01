package com.qzimyion.mixin;

import com.qzimyion.core.api.IRenderLayer;
import com.qzimyion.core.effects.ModMobEffects;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ViewArea;
import net.minecraft.client.renderer.chunk.SectionRenderDispatcher;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {

    @Unique
    private static void updateChunks() {
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
    @Unique
    private static boolean previousMagmaVision = false;

    @Shadow @Nullable public LocalPlayer player;

    @Inject(at = @At("HEAD"), method = "tick")
    private void onStartTick(CallbackInfo info) {
        if (player == null) return;
        if (player.hasEffect(ModMobEffects.MAGMA_VISION)){
            if (!previousMagmaVision){
                IRenderLayer.INSTANCE.putFluids(RenderType.translucent(), Fluids.FLOWING_LAVA, Fluids.LAVA);
                updateChunks();
            }
        } else {
            if (previousMagmaVision){
                IRenderLayer.INSTANCE.putFluids(RenderType.solid(), Fluids.FLOWING_LAVA, Fluids.LAVA);
                updateChunks();
            }
        }
        previousMagmaVision = player.hasEffect(ModMobEffects.MAGMA_VISION);
    }
}
