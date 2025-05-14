package src.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
    private String name;
    private int x, y; // Player's position
    private int width, height; // Player's dimensions
    private Rectangle hitbox; // Player's hitbox
    private Image idleSprite; // Idle sprite

    public Player(String name, int startX, int startY) {
        this.name = name;
        this.x = startX;
        this.y = startY;
        this.width = 120; // Default width (based on sprite dimensions)
        this.height = 80; // Default height (based on sprite dimensions)
        this.hitbox = new Rectangle(x, y, width, height);

        // Load the idle sprite
        try {
            idleSprite = ImageIO.read(new File("res/" + name + "/_Idle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
        updateHitbox();
    }

    public void render(Graphics g) {
        // Draw the idle sprite
        if (idleSprite != null) {
            g.drawImage(idleSprite, x, y, width, height, null);
        } else {
            // Fallback: Draw a rectangle if the sprite is missing
            g.drawRect(x, y, width, height);
        }
    }

    public void tick() {
        // Placeholder for updating player logic
    }

    private void updateHitbox() {
        this.hitbox.setBounds(x, y, width, height);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}