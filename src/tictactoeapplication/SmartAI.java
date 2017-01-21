/**
 * Realization of AI interface using smart strategy [NOT IMPLEMENTED].
 *
 * STUDENTS: Nothing to change here.
 *
 * @author Andrew Vardy
 */

package tictactoeapplication;
/**
 * Realization of AI interface using smart strategy [NOT IMPLEMENTED].
 *
 * STUDENTS: Nothing to change here.
 *
 * @author Andrew Vardy
 */

public class SmartAI implements AI {
    
    private final char aipiece;
    
    /**
     * Construct a SmartAI.
     * 
     * @param aiIsX Indicates whether the AI player's piece is
     *              the 'X'.
     */
    public SmartAI(boolean aiIsX) 
    {
        aipiece = aiIsX?'X':'O';
    }

    /**
     * 
     * @param board - the boards configuration before the ai's turn
     * @return - move to be placed on the board 
     */
    @Override
    public Move chooseMove(Board board) 
    {
        //check row to block or win using i+1%3 and i+2%3
        //check column to block or win using j+1%3 and j+2%3
        
        Move move = null;
        
        //win/block coordinates. initialized at -1
        int canWinRow = -1;
        int canWinCol = -1;
        int canBlockRow = -1;
        int canBlockCol = -1;
        
        //array of the current board config
        char[] boardArray = board.boardGetter();
        
        //true if the location is empty, false if occupied
        boolean[][] availableMoves = new boolean[3][3];
        
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(boardArray[3*i+j] == 0)
                    availableMoves[i][j] = true;      
            }
        }
        
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                //find available spot
                    //check if the rest of the row or column is filled
                        //check if its filled by 2 of the same pieces(ai can win next move or player can win next move

                if(availableMoves[i][j]==true)
                {
                    //check rows
                    if(availableMoves[(i+1)%3][j] == availableMoves[(i+2)%3][j] && availableMoves[(i+1)%3][j] == false)
                    {
                        if(boardArray[3*((i+1)%3)+j] == boardArray[3*((i+2)%3)+j])
                        {
                            if(boardArray[3*((i+1)%3)+j] == aipiece)
                            {
                                canWinRow = i;
                                canWinCol = j;
                            }
                            else
                            {
                                canBlockRow = i;
                                canBlockCol = j;
                            }
                        }
                    }
                    
                    //check columns
                    else if(availableMoves[i][(j+1)%3] == availableMoves[i][(j+2)%3] && availableMoves[i][(j+1)%3] == false)
                    {
                        if(boardArray[3*i+((j+1)%3)] == boardArray[3*i+((j+2)%3)])
                        {
                            if(boardArray[3*i+((j+1)%3)] == aipiece)
                            {
                                canWinRow = i;
                                canWinCol = j;
                            }
                            else    
                            {
                                canBlockRow = i;
                                canBlockCol = j;
                            }
                        }
                    }
                }
            }  
        }
        
        //place piece to win
        if(canWinRow != -1)
            return new Move(canWinRow, canWinCol, aipiece);
        
        //if cant win, block
        if(canBlockRow != -1)
            return new Move(canBlockRow, canBlockCol, aipiece);
        
        //if cant win or block go middle if free
        if(availableMoves[1][1] == true)
            return new Move(1,1,aipiece);
       
        //check for xox diagonal block(go to a side as to not create 2 winning situations for player)
        if(boardArray[0] == boardArray[8] && boardArray[4] != boardArray[8])
            if(boardArray[0] != aipiece && boardArray[4] != 0 && boardArray[0] != 0)
                for(int i = 0; i < 2; i++)
                {
                    if(availableMoves[1][i*2] == true)
                        return new Move(1,(i*2),aipiece);
                    else if(availableMoves[i*2][1] == true)
                        return new Move((i*2),1,aipiece);
                }

        //if cant win, block, or middle go to a corner
        for(int i = 0; i < 2; i++)
        {
            if(availableMoves[i*2][i*2] == true)
                return new Move((i*2),(i*2),aipiece);
            if(availableMoves[0][2] == true)
                return new Move(0,2,aipiece);
            if(availableMoves[2][0] == true)
                return new Move(2,0,aipiece);
        }
        
        //if cant win, block, middle, or corner, go to a side
        for(int i = 0; i < 2; i++)
        {
            if(availableMoves[1][i*2] == true)
                move = new Move(1,(i*2),aipiece);
            else if(availableMoves[i*2][1] == true)
                move = new Move((i*2),1,aipiece);
        }
        return move;
    }
}
       
       