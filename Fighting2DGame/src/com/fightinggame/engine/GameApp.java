// Updated GameApp.java to support two players with separate inputs and sprite folders
package com.fightinggame.engine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.fightinggame.input.InputHandler;
import com.fightinggame.player.Player;

public class GameApp extends Application {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Pane root;
    private InputHandler input;
    private Player player1, player2;

    private AnimationTimer gameLoop;

    private long lastLeftPressP1 = 0, lastRightPressP1 = 0;
    private long lastLeftPressP2 = 0, lastRightPressP2 = 0;
    private static final long DASH_WINDOW = 250_000_000; // 250ms

    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        input = new InputHandler();
        input.attachListeners(scene);

        player1 = new Player(200, 300, "res/Knight1", true);
        player2 = new Player(600, 300, "res/Knight2", false);

        player1.render(root);
        player2.render(root);

        primaryStage.setTitle("2D Fighting Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        initGameLoop();
        gameLoop.start();
    }

    private void initGameLoop() {
        gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate > 0) {
                    double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
                    update(deltaTime);
                }
                lastUpdate = now;
            }
        };
    }

    private void update(double deltaTime) {
        long now = System.nanoTime();

        // --- Player 1 Input ---
        if (input.isJustPressed("A")) {
            if (now - lastLeftPressP1 < DASH_WINDOW) {
                player1.triggerDash("left");
                lastLeftPressP1 = 0;
            } else {
                lastLeftPressP1 = now;
            }
        }

        if (input.isJustPressed("D")) {
            if (now - lastRightPressP1 < DASH_WINDOW) {
                player1.triggerDash("right");
                lastRightPressP1 = 0;
            } else {
                lastRightPressP1 = now;
            }
        }

        if (input.isKeyPressed("A")) player1.moveLeft(deltaTime);
        else if (input.isKeyPressed("D")) player1.moveRight(deltaTime);
        else player1.decelerate(deltaTime);

        if (input.isKeyPressed("W")) player1.jump();
        player1.crouch(input.isKeyPressed("S"));
        player1.handleInput(input); // includes Q for roll, SHIFT for slide, R for attack

        // --- Player 2 Input (I, J, K, L, U, SHIFT for Player 2) ---
        if (input.isJustPressed("J")) {
            if (now - lastLeftPressP2 < DASH_WINDOW) {
                player2.triggerDash("left");
                lastLeftPressP2 = 0;
            } else {
                lastLeftPressP2 = now;
            }
        }

        if (input.isJustPressed("L")) {
            if (now - lastRightPressP2 < DASH_WINDOW) {
                player2.triggerDash("right");
                lastRightPressP2 = 0;
            } else {
                lastRightPressP2 = now;
            }
        }

        if (input.isKeyPressed("J")) player2.moveLeft(deltaTime);
        else if (input.isKeyPressed("L")) player2.moveRight(deltaTime);
        else player2.decelerate(deltaTime);

        if (input.isKeyPressed("I")) player2.jump();
        player2.crouch(input.isKeyPressed("K"));

        // Player 2 actions
        if (input.isJustPressed("U")) player2.triggerAttack();
        if (input.isJustPressed("SHIFT")) player2.triggerSlide();
        if (input.isJustPressed("Q")) player2.triggerRoll();

        // Update
        player1.update(deltaTime);
        player2.update(deltaTime);

        input.endFrame();
    }
}
