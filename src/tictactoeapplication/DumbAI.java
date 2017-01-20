/**
 * Realization of AI interface using simplistic random placement strategy.
 *
 * @author Joshua Bennett
 */
package tictactoeapplication;

import java.util.Random;

public class DumbAI implements AI {
    
    private final Random random = new Random();

    private final char piece;
    
    /**
     * Construct a DumbAI.
     * 
     * @param aiIsX Indicates whether the AI player's piece is
     *              the 'X'.
     */
    public DumbAI(boolean aiIsX) 
    {
    	piece = aiIsX? 'O':'X';
    }

    @Override
    public Move chooseMove(Board board) 
    {
        Move move = null;
        int i;
        int j;

        while(move == null)
        {
            //generate random coordinates
            i = random.nextInt(9);
            i = i/3;
            j = random.nextInt(9);
            j = j%3;
            
            //check to make sure the random coordinates are not taken
            char location = board.get(i,j);
            if(location == 0)
                //define new move
                move = new Move(i,j,piece);
        }
        return move;
    }
    public char getPiece()
    {
    	return piece;
    }
}
