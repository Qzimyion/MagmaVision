package com.qzimyion.magmavision.datagen.lang;

import com.qzimyion.magmavision.common.registries.ModMobEffects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ENUSLangGen extends FabricLanguageProvider {
    public ENUSLangGen(FabricDataOutput dataOutput) {
        super(dataOutput ,"en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModMobEffects.MAGMA_VISION.get(), "Magma Vision");
    }
}
