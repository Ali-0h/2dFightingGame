public class GameOver {

    private boolean isGameOver;
    private int player1Score;
    private int player2Score;

    public GameOver() {
        this.isGameOver = false;
        this.player1Score = 0;
        this.player2Score = 0;
    }

    public void displayGameOverScreen() {
        // Logic to display the game over screen
        System.out.println("Game Over!");
        System.out.println("Player 1 Score: " + player1Score);
        System.out.println("Player 2 Score: " + player2Score);
        System.out.println("Press R to Restart or M to return to Main Menu.");
    }

    public void updateScores(int score1, int score2) {
        this.player1Score = score1;
        this.player2Score = score2;
    }

    public void setGameOver(boolean gameOver) {
        this.isGameOver = gameOver;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void restartGame() {
        // Logic to restart the game
        this.isGameOver = false;
        this.player1Score = 0;
        this.player2Score = 0;
        // Additional reset logic if needed
    }
}