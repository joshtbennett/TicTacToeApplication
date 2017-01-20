/**
 * Represents the logic of the game in terms of detecting wins or draws.  Also
 * places new pieces for the human player or AI.
 *
 * @author Joshua Bennett
 */

package tictactoeapplication;

import java.util.Scanner;

public class Game {
    private Board board;
    private GameStatus status;
    private AI ai;
    private final boolean playerIsX, challenging;
    private Scanner scanner;
      
    /**
     * Construct a new Game according to the given parameters.
     * 
     * @param playerIsX - true if the player chooses to be X
     *                  - false if the player chooses to be O
     * @param challenging - true if the player wants a challenge (Smart AI)
     *                    - false if the player wants an easy opponent (Dumb AI)
     */
    public Game(boolean playerIsX, boolean challenging) 
    {
        this.challenging = challenging;
        this.playerIsX = playerIsX;
        
        //create an empty board
        board = new Board();
        
        status = GameStatus.IN_PROGRESS;
    }

    /**
     * @return - a copy of the board's current contents.
     */
    public Board getBoard() 
    {
        return board;
    }

    /**
     * @return - the game's status.
     */
    public GameStatus getStatus() 
    {
        return status;
    }
    
    /**
     * Place a piece for the player on the board.
     * @param i i-coordinate of desired position.
     * @param j j-coordinate of desired position
     * @return true only if the coordinates of the desired position are in
     * range and the corresponding cell is empty.
     *
     * @precondition status == IN_PROGRESS
     *
     */
    public boolean placePlayerPiece(int i, int j) 
    {
    	char piece;
        Move move;
        
        //determines which piece to put in the inputted coordinates
        piece = (playerIsX)? 'X':'O';

      //checks if inputted coordinates are empty       
        char location = board.get(i,j);
        if(location == 0)
        {
            move = new Move(i,j,piece);
            placePiece(move);   
        }
                
        //inputted coordinates are taken so this move cannot be made
        else
        {
            int xmove;
            int ymove;
            scanner = new Scanner(System.in);
            
            //prompts user to pick a valid spot on the board
            System.out.print("Invalid Move! Choose a different spot \n");
            System.out.print("Enter desired x-coordinate: \n");
            xmove = scanner.nextInt();
            System.out.print("Enter desired y-coordinate: \n");
            ymove = scanner.nextInt();
            placePlayerPiece(xmove, ymove);
        }
        
        //piece has been placed
        return true;
    }

    /**
     * @precondition status == IN_PROGRESS
     */
    public void aiPlacePiece() 
    {
    	Move move;
    	
    	ai = challenging? new SmartAI(!playerIsX):new DumbAI(playerIsX);
    	move = ai.chooseMove(board);
    	placePiece(move);
    }
    
    /**
     * print the current board to the console
     */
    void printBoard()
    {
        String stringBoard = board.toString();
        System.out.print(stringBoard);
    }

    /**
     * print the outcome of the game on the console
     */
    void printWinner(boolean bplayer)
    {
        if(null == status)
        {
            System.out.print("Draw! \n");
            endGame();
        }
        
        else switch (status) {
            case X_WON:
                
                if(bplayer==true)
                {
                    System.out.print("You won! \n");
                    endGame();
                }
                else
                {
                    System.out.print("You lost! \n");
                    endGame();
                }
                break;
            case O_WON:
                if(bplayer == false)
                {
                    System.out.print("You won! \n");
                    endGame();
                }
                else
                {
                    System.out.print("You lost! \n");
                    endGame();
                }
                break;
            default:
                System.out.print("Draw! \n");
                endGame();
                break;
        }
    }
    
    /**
     * uses the most recent move and checks if it is a winning move
     * 
     * @param move - the location and piece to be placed on the board
     * @param board - an array of the contents of the board
     */
    private void isWon(Move move, char[] board)
    {
        boolean h = true;
        boolean v = true;
        boolean d = false;
        int i = move.getI();
        int j = move.getJ();
        char piece = move.getPiece();
                    
        //check diagonals for a winner
        if((i==1 && j==1) || (i!=1 && j!=1))
        {
            // check '\' diagonal
            if(board[0] == piece && board[4] == piece && board[8] == piece)
                d = true;

            // check '/' diagonal
            else if(board[2] == piece && board[4] == piece && board[6] == piece)
                d = true;
        }

        //check horizontals for a winner
        for(int col = 0; col < 3; col++)
            if(board[3*i+col] != piece)
                h = false;

        //check verticals for a winner
        for(int row = 0; row < 3; row++)
            if(board[3*row+j] != piece)
                v = false;

        //if there is a winner, set status accordingly
        if(d == true || v == true || h == true)
        	status = (piece == 'X')? GameStatus.X_WON:GameStatus.O_WON;

    }
    
    /**
     * called when the game is won or drawn and ends the program
     */
    public void endGame()
    {
        System.exit(1);
    }
    
    /**
     * 
     * @param move - the location and piece to be placed on the board
     */
    public void placePiece(Move move)
    {        
        //replace the current board with an identical one, except with the new piece placed
        board = new Board(board, move);
        char[] boardContents;
        boardContents = board.boardGetter();
                    
        //check if the most recent move is a winning one
        isWon(move, boardContents);
    }
}
