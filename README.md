# Quiz-Game (Brahim)

This is a simple console-based game where players answer questions and roll dice to progress.
The game requires a minimum of two players and involves a turn-based system where players can be sent to a penalty box based if their answer is wrong. User will be prompted, but auto-play can be enabled to let the game flow be played by itself.

<img src="https://raw.githubusercontent.com/brahimelo/quiz-game/refs/heads/main/assets/game_screenshot.png" alt="Play Image" width="500">

## Table of Contents

- [Installation](#quick-start)
- [Usage](#usage)
- [Game Structure](#game-structure)
- [For the future](#for-the-future)

## Quick Start

This is a quick-start scenario where you can test the game

```bash
  Game game = new Game();

  game.addPlayer("Player 1");
  game.addPlayer("Player 2");

  game.start();

  while (game.isNotFinished()) {
      game.play();
  }
```

## Usage

1. Start the game by adding some players.
2. Players take turns rolling a dice and by answering questions.
3. Players earn gold from good answers, but they go to penalty box if their answer is bad.
4. The game ends when a player collects a certain amount of gold coins.
5. User will be prompted, but auto-play can be enabled to let the game flow be played by itself.


## Game Structure

The game package includes several important classes:

- **Game**: This is the main class that controls the flow of the game, manages player turns, asks the questions and keeps track of the overall state of the game.
- **Player**: This class represents each player in the game, tracking their name, score, and current status (like if they’re in the penalty box).
- **PlayerManager**: This class manages the players with their turns.
- **Question**: This class defines what a question is, including its category and the correct answer.
- **QuestionManager**: This class handles the questions deck, ensuring they are provided based on the current player's category.
- **Category**: This is an enumeration that defines the different types of questions available in the game.


## For the future

The implementation of the game is far from being perfect, here are some points that will be implemented in the future

- **Pattern State**: Implementing the State Design Pattern can help manage the various states of the game (e.g., initializing, in progress, finished). This will simplify the control flow and make it easier to manage state-specific behaviors.
- **Notifier or Logger Class**: Introducing a Notifier or Logger class can significantly improve the readability of the code. This class would centralize logging activities, allowing for a more systematic approach to logging game events, errors, and player actions.
- **Javadoc**: Comprehensive Javadoc comments should be added to all public classes and methods. This will improve the documentation quality, making it easier for other developers to understand the functionality of the code.
- **Improved Testing for `roll` Method**: The current `roll` method can be challenging to test effectively due to its reliance on randomization and external factors. To enhance its testability, consider using dependency injection. This approach will allow for more precise and controlled unit tests, leading to better reliability and robustness of the game logic.

