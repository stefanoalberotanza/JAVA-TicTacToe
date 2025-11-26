
import model.Board;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static model.Board.*;

public class TicTacToe {


    // The players, represented as X and O
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private static List<Player> players;

    private static final Scanner scanner = new Scanner(System.in);

    // The current player

    public static void main(String[] args) {
        // Initialize the grid with empty spaces
        setup();

        // Start the game loop
        game();
    }

    //Engine to handle game rounds
    private static void game(){

        int i = 0;
            while (true) {
                // Print the grid
                Board.printGrid();

                // Prompt the current player to make a move
                Player currentPlayer = players.get(i);

                int[] rowCol = currentPlayer.makeMove(scanner, Board.getGrid());
                // Update the grid with the player's move
                Board.update(currentPlayer, rowCol);

                // Check if the game is over
                if (isGameOver()) {
                    // Print the final grid
                    Board.printGrid();

                    // Print the winner (if any)
                    if (hasWinner()) {
                        System.out.println("Player " + currentPlayer + " wins!");
                    } else {
                        System.out.println("It's a tie!");
                    }

                    // End the game loop
                    break;
                }

            // Switch to the other player
                i= (i+1)%2;
            }

        //Asking for new game
        newGame();
    }

    private static void newGame() {
        System.out.print("""
        
        Do you want play again?
            
            1 - Yes
            2 - No 
            
        """);

        if(scanner.nextInt()==1){
            setup();
            game();
        }else {
            System.out.print("Bye, thanks for playing!");
        }
    }

    //setup game
    public static void setup() {
        Board.clear();
        players = new ArrayList<>();

        System.out.println("What game do u want play?\n" +
                "1 - Player vs AI\n" +
                "2 - Player vs Player\n" +
                "3 - AI vs AI \n");

        Player playerX = new Player(PLAYER_X);
        Player playerO = new Player(PLAYER_O);

        int mode = scanner.nextInt();
        if(mode==1) {
            System.out.println("What game do u want play?\n" +
                    "1 - first as X\n" +
                    "2 - second as O\n" +
                    "3 - random ");

            int order = scanner.nextInt();
            if(order==1) {
                playerX.ai = false;
                playerO.ai = true;
            }else if(order==2){
                playerX.ai = true;
                playerO.ai = false;

            }else{
                Random random = new Random();
                boolean ai = random.nextInt(1) > 0.5;
                playerX.ai = ai;
                playerO.ai = !ai;
            }

            players.add(playerX);
            players.add(playerO);
        }else if(mode==2) {
            playerX.ai = false;
            playerO.ai = false;
            players.add(playerX);
            players.add(playerO);
        }else {
            playerX.ai = true;
            playerO.ai = true;
            players.add(playerX);
            players.add(playerO);
        }
    }

    // Check if the game is over (i.e. someone has won or there are no more empty spaces)
    private static boolean isGameOver() {
        return hasWinner() || isFull();
    }

    // Check if there is a winner (i.e. someone has three marks in a row)
    private static boolean hasWinner() {
        // Check for horizontal wins
        for (int i = 0; i < 3; i++) {
            if (isRowWin(i)) {
                return true;
            }
        }

        // Check for vertical wins
        for (int i = 0; i < 3; i++) {
            if (isColWin(i)) {
                return true;
            }
        }

        // Check for diagonal wins
        if (isDiag1Win() || isDiag2Win()) {
            return true;
        }

        // If none of the above checks passed, there is no winner
        return false;
    }


}
