package com.loucaskreger.simpletexthud.client;

import net.minecraft.client.MinecraftClient;

import java.util.HashMap;
import java.util.Map;

public class FormatStringParser {

    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
    private static Map<String, Object> VARIABLES = new HashMap<String, Object>();
    static {
        tick();
    }

    public FormatStringParser() {
    }

    public String parse(String string) {
        String formatted = "";
        for (int i = 0; i < string.length(); i++) {
            char token = string.charAt(i);
            if (token == '{') {
                String variable = "";
                while (i < string.length() && token != '}') {
                    variable += string.charAt(i++);
                }
                System.out.println(VARIABLES.get(variable));
                formatted += VARIABLES.get(variable);
            } else {
                formatted += token;
            }
        }
        return formatted;
    }

    public static void tick() {
        VARIABLES.put("{time}", CLIENT.world.getTime() / 20);
        VARIABLES.put("{days}", CLIENT.world.getTimeOfDay() / 24_000);
    }

}
