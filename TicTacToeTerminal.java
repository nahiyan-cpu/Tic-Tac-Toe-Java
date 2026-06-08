import java.util.Scanner;
import java.util.Random;

public class TicTacToeTerminal {

    // Global variables accessible by all methods (Functions)
    static Scanner scanner = new Scanner(System.in);
    static String[] gameHistory = new String[50]; // Array to store history of up to 50 games
    static int gameCount = 0;

    public static void main(String[] args) {
        // --- Login System ---
        System.out.println("--- Welcome to Tic-Tac-Toe ---");
        boolean isLoggedIn = false;
        do {
            System.out.print("Enter username: ");
            String username = scanner.next();
            System.out.print("Enter password: ");
            String password = scanner.next();

            // Simple login check using conditional statements
            if (username.equals("user") && password.equals("pass123")) {
                System.out.println("Login successful!");
                isLoggedIn = true;
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        } while (!isLoggedIn);

        // --- Main Menu ---
        // Loop to show menu until user logs out
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Player vs. Player");
            System.out.println("2. Player vs. Computer");
            System.out.println("3. Show Game History");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = getIntInput();

            // Switch statement to handle menu options
            switch (choice) {
                case 1:
                    playGame(false, ""); // Player vs Player
                    break;
                case 2:
                    selectDifficulty(); // Player vs Computer
                    break;
                case 3:
                    showHistory();
                    break;
                case 4:
                    System.out.println("Logging out. Goodbye!");
                    return; // Exit the main method, ending the program
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    // --- Difficulty Selection for PvC ---
    public static void selectDifficulty() {
        while (true) {
            System.out.println("\n--- Select Difficulty ---");
            System.out.println("1. Easy");
            System.out.println("2. Medium");
            System.out.println("3. Hard (Unbeatable)");
            System.out.print("Choose a difficulty: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    playGame(true, "EASY");
                    return;
                case 2:
                    playGame(true, "MEDIUM");
                    return;
                case 3:
                    playGame(true, "HARD");
                    return;
                default:
                    System.out.println("Invalid difficulty. Please choose again.");
            }
        }
    }

    // --- Main Game Logic ---
    public static void playGame(boolean isVsComputer, String difficulty) {
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        char currentPlayer = 'X';
        boolean gameEnded = false;

        System.out.println("\n--- Game Start! ---");
        printBoard(board);

        // Game loop
        while (!gameEnded) {
            if (currentPlayer == 'X' || !isVsComputer) {
                playerMove(board, currentPlayer);
            } else { // Computer's turn
                computerMove(board, difficulty);
                System.out.println("Computer (" + difficulty + ") moved.");
            }

            printBoard(board);

            // Check for winner or draw
            if (checkWinner(board, currentPlayer)) {
                System.out.println("Player " + currentPlayer + " wins!");
                addHistory("Player " + currentPlayer + " won.");
                gameEnded = true;
            } else if (isBoardFull(board)) {
                System.out.println("The game is a draw!");
                addHistory("Game was a draw.");
                gameEnded = true;
            }

            // Switch players
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    // --- Board and Moves ---
    public static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static void playerMove(char[][] board, char player) {
        int row, col;
        while (true) {
            System.out.print("Player " + player + ", enter your move (row and column, e.g., 1 1): ");
            row = getIntInput() - 1;
            col = getIntInput() - 1;

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                board[row][col] = player;
                break;
            } else {
                System.out.println("This move is not valid. Try again.");
            }
        }
    }

    public static void computerMove(char[][] board, String difficulty) {
        switch (difficulty) {
            case "EASY":
                easyMove(board);
                break;
            case "MEDIUM":
                mediumMove(board);
                break;
            case "HARD":
                hardMove(board);
                break;
        }
    }

    // --- Computer AI Logic ---
    public static void easyMove(char[][] board) {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');
        board[row][col] = 'O';
    }

    public static void mediumMove(char[][] board) {
        // 1. Check if Computer can win in the next move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    if (checkWinner(board, 'O')) {
                        return; // Found a winning move
                    }
                    board[i][j] = ' '; // Undo move
                }
            }
        }

        // 2. Check if Player can win, and block them
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'X';
                    if (checkWinner(board, 'X')) {
                        board[i][j] = 'O'; // Block the player
                        return;
                    }
                    board[i][j] = ' '; // Undo move
                }
            }
        }

        // 3. Otherwise, make a random move (like easy)
        easyMove(board);
    }

    public static void hardMove(char[][] board) {
        int bestScore = -1000;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O'; // Try the move
                    int score = minimax(board, 0, false);
                    board[i][j] = ' '; // Undo the move
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        board[bestMove[0]][bestMove[1]] = 'O';
    }

    // Minimax algorithm for unbeatable AI
    public static int minimax(char[][] board, int depth, boolean isMaximizing) {
        if (checkWinner(board, 'O')) return 10;
        if (checkWinner(board, 'X')) return -10;
        if (isBoardFull(board)) return 0;

        if (isMaximizing) { // Computer's turn (wants to maximize score)
            int bestScore = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        bestScore = Math.max(bestScore, minimax(board, depth + 1, false));
                        board[i][j] = ' ';
                    }
                }
            }
            return bestScore;
        } else { // Player's turn (wants to minimize score)
            int bestScore = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        bestScore = Math.min(bestScore, minimax(board, depth + 1, true));
                        board[i][j] = ' ';
                    }
                }
            }
            return bestScore;
        }
    }


    // --- Game Status Checkers ---
    public static boolean checkWinner(char[][] board, char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Found an empty space
                }
            }
        }
        return true; // No empty spaces
    }

    // --- History and Utility ---
    public static void addHistory(String result) {
        if (gameCount < gameHistory.length) {
            gameHistory[gameCount] = "Game " + (gameCount + 1) + ": " + result;
            gameCount++;
        }
    }

    public static void showHistory() {
        System.out.println("\n--- Game History ---");
        if (gameCount == 0) {
            System.out.println("No games played yet.");
        } else {
            for (int i = 0; i < gameCount; i++) {
                System.out.println(gameHistory[i]);
            }
        }
    }
    
    // Utility method for safe integer input
    public static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a valid number. Please enter an integer.");
            scanner.next(); // discard non-integer input
        }
        return scanner.nextInt();
    }
}