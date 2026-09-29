package com.stev01.texturetomap.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import net.minecraft.text.Text;
import com.stev01.texturetomap.client.gui.BlockSelectorScreen;

public class TextureToMapClient implements ClientModInitializer {
    public static KeyBinding openGuiKey;

    @Override
    public void onInitializeClient() {
        System.out.println("TextureToMap Client initialized!");

        // Register keybinding
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.texturetomap.open_gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_T,
            "category.texturetomap"
        ));

        // Register client commands
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(net.minecraft.client.command.ClientCommandManager.literal("texturetomap_gui")
                .executes(context -> {
                    context.getSource().sendFeedback(Text.literal("Opening TextureToMap GUI..."));
                    return 1;
                }));
        });

        // Key press listener
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                if (client.player != null) {
                    client.setScreen(new BlockSelectorScreen(Text.literal("Select Block")));
                }
            }
        });
    }
}
