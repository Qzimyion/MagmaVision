package com.qzimyion.magmavision.forge;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.IBrewingRecipe;

import javax.annotation.Nonnull;

public class ProperBrewingRecipe extends BrewingRecipe {
    private final Ingredient input;
    private final Ingredient ingredient;
    private final ItemStack output;
    public ProperBrewingRecipe(Ingredient input, Ingredient ingredient, ItemStack output) {
        super(input, ingredient, output);
        this.input = input;
        this.ingredient = ingredient;
        this.output = output;
    }

    @Override
    public boolean isInput(@Nonnull ItemStack stack) {
        if (stack == null) {
            return false;
        } else {
            ItemStack[] matchingStacks = input.getItems();
            if (matchingStacks.length == 0) {
                return stack.isEmpty();
            } else {
                for (ItemStack itemstack : matchingStacks) {
                    if (ItemStack.isSameItem(stack, itemstack) && ItemStack.isSameItemSameTags(itemstack, stack)) {
                        return true;
                    }
                }

                return false;
            }
        }
    }
}
