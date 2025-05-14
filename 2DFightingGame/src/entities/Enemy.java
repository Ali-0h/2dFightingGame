public class Enemy {
    private int health;
    private int damage;
    private int x, y; // Position of the enemy
    private int speed; // Movement speed
    private boolean isAlive;

    public Enemy(int health, int damage, int speed, int startX, int startY) {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.x = startX;
        this.y = startY;
        this.isAlive = true;
    }

    public void move(int playerX, int playerY) {
        if (isAlive) {
            // Simple AI to move towards the player
            if (playerX > x) {
                x += speed;
            } else if (playerX < x) {
                x -= speed;
            }

            if (playerY > y) {
                y += speed;
            } else if (playerY < y) {
                y -= speed;
            }
        }
    }

    public void attack(Player player) {
        if (isAlive && isInRange(player)) {
            player.takeDamage(damage);
        }
    }

    private boolean isInRange(Player player) {
        // Check if the player is within attack range
        return Math.abs(player.getX() - x) < 50 && Math.abs(player.getY() - y) < 50;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            isAlive = false;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}