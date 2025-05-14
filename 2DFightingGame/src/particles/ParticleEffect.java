public class ParticleEffect {
    private float x, y; // Position of the particle effect
    private float lifespan; // Lifespan of the particle effect
    private float currentTime; // Current time of the particle effect
    private boolean isActive; // Status of the particle effect

    public ParticleEffect(float x, float y, float lifespan) {
        this.x = x;
        this.y = y;
        this.lifespan = lifespan;
        this.currentTime = 0;
        this.isActive = true;
    }

    public void update(float deltaTime) {
        if (isActive) {
            currentTime += deltaTime;
            if (currentTime >= lifespan) {
                isActive = false; // Deactivate the particle effect after its lifespan
            }
        }
    }

    public void render(Graphics g) {
        if (isActive) {
            // Render the particle effect at its position
            // This is a placeholder for actual rendering logic
            g.drawImage(getParticleImage(), (int) x, (int) y, null);
        }
    }

    private Image getParticleImage() {
        // Placeholder for getting the appropriate particle image
        return null; // Replace with actual image retrieval logic
    }

    public boolean isActive() {
        return isActive;
    }
}