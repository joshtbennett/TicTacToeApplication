/**
 * Represents a move (by the player or the AI) to a particular cell.
 *
 * STUDENTS: Nothing to change here.
 *
 * @author Andrew Vardy
 */

package tictactoeapplication;

public class Move {
    private final int i;
    private final int j;
    private final char piece;

    /**
     * Construct a move, which represents placing the given type of piece in
     * row i, column j.
     * @param i - the row the piece will be placed in
     * @param j - the column the piece will be placed in
     * @param piece - the piece that will be placed in the location
     */
    public Move(int i, int j, char piece) 
    {
        this.piece = piece;
        this.i = i;
        this.j = j;
    }
    
    public int getI() 
    {
        return i;
    }
    
    public int getJ() 
    {
        return j;
    }
    
    public char getPiece() 
    {
        return piece;
    }
}
