package src.maps;

import java.awt.Graphics;

public class GameMap {
    private String mapName;

    public GameMap(String mapName) {
        this.mapName = mapName;
    }

    public void render(Graphics g) {
        // Placeholder for rendering the map
        g.setColor(java.awt.Color.LIGHT_GRAY);
        g.fillRect(0, 0, 800, 600); // Draw a simple background
    }
}