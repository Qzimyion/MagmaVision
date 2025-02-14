package com.qzimyion.core.potions;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotionRecipes {
    public static void register(){
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.NIGHT_VISION, Ingredient.of(Items.MAGMA_CREAM), BuiltInRegistries.POTION.wrapAsHolder(ModPotions.MAGMA_VISION));
            builder.registerPotionRecipe(BuiltInRegistries.POTION.wrapAsHolder(ModPotions.MAGMA_VISION), Ingredient.of(Items.REDSTONE), BuiltInRegistries.POTION.wrapAsHolder(ModPotions.LONG_MAGMA_VISION));
            builder.registerPotionRecipe(Potions.LONG_NIGHT_VISION, Ingredient.of(Items.MAGMA_CREAM), BuiltInRegistries.POTION.wrapAsHolder(ModPotions.LONG_MAGMA_VISION));
        });
    }
}
