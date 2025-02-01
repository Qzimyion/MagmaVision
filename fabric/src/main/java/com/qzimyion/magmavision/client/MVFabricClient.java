package com.qzimyion.magmavision.client;

import com.qzimyion.magmavision.core.ModEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public final class MVFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(ModEvents::lavaRenderingEvent);
    }
}
