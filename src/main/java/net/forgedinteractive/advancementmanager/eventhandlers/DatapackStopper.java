package net.forgedinteractive.advancementmanager.eventhandlers;


import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class DatapackStopper implements ServerLifecycleEvents.ServerStopped {
    @Override
    public void onServerStopped(MinecraftServer minecraftServer) {
        DatapackStarter.datapackLoaded = false;
    }
}