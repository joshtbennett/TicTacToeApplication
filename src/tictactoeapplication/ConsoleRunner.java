/**
 * ConsoleRunner:  Prompts the user to determine the parameters of the Game
 * constructor.  Creates a Game and manages the alternating calls to the
 * place methods in Game.  Prompts the user for inputs and outputs the state
 * of the board to the console.
 *
 * @author Joshua Bennett
 */

package tictactoeapplication;

import java.util.Objects;
import java.util.Scanner;

public class ConsoleRunner 
{
    // Use to process text input from the user.
    private final Scanner scanner = new Scanner(System.in);
    
    private final boolean playerIsX;
    private final boolean challenge;
    private int i, j, whosMove, counter;
    private GameStatus status;
    private Game game;
    
    /**
     * Constructor
     */
    public ConsoleRunner() 
    {    
        String splayer, sdifficulty;
        //print user prompts and set player piece and ai difficulty
        System.out.print("Do you want to play as X (y/n):\n");
        splayer = scanner.next();
        System.out.print("Do you want a challange (y/n): \n");
        sdifficulty = scanner.next();
        playerIsX = Objects.equals(splayer, "y");
        challenge = Objects.equals(sdifficulty, "y");
    }

    /**
     * Enter the main control loop which returns only at the end of the game
     * when one party has won or there has been a draw.
     */
    public void mainLoop() 
    {    	
        whosMove = playerIsX? 1:0;

        //start new game based on difficulty and piece choice
        game = new Game(playerIsX, challenge);

        //player and ai take turns placing pieces until one wins or the board fills up(ie a draw)
        while(counter < 9)
        {
            //start checking for wins only after the 3rd piece is placed
            if(counter >= 3)
            {	
                status = game.getStatus();
                if(status != GameStatus.IN_PROGRESS)
                {
                    game.printWinner(playerIsX);
                }
            }
            
            //modulos is used to determine whose turn it is
            //when whosMove is an even number its the ai's turn, when odd its the players
            //make a move and print the new board
            if(whosMove % 2 == 0)
            {
                game.aiPlacePiece();
                System.out.print("After AI move: \n");
                game.printBoard();
                counter++;
                whosMove = 1;
            }
            else
            {
                System.out.print("Enter desired x-coordinate: \n");
                i = scanner.nextInt();
                System.out.print("Enter desired y-coordinate: \n");
                j = scanner.nextInt();
                game.placePlayerPiece(i, j);
                System.out.print("After your move: \n");
                game.printBoard();
                counter++;
                whosMove = 0;
            }
        }
        
        //the board is now filled and still no winner therefore the game must be a draw
        game.printWinner(playerIsX);
    }
}

