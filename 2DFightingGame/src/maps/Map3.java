public class Map3 extends GameMap {
    
    public Map3() {
        super("Map3", "res/Map3/Map3Tiled.tmj", "res/Map3/Map3Tiled.png", "res/Map3/Map3PlatformAndSpawnPoint.tmj");
    }

    @Override
    public void loadMap() {
        // Load the map layout and properties specific to Map3
        super.loadMap();
        // Additional setup for Map3 can be added here
    }

    @Override
    public void render() {
        // Render the map and any specific elements for Map3
        super.render();
        // Additional rendering logic for Map3 can be added here
    }
}