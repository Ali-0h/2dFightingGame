public class Map1 {
    private String mapName;
    private int width;
    private int height;
    private String tileSetPath;

    public Map1() {
        this.mapName = "Map1";
        this.width = 800; // Example width
        this.height = 600; // Example height
        this.tileSetPath = "res/Map1/Map1Tiled.png"; // Path to the tile set image
    }

    public void loadMap() {
        // Logic to load the map using the tile set and dimensions
    }

    public void render() {
        // Logic to render the map on the screen
    }

    public String getMapName() {
        return mapName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTileSetPath() {
        return tileSetPath;
    }
}