public class Camera {
    private float x, y; // Camera position
    private float zoom; // Camera zoom level
    private float shakeIntensity; // Intensity of camera shake
    private float shakeDuration; // Duration of camera shake
    private float shakeTimer; // Timer for shake duration

    public Camera(float startX, float startY) {
        this.x = startX;
        this.y = startY;
        this.zoom = 1.0f;
        this.shakeIntensity = 0.0f;
        this.shakeDuration = 0.0f;
        this.shakeTimer = 0.0f;
    }

    public void update() {
        // Update camera shake
        if (shakeTimer > 0) {
            shakeTimer -= 1; // Decrease shake timer
            if (shakeTimer <= 0) {
                shakeIntensity = 0; // Reset shake intensity
            }
        }
    }

    public void shake(float intensity, float duration) {
        this.shakeIntensity = intensity;
        this.shakeDuration = duration;
        this.shakeTimer = duration; // Reset shake timer
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
    }

    public float getX() {
        return x + (float) (Math.random() * shakeIntensity * 2 - shakeIntensity); // Add shake effect
    }

    public float getY() {
        return y + (float) (Math.random() * shakeIntensity * 2 - shakeIntensity); // Add shake effect
    }

    public float getZoom() {
        return zoom;
    }
}