public class Pause {
    private boolean isPaused;

    public Pause() {
        this.isPaused = false;
    }

    public void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            displayPauseMenu();
        } else {
            resumeGame();
        }
    }

    private void displayPauseMenu() {
        // Code to display the pause menu options (Resume, Quit)
        System.out.println("Game Paused. Press 'R' to Resume or 'Q' to Quit.");
    }

    private void resumeGame() {
        // Code to resume the game
        System.out.println("Game Resumed.");
    }

    public boolean isPaused() {
        return isPaused;
    }
}