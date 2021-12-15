package com.loucaskreger.simpletexthud;

import com.loucaskreger.simpletexthud.client.EditHudScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class SimpleTextHud implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("simpletexthud");
    private static final KeyBinding hudEditorKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.simpletexthud.hudeditor", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, "category.simpletexthud.hud"));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (hudEditorKey.isPressed()) {
                client.setScreen(new EditHudScreen());
            }
        });
    }
}
