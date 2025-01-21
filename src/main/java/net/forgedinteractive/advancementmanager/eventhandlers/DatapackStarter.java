package net.forgedinteractive.advancementmanager.eventhandlers;


import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class DatapackStarter implements ServerPlayConnectionEvents.Join {
    public static boolean datapackLoaded = false;

    @Override
    public void onPlayReady(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer server) {
        if (!datapackLoaded) {
            CommandManager cmdManager = server.getCommandManager();
            cmdManager.executeWithPrefix(server.getCommandSource(), "datapack list");
            cmdManager.executeWithPrefix(server.getCommandSource(), "datapack enable \"file/advancementmanager\"");
        }
    }
}
