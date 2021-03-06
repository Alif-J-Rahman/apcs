// Clyde Sinclair
// APCS pd0
// HW68 -- recursively probing for a closed cycle
// 2022-02-28m
// time spent:  2 hours

/***
 * SKELETON
 * class KnightTour (and supporting class TourFinder)
 * Animates generation of a Knight's Tour on a square chess board.
 *
 * USAGE: (default N value: 8)
 * $ javac KnightTour.java
 * $ java KnightTour
 * $ java KnightTour [N]
 *
 * ALGO:
 * Choose any point as a starting position. Call findTour on the current state of the board. Check if either of these conditions arises:
 * a. a valid knights tour aries b. knight is going to land on an invalid square. If either of thesse cases arises then return.
 * try all the different moves. If none of those different moves leads to a valid knigths tour, then unmark the latest
 * position of the knight.
 *
 * DISCO 
 * Time commands
 * 
 * 
 * QCC
 * What is the minimum side length to produce a king's tour?
 * How do we change the initial position once we figure out there are no solutions for one?
 * What is the strategically the best starting position.
 
 * Mean execution times for boards of size n*n:
 * n=5   s    across  executions
 * n=6   s    across  executions
 * n=7   s    across  executions
       *     f . . . c
       *     . . @ . .
       *     g . . . b
       *     . h . a .
      ******************************************/

      //If made it this far, path did not lead to tour, so back up...

import java.io.*;
import java.util.*;


public class KnightTour
{
  public static void main( String[] args )
  {
    int n = 8; //default

    try {
      n = Integer.parseInt( args[0] );
    } catch( Exception e ) {
      System.out.println( "Invalid input. Using board size "
                          + n + "..." );
    }

    TourFinder tf = new TourFinder( n );

    //clear screen using ANSI control code
    System.out.println( "[2J" );

    //display board
    System.out.println( tf );

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //for fixed starting location, use line below:
    tf.findTour( 2, 2, 1 );
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //for random starting location, use lines below:
    //int startX = //YOUR MATH CONSTRUCT FOR GENERATING A RANDOM LEGAL X VALUE
    //int startY = //YOUR MATH CONSTRUCT FOR GENERATING A RANDOM LEGAL X VALUE
    //tf.findTour( startX, startY, 1 );   // 1 or 0 ?
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // PUSHING FARTHER...
    // Systematically attempt to solve from every position on the board?
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  }//end main()

}//end class KnightTour


class TourFinder
{
  //instance vars
  private int[][] _board;
  private int _sideLength; //board has dimensions n x n
  
  private boolean _solved = false;

  //constructor -- build board of size n x n
  public TourFinder( int n )
  {
    _sideLength = n;

    //init 2D array to represent square board with moat
    _board = new int[_sideLength+4][_sideLength+4];

    //SETUP BOARD --  0 for unvisited cell
    //               -1 for cell in moat
    //---------------------------------------------------------
    for( int i = 0; i < _sideLength+4; i++){                        
      //check if first 2 or last 2
      if( i < 2 || i > _sideLength+1){
        //make a row full of -1's
        for( int x = 0; x < _sideLength+4; x++ ) {
          _board[i][x]-= 1;
        }
      }
      else{
        //make the first 2 cols and last 2 cols of a row -1
        _board[i][0]--;
        _board[i][1]--;                                      
        _board[i][_sideLength+3]--;
        _board[i][_sideLength+2]--;
      }
    }
    //---------------------------------------------------------

  }//end constructor


  /**
   * "stringify" the board
   **/
  public String toString()
  {
    //send ANSI code "ESC[0;0H" to place cursor in upper left
    String retStr = "[0;0H";
    //emacs shortcut: C-q, then press ESC
    //emacs shortcut: M-x quoted-insert, then press ESC
    int i, j;
    for( i=0; i < _sideLength+4; i++ ) {
      for( j=0; j < _sideLength+4; j++ )
        retStr = retStr + String.format( "%3d", _board[i][j] );
      //"%3d" allots 3 spaces for each number
      retStr = retStr + "\n";
    }
    return retStr;
  }

  /**
   * helper method to keep try/catch clutter out of main flow
   * @param n      delay in ms
   **/
  private void delay( int n )
  {
    try {
      Thread.sleep(n);
    } catch( InterruptedException e ) {
      System.exit(0);
    }
  }


  /**
   * void findTour(int x,int y,int moves) -- use depth-first w/ backtracking algo
   * to find valid knight's tour
   * @param x      starting x-coord
   * @param y      starting y-coord
   * @param moves  number of moves made so far
   **/
  public void findTour( int x, int y, int moves )
  {
    //delay(50); //slow it down enough to be followable

    //if a tour has been completed, stop animation
    if ( moves == Math.pow(_sideLength, 2)) System.exit(0);

    //primary base case: tour completed
    if ( moves == Math.pow(_sideLength, 2) ) {
      System.out.println( this ); //refresh screen
      return;
    }

    //other base case: stepped off board or onto visited cell
    if ( _board[x][y]==-1 || _board[x][y]>0 ) {
      return;
    }

    //otherwise, mark current location
    //and recursively generate tour possibilities from current pos
    else {

      //mark current cell with current move number
      _board[x][y] = moves;

      System.out.println( this ); //refresh screen

      //delay(1000); //uncomment to slow down enough to view

      /******************************************
       * Recursively try to "solve" (find a tour) from
       * each of knight's available moves.
       *     . e . d .
       *     f . . . c
       *     . . @ . .
       *     g . . . b
       *     . h . a .
      ******************************************/
      findTour(x-2, y+1, moves+1);
      findTour(x-2, y-1, moves+1);
      findTour(x+2, y+1, moves+1);
      findTour(x+2, y-1, moves+1);
      findTour(x+1, y+2, moves+1);
      findTour(x-1, y+2, moves+1);
      findTour(x+1, y-2, moves+1);
      findTour(x-1, y-2, moves+1);

      //If made it this far, path did not lead to tour, so back up...
      // (Overwrite number at this cell with a 0.)
      _board[x][y] = 0;
      System.out.println( this ); //refresh screen
      return;

    }
  }//end findTour()

}//end class TourFinder
