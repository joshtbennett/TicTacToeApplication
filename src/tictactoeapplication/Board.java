/**
 * An immutable class that represents the state of the 3x3 tic-tac-toe board.
 *
 * @author Joshua Bennett
 * 
 */

package tictactoeapplication;

public class Board {

    private final char[][] board = new char[3][3];

    /**
     * Construct an empty board (contains all space char's).
     */
    public Board() 
    {
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                board[i][j] = 0;
    }

    /**
     * Given the 'other' board as a starting condition, apply the given
     * 'move' to generate this board's state.
     * 
     * @param other - the board before the move has been placed
     * @param move - the the location and piece to be placed on the board
     */
    public Board(Board other, Move move) 
    {
    	//recreate old board
        for(int i = 0; i < 3; i ++)
            System.arraycopy(other.board[i], 0, board[i], 0, 3);
        
        //change the spot where a piece is being placed
        int x = move.getI();
        int y = move.getJ();
        char p = move.getPiece();
        board[x][y] = p;
    }

    /**
     * Convert to a string that shows the board's state.
     * @return s - the board and all its contents in the form of a string 
     */
    @Override
    public String toString() 
    {
    	//s originally represents an empty board, to be filled later in the method
        StringBuilder s = new StringBuilder("-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------\n|   |   |   |\n-------------\n");
        
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(board[i][j] != 0)
                   //formula derived in order to but the pieces in the center of the displayed sections
                   s.setCharAt((4*(7*i+j+4)), board[i][j]);
      
        return s.toString();
    }

    /**
     * Get the entry of the board at column i, row j.  Both indices should
     * be in the range [0, 2].
     *
     * @param i - the row to be checked
     * @param j - the column to be checked
     * @return - the character residing in the given location of the current board
     */
    public char get(int i, int j) 
    {
    	return board[i][j];
    }
    
    /**
     * 
     * @return - true if there remain no empty spots on the board.
     *         - false if an empty spot is found
     */
    public boolean isFull() 
    {
    	for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(board[i][j] == 0)
                    return false;
        return true;
    }
    
    /**
     * 
     * @return - a copy of the board array
     */
    public char[] boardGetter()
    {
       char[] boardCopy = new char[9];
       for(int i = 0; i < 3; i++)
           System.arraycopy(board[i], 0, boardCopy, 3*i, 3);
       return boardCopy;
    }
    
    public char checkVictory()
    {
        for(int i = 0; i < 3; i ++)
        {
            
            //check horizontals
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i])
            {
                if(board[0][i] == 'X')
                    return 'X';
                else if(board[0][i] == 'O')
                    return 'O';
            }
             
            //check verticals
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2])
            {
                if(board[i][0] == 'X')
                    return 'X';
                else if(board[i][0] == 'O')
                    return 'O';
            }
        }
        
        //check diagonals
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2])
        {
                if(board[1][1] == 'X')
                    return 'X';
                else if(board[1][1] == 'O')
                    return 'O';
        }
        
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
                if(board[1][1] == 'X')
                    return 'X';
                else if(board[1][1] == 'O')
                    return 'O';
        }
        
        //check for draw
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(board[i][j] == 0 || board[i][j] == '0')
                    return '0';
       
        //game is a draw
        return 'D';
    }
}