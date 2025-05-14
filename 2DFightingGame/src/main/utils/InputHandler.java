public class InputHandler {
    private boolean[] keys;
    private boolean up, down, left, right, attack1, attack2, roll, slide;

    public InputHandler() {
        keys = new boolean[256]; // Assuming 256 keys for simplicity
    }

    public void keyPressed(int keyCode) {
        if (keyCode < keys.length) {
            keys[keyCode] = true;
        }
    }

    public void keyReleased(int keyCode) {
        if (keyCode < keys.length) {
            keys[keyCode] = false;
        }
    }

    public void update() {
        up = keys[87]; // W key
        down = keys[83]; // S key
        left = keys[65]; // A key
        right = keys[68]; // D key
        attack1 = keys[82]; // R key
        attack2 = keys[76]; // L key
        roll = keys[81]; // Q key
        slide = keys[16]; // Shift key
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isAttack1() {
        return attack1;
    }

    public boolean isAttack2() {
        return attack2;
    }

    public boolean isRoll() {
        return roll;
    }

    public boolean isSlide() {
        return slide;
    }
}