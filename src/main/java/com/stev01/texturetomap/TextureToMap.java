package com.stev01.texturetomap;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.CommandManager;

public class TextureToMap implements ModInitializer {
    public static final String MOD_ID = "texturetomap";

    @Override
    public void onInitialize() {
        System.out.println("TextureToMap initialized!");

        // Register commands
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            registerCommands(dispatcher);
        });
    }

    private static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("texturetomap")
            .executes(context -> {
                context.getSource().sendFeedback(
                    () -> Text.literal("§6TextureToMap Mod v2.1.0§r - Use GUI to select blocks!"),
                    false
                );
                return 1;
            }));
    }
}
