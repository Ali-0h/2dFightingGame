public class CritEffect extends ParticleEffect {
    private static final int FRAME_COUNT = 3; // Number of frames for the critical hit effect
    private int currentFrame;
    private long lastFrameTime;
    private static final long FRAME_DURATION = 100; // Duration for each frame in milliseconds

    public CritEffect(float x, float y) {
        super(x, y);
        this.currentFrame = 0;
        this.lastFrameTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= FRAME_DURATION) {
            currentFrame++;
            lastFrameTime = currentTime;

            if (currentFrame >= FRAME_COUNT) {
                // Effect finished, can be removed or reset
                setActive(false);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (isActive()) {
            // Render the current frame of the critical hit effect
            g.drawImage(getFrame(currentFrame), getX(), getY(), null);
        }
    }

    private Image getFrame(int frame) {
        // Load and return the image for the specified frame
        return loadImage("res/ParticleEffect/CritEffect/FX036_0" + (frame + 1) + ".png");
    }
}