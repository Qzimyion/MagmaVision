package com.qzimyion.core.potions;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotionRecipes {
    public static void register(){
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.NIGHT_VISION, Ingredient.of(Items.MAGMA_CREAM), ModPotions.MAGMA_VISION);
            builder.registerPotionRecipe(ModPotions.MAGMA_VISION, Ingredient.of(Items.REDSTONE), ModPotions.LONG_MAGMA_VISION);
            builder.registerPotionRecipe(Potions.LONG_NIGHT_VISION, Ingredient.of(Items.MAGMA_CREAM), ModPotions.LONG_MAGMA_VISION);
        });
    }
}
