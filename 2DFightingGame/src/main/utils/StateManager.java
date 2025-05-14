public class StateManager {
    private enum GameState {
        MAIN_MENU,
        GAMEPLAY,
        PAUSE,
        GAME_OVER
    }

    private GameState currentState;

    public StateManager() {
        currentState = GameState.MAIN_MENU; // Start with the main menu
    }

    public void setState(GameState newState) {
        currentState = newState;
        onStateChange();
    }

    public GameState getCurrentState() {
        return currentState;
    }

    private void onStateChange() {
        switch (currentState) {
            case MAIN_MENU:
                // Initialize main menu
                break;
            case GAMEPLAY:
                // Start gameplay
                break;
            case PAUSE:
                // Pause the game
                break;
            case GAME_OVER:
                // Handle game over state
                break;
        }
    }
}