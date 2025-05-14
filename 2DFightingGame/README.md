# 2D Fighting Game

## Overview
This project is a 2-player 2D fighting game inspired by popular titles like "Brawlhalla." The game features various maps, character animations, and combat mechanics, allowing players to engage in competitive matches. The game is developed using Java and Eclipse, utilizing a structured approach to manage game states, player interactions, and visual effects.

## Game Features
- **2-Player Gameplay**: Players can compete against each other in a series of rounds.
- **Multiple Maps**: Choose from three distinct maps, each with unique layouts and aesthetics.
- **Combat Mechanics**: Players can perform various attacks, combos, and special moves.
- **Round System**: The first player to win three rounds is declared the winner. If both players reach three wins simultaneously, the match ends in a draw.
- **Time Limit**: Each round has a maximum duration of 1.5 minutes.
- **Dynamic UI**: The game features a user-friendly interface displaying health, scores, and round information.

## Game Controls
### Player 1
- **Movement**: W, A, S, D
- **Attack**: R
- **Slide**: Shift
- **Roll**: Q

### Player 2
- **Movement**: Arrow Keys
- **Attack**: L
- **Slide**: J
- **Roll**: U

### Miscellaneous
- **Toggle Hitboxes**: F1

## Project Structure
The project is organized into several packages and directories:
- **src/main**: Contains the main game logic and state management.
- **src/entities**: Defines player and enemy behaviors.
- **src/maps**: Manages map loading and rendering.
- **src/particles**: Handles particle effects for various actions.
- **res**: Contains all game resources, including sprites, sounds, and maps.

## Development Milestones
1. **Project Architecture & Game Skeleton**: Set up the project structure and core classes.
2. **Movement & Core Player Mechanics**: Implement basic player movements and animations.
3. **Combat Mechanics**: Develop attack sequences and hitbox logic.
4. **Maps, Camera, and Particles**: Load maps and implement camera logic.
5. **Game Flow & Rules System**: Establish round-based game logic and transitions.
6. **UI & UX Polish**: Create menus and enhance user experience.
7. **Optimization & QA**: Profile performance and clean up code.

## License
This project is open-source and can be modified and distributed under the terms specified in the project's license agreement. 

## Acknowledgments
Special thanks to the developers and artists whose resources and inspiration contributed to the creation of this game.