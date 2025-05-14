public class Map2 {
    // Properties for the map layout and resources
    private String mapFile;
    private String tileSetFile;
    private String platformSpawnPointFile;

    public Map2() {
        // Initialize the map with its resources
        this.mapFile = "res/Map2/Map2Tiled.tmj";
        this.tileSetFile = "res/Map2/Map2Tiled.png";
        this.platformSpawnPointFile = "res/Map2/Map2PlatformAndSpawnPoint.tmj";
    }

    public void loadMap() {
        // Logic to load the map using the specified files
        // This would typically involve reading the map file and setting up the layout
    }

    public void render() {
        // Logic to render the map on the screen
        // This would involve drawing the tiles and platforms based on the loaded map data
    }

    // Additional methods for map interactions, collision detection, etc.
}