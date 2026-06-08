# 🎮 Tic-Tac-Toe — Console Edition

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Platform](https://img.shields.io/badge/Platform-Console%2FTerminal-black?style=for-the-badge&logo=windowsterminal&logoColor=white)
![Type](https://img.shields.io/badge/Type-CLI%20Game-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen?style=for-the-badge)

**A fully-functional, terminal-based Tic-Tac-Toe game written in pure Java.**  
Features a login system, 3 AI difficulty levels (including an unbeatable Minimax AI), game history tracking, and both PvP & PvC modes.

</div>

---

## ✨ Features

| Feature | Details |
|---|---|
| 🔐 **Login System** | Username & password authentication before game access |
| 👥 **Player vs Player** | Two humans can play on the same machine |
| 🤖 **Player vs Computer** | AI opponent with 3 difficulty settings |
| 😊 **Easy AI** | Plays completely random valid moves |
| 🤔 **Medium AI** | Wins if possible, otherwise blocks, otherwise random |
| 😈 **Hard AI** | Fully unbeatable using the **Minimax algorithm** |
| 📜 **Game History** | Stores and displays results of up to 50 past games |
| 🔄 **Main Menu Loop** | Navigate between modes and history without restarting |

---

## 📸 Screenshots

<img width="340" height="285" alt="Terminal png (2)" src="https://github.com/user-attachments/assets/6a384c8b-a235-4b3e-91ce-aec47abca739" />
<img width="301" height="181" alt="Terminal2 png" src="https://github.com/user-attachments/assets/0bd452e0-ad1e-482c-b536-3c5e2540a92e" />
<img width="597" height="326" alt="Terminal3 png" src="https://github.com/user-attachments/assets/7603889d-3137-439f-9c2e-3b942e8b4e54" />
<img width="241" height="165" alt="Terminal4 png" src="https://github.com/user-attachments/assets/bd820987-a069-4289-8369-513f5d8c5b59" />
<img width="408" height="196" alt="Terminal5 png" src="https://github.com/user-attachments/assets/f540d9f3-cdc5-4a56-976c-183b1ea91e56" />
<img width="308" height="334" alt="Terminal6 png" src="https://github.com/user-attachments/assets/e90dc8f4-b3e7-429c-972e-f9e02152ac0d" />


### 🔐 Login Screen
```
--- Welcome to Tic-Tac-Toe ---
Enter username: user
Enter password: pass123
Login successful!
```

### 📋 Main Menu
```
--- Main Menu ---
1. Player vs. Player
2. Player vs. Computer
3. Show Game History
4. Logout
Choose an option:
```

### 🎯 Gameplay
```
-------------
| X |   |   |
-------------
|   | O |   |
-------------
|   |   | X |
-------------
Player O, enter your move (row and column, e.g., 1 1):
```

### 🏆 Win Screen
```
Player X wins!
```

### 📜 Game History
```
--- Game History ---
Game 1: Player X won.
Game 2: Game was a draw.
Game 3: Player O won.
```

> 💡 **Tip:** Take screenshots of your actual terminal and replace the code blocks above with real images using:
> ```markdown
> ![Screenshot](screenshots/login.png)
> ```

---

## 🚀 Getting Started

### Prerequisites

- Java **JDK 8** or higher

### 🔧 Compile

```bash
javac TicTacToe.java
```

### ▶️ Run

```bash
java TicTacToe
```

---

## 🔐 Login Credentials

| Field | Value |
|---|---|
| **Username** | `user` |
| **Password** | `pass123` |

> To change credentials, edit the `if` condition inside the `main()` method in `TicTacToe.java`.

---

## 🎮 How to Play

1. Run the program and **log in** with the credentials above
2. From the **Main Menu**, choose a game mode
3. For **PvP** — both players take turns entering moves
4. For **PvC** — choose Easy, Medium, or Hard difficulty
5. Enter moves as `row column` (e.g., `1 1` for top-left, `2 2` for center)
6. First to get **3 in a row** (horizontal, vertical, or diagonal) wins!
7. View past results anytime from the **Game History** option

### 🗺️ Board Position Reference

```
 (1,1) | (1,2) | (1,3)
-------+-------+-------
 (2,1) | (2,2) | (2,3)
-------+-------+-------
 (3,1) | (3,2) | (3,3)
```

---

## 🤖 AI Difficulty Explained

### 😊 Easy
Picks a random empty cell every time. Great for beginners.

### 🤔 Medium
Uses a simple strategy:
1. **Win** immediately if possible
2. **Block** the player from winning
3. Otherwise, play randomly

### 😈 Hard — Unbeatable
Uses the **Minimax Algorithm** — it evaluates every possible future game state and always picks the optimal move. You **cannot** beat this AI; the best result is a draw.

```
Score:  O wins → +10
        X wins → -10
        Draw   →   0
```

---

## 🗂️ Code Structure

```
TicTacToe.java
│
├── main()              # Login system + main menu loop
├── selectDifficulty()  # Difficulty picker for PvC mode
├── playGame()          # Core game loop logic
│
├── printBoard()        # Renders the 3×3 board to console
├── playerMove()        # Handles & validates human input
├── computerMove()      # Routes to correct AI method
│
├── easyMove()          # Random AI
├── mediumMove()        # Win/Block/Random AI
├── hardMove()          # Minimax-based best move finder
├── minimax()           # Recursive Minimax algorithm
│
├── checkWinner()       # Checks rows, columns, diagonals
├── isBoardFull()       # Draw condition checker
│
├── addHistory()        # Appends result to history array
├── showHistory()       # Prints all stored game results
└── getIntInput()       # Safe integer input with validation
```

---

## 📦 Data Storage

Game history is stored **in-memory** using a fixed-size array (`String[50]`) during the session. History is cleared when the program exits.

```java
static String[] gameHistory = new String[50];
static int gameCount = 0;
```

> To add persistent storage, the `addHistory()` and `showHistory()` methods can be extended with file I/O.

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| **Java (JDK 8+)** | Core language |
| **Scanner** | Console input handling |
| **Random** | Easy AI random move generation |
| **Minimax Algorithm** | Hard AI decision engine |
| **Arrays** | In-memory game history storage |

---

## 👤 Author

**Syed Nahiyan**

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

<div align="center">
  <i>Built with ☕ Java — no libraries, no frameworks, just pure logic.</i>
</div>
