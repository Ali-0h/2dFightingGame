public class DashEffect extends ParticleEffect {
    private static final int FRAME_COUNT = 10; // Number of frames for the dash effect
    private int currentFrame;
    private long lastFrameTime;

    public DashEffect() {
        super();
        this.currentFrame = 0;
        this.lastFrameTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= 100) { // Update every 100 milliseconds
            currentFrame++;
            if (currentFrame >= FRAME_COUNT) {
                currentFrame = 0; // Reset to the first frame after reaching the last
            }
            lastFrameTime = currentTime;
        }
    }

    @Override
    public void render(Graphics g, int x, int y) {
        // Render the current frame of the dash effect at the specified position
        g.drawImage(getFrame(currentFrame), x, y, null);
    }

    private Image getFrame(int frame) {
        // Load and return the image for the specified frame
        return loadImage("res/ParticleEffect/DashEffect/FX033_0" + (frame + 1) + ".png");
    }
}