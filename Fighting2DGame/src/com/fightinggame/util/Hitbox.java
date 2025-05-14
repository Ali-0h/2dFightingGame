package com.fightinggame.util;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Hitbox {
    private Rectangle rect;
    private double damage;
    private boolean active;
    private boolean visible;
    private boolean hasHit;

    public Hitbox(int width, int height, double dmg) {
        rect = new Rectangle(width, height);
        rect.setFill(Color.RED.deriveColor(1, 1, 1, 0.3));
        rect.setVisible(false);
        damage = dmg;
        active = false;
        hasHit = false;
    }

    public void show() {
        visible = true;
        rect.setVisible(true);
    }

    public void hide() {
        visible = false;
        rect.setVisible(false);
    }

    public void trigger(double x, double y) {
        rect.setX(x);
        rect.setY(y);
        rect.setVisible(visible);
        active = true;
        hasHit = false;
    }

    public void disable() {
        active = false;
        rect.setVisible(false);
    }

    public boolean canHit() {
        return active && !hasHit;
    }

    public void markHit() {
        hasHit = true;
    }

    public Rectangle getNode() {
        return rect;
    }

    public double getDamage(boolean isCrit) {
        return isCrit ? damage * 1.3 : damage;
    }

    public boolean isActive() {
        return active;
    }
}
