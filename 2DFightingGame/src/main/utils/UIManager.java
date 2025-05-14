public class UIManager {
    private int player1Health;
    private int player2Health;
    private int player1Score;
    private int player2Score;

    public UIManager() {
        player1Health = 100; // Initial health for Player 1
        player2Health = 100; // Initial health for Player 2
        player1Score = 0;    // Initial score for Player 1
        player2Score = 0;    // Initial score for Player 2
    }

    public void updateHealth(int player, int health) {
        if (player == 1) {
            player1Health = health;
        } else if (player == 2) {
            player2Health = health;
        }
    }

    public void updateScore(int player, int score) {
        if (player == 1) {
            player1Score += score;
        } else if (player == 2) {
            player2Score += score;
        }
    }

    public void render() {
        // Code to render health bars and scores on the screen
        // This will involve drawing the health and score values
        // using the graphics context of the game
    }

    public int getPlayer1Health() {
        return player1Health;
    }

    public int getPlayer2Health() {
        return player2Health;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }
}