package com.fightinggame.input;

import javafx.scene.Scene;
import java.util.HashSet;
import java.util.Set;

public class InputHandler {

    private final Set<String> activeKeys = new HashSet<>();
    private final Set<String> justPressedKeys = new HashSet<>();

    public void attachListeners(Scene scene) {
        scene.setOnKeyPressed(event -> {
            String code = event.getCode().toString();
            if (!activeKeys.contains(code)) {
                justPressedKeys.add(code); // Only added on first press
            }
            activeKeys.add(code);
        });

        scene.setOnKeyReleased(event -> {
            String code = event.getCode().toString();
            activeKeys.remove(code);
        });
    }

    public boolean isKeyPressed(String keyName) {
        return activeKeys.contains(keyName);
    }

    public boolean isJustPressed(String keyName) {
        return justPressedKeys.contains(keyName);
    }

    public void endFrame() {
        justPressedKeys.clear(); // Called at the end of each frame
    }
}
