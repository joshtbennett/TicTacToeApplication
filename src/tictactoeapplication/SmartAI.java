/**
 * Realization of AI interface using smart strategy [NOT IMPLEMENTED].
 *
 * STUDENTS: Nothing to change here.
 *
 * @author Andrew Vardy
 */


/*
    node:
        depth
        score
        gameover
        preboard[][]
        postboard[][]
        player
*/

/*
    create root node using current board
    recursively method to find all possible outcomes
        once a leaf is reached, set the score and depth fields
    bring scores and depths up from leaves to level 2(just below the root)
    make the move with the highest (score - depth)

*/


package tictactoeapplication;
/**
 * Realization of AI interface using smart strategy [NOT IMPLEMENTED].
 *
 * STUDENTS: Nothing to change here.
 *
 * @author Andrew Vardy
 */

import java.lang.UnsupportedOperationException;

public class SmartAI implements AI {
    
    /**
     * Construct a SmartAI.
     * 
     * @param aiIsX Indicates whether the AI player's piece is
     *              the 'X'.
     */
    public SmartAI(boolean aiIsX) 
    {
        throw new UnsupportedOperationException("Not implemented.");
    }

    public Move chooseMove(Board board) 
    {
        throw new UnsupportedOperationException("Not implemented.");
    }
}
       
       