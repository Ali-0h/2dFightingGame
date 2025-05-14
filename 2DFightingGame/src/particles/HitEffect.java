public class HitEffect extends ParticleEffect {

    private int duration; // Duration of the hit effect
    private int frameCount; // Number of frames for the hit effect
    private int currentFrame; // Current frame being displayed

    public HitEffect(int duration) {
        this.duration = duration;
        this.frameCount = 4; // Assuming there are 4 frames for the hit effect
        this.currentFrame = 0;
    }

    public void update() {
        if (currentFrame < frameCount) {
            // Update the current frame based on the duration
            currentFrame++;
        }
    }

    public void render(Graphics g, int x, int y) {
        if (currentFrame < frameCount) {
            // Render the current frame of the hit effect at the specified position
            g.drawImage(getFrame(currentFrame), x, y, null);
        }
    }

    private BufferedImage getFrame(int frame) {
        // Logic to retrieve the appropriate frame image from the resources
        return ParticleEffect.getImage("HitEffectFrame" + frame);
    }

    public boolean isFinished() {
        return currentFrame >= frameCount;
    }
}