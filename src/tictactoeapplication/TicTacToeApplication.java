/**
 * Contains the main method. Creates a ConsoleRunner and then calls its
 * mainLoop method.
 *
 * @author 
 * 
 * Joshua Bennett
 * ENGI 5895 - Software Design
 * Assignment 1
 * January 23rd 2017
 * 
 */

package tictactoeapplication;

public class TicTacToeApplication {

    public static void main(String[] args) 
    {
        ConsoleRunner runner = new ConsoleRunner();
        runner.mainLoop();
    }
}
