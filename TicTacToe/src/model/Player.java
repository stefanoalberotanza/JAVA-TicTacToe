package model;

import java.util.Scanner;

public class Player {
    public boolean ai;

    public char sign;

    public Player(char sign) {
        this.sign = sign;
    }


    public int[] makeMove(Scanner scanner, char[][] grid){
        return choose(grid, scanner);
    }


    public int[] choose(char[][] grid, Scanner scanner){

        boolean taken = false;
        int[] rowCol = new int[2];
        while(!taken) {
            try{

                if (!ai) {
                    // Prompt the current player to make a move
                    System.out.println("Player " + sign + ", enter your move (row, col): ");
                    rowCol[0] = scanner.nextInt();
                    rowCol[1] = scanner.nextInt();
                } else {

                    // Random choice AI
                    System.out.print("AI is thinking about next move..\n");
                    rowCol[0] = (int) (Math.random() * 3);
                    rowCol[1] = (int) (Math.random() * 3);
                }

                //Check if is a valid move
                if (grid[rowCol[0]][rowCol[1]] == ' ') {
                    taken = true;
                } else {

                    //If player is human, take another move
                    if (!ai) {
                        System.out.print("You need to select another cell!\n");
                        Board.printGrid();
                    }
                }
            }catch (Exception e){
                System.out.println("Invalid selection, please try again.");
                Board.printGrid();
            }
        }

        return rowCol;
    }

    @Override
    public String toString(){
        return String.valueOf(sign);
    }
}
