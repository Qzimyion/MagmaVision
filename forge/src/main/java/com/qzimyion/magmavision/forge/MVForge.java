package com.qzimyion.magmavision.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.qzimyion.magmavision.MVCommon;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Mod(MVCommon.MOD_ID)
public final class MVForge {
    public MVForge() {
        EventBuses.registerModEventBus(MVCommon.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        IEventBus modBusEvent = FMLJavaModLoadingContext.get().getModEventBus();
        MVCommon.init();
    }


    //Copied from blueprint:https://github.com/team-abnormals/blueprint/blob/1.20.x/src/main/java/com/teamabnormals/blueprint/core/util/DataUtil.java
    private static final Method ADD_MIX_METHOD = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "m_43513_", Potion.class, Item.class, Potion.class);

    public static void addMix(Potion input, Item reactant, Potion result) {
        try {
            ADD_MIX_METHOD.invoke(null, input, reactant, result);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Failed to add mix for " + ForgeRegistries.POTIONS.getKey(result) + " from " + ForgeRegistries.ITEMS.getKey(reactant), e);
        }
    }
}
