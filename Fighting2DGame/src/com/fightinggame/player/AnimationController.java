package com.fightinggame.player;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class AnimationController {

    private final ImageView spriteView;
    private final Map<String, Image> animationSheets = new HashMap<>();
    private final Map<String, Integer> frameCounts = new HashMap<>();

    private String currentAnimation = "idle";
    private int frameIndex = 0;
    private double frameTime = 0;
    private int frameWidth = 120;
    private int frameHeight = 80;
    private boolean facingRight = true;

    private boolean playOnce = false;
    private double durationLeft = 0;
    private double frameDuration = 0;
    
    private int currentFrame = 0;

    public AnimationController(int frameWidth, int frameHeight) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.spriteView = new ImageView();
        this.spriteView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
    }

    public void load(String name, String filePath, int frames) {
        Image img = new Image("file:" + filePath);
        animationSheets.put(name, img);
        frameCounts.put(name, frames);
    }

    public void play(String name) {
        if (!name.equals(currentAnimation)) {
            currentAnimation = name;
            spriteView.setImage(animationSheets.get(name));
            frameIndex = 0;
            frameTime = 0;
            playOnce = false;
            spriteView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        }
    }

    public void playOnce(String name, double duration) {
        currentAnimation = name;
        spriteView.setImage(animationSheets.get(name));
        frameIndex = 0;
        frameTime = 0;
        playOnce = true;
        durationLeft = duration;
        int frames = frameCounts.getOrDefault(name, 1);
        frameDuration = duration / frames;
        spriteView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
    }

    public boolean isAnimationDone() {
        return playOnce && durationLeft <= 0;
    }

    public void update(double deltaTime, double fps) {
    	currentFrame = frameIndex; // or whatever your frame index variable is
        if (playOnce) {
            durationLeft -= deltaTime;
            frameTime += deltaTime;
            if (frameTime >= frameDuration) {
                frameIndex++;
                frameTime = 0;
                int frames = frameCounts.getOrDefault(currentAnimation, 1);
                if (frameIndex >= frames) frameIndex = frames - 1;
                double offsetX = frameIndex * frameWidth;
                spriteView.setViewport(new Rectangle2D(offsetX, 0, frameWidth, frameHeight));
            }
        } else {
            frameTime += deltaTime;
            double frameDuration = 1.0 / fps;
            if (frameTime >= frameDuration) {
                int totalFrames = frameCounts.getOrDefault(currentAnimation, 1);
                frameIndex = (frameIndex + 1) % totalFrames;
                double offsetX = frameIndex * frameWidth;
                spriteView.setViewport(new Rectangle2D(offsetX, 0, frameWidth, frameHeight));
                frameTime = 0;
            }
        }
    }

    public void flip(boolean faceRight) {
        if (facingRight != faceRight) {
            facingRight = faceRight;
            spriteView.setScaleX(faceRight ? 1 : -1);
        }
    }

    public void setPosition(double x, double y) {
        spriteView.setLayoutX(x);
        spriteView.setLayoutY(y);
    }

    public ImageView getView() {
        return spriteView;
    }
    
    public int getCurrentFrameIndex() {
        return currentFrame;
    }

}