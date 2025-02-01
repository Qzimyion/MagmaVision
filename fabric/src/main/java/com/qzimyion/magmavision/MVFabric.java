package com.qzimyion.magmavision;

import com.qzimyion.magmavision.common.registries.ModPotions;
import com.qzimyion.magmavision.core.mixin.PotionBrewingInvoker;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;

public final class MVFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        MVCommon.init();
        //Potion recipes
        PotionBrewingInvoker.addMix(Potions.NIGHT_VISION, Items.MAGMA_CREAM, ModPotions.MV_SHORT.get());
        PotionBrewingInvoker.addMix(ModPotions.MV_SHORT.get(), Items.REDSTONE, ModPotions.MV_LONG.get());
        PotionBrewingInvoker.addMix(Potions.LONG_NIGHT_VISION, Items.MAGMA_CREAM, ModPotions.MV_LONG.get());
    }
}
