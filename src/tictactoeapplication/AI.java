/**
 * Interface stipulating that an AI is a class that implements chooseMove.
 *
 * STUDENTS: Nothing to change here.
 *
 * @author Joshua Bennett
 */

package tictactoeapplication;

public interface AI 
{
    /**
     * Make a move given the current state of the board.
     * 
     * @param board the board before the current move has been made
     * @return the move to be placed on the board
     * @precondition There exists an open space on the board.
     */
    public Move chooseMove(Board board);
}
