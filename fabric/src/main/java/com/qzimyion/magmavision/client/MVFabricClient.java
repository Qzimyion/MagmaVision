package com.qzimyion.magmavision.client;

import com.qzimyion.magmavision.MVClient;
import net.fabricmc.api.ClientModInitializer;

public final class MVFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MVClient.init();
    }
}
