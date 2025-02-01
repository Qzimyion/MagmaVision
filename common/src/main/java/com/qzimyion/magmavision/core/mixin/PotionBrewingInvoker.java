package com.qzimyion.magmavision.core.mixin;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PotionBrewing.class)
public interface PotionBrewingInvoker {

    @Invoker("addMix")
    static void addMix(Potion potion, Item item, Potion potion2){
        throw new AssertionError();
    }
}
