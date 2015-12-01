package tictactoe;

import java.util.Scanner;

public class TicTacToe {   

    private static final String HEADER = 
                              "################################\n"
                            + "#     Tic Tac To by Svenja     #\n"
                            + "################################\n\n";

    private static final String PREFIX = "~$ ";

    public static void main(String[] args) throws Exception {
        
        // Tastaturbefehl einlesen
        final Scanner input = new Scanner(System.in);
        final TicTacToeGrid grid = new TicTacToeGrid();

        System.out.printf(HEADER);

        // Spielernamen festlegen und ausgeben
        final String playerOne = spielername(1, input);
        final String playerTwo = spielername(2, input);
        info("Hallo " + playerOne + " & " + playerTwo + " los gehts!");
        
        grid.print();
 
        // Spieleingaben
        while (true) {        	
        	setField(playerOne, input, TicTacToeGrid.STATE_PL_ONE, grid);
        	setField(playerTwo, input, TicTacToeGrid.STATE_PL_TWO, grid);
        }
    }


    /*
     * Ließt den Spielernamen über die Tastatur ein
     * 
     */
    private static String spielername(int player, Scanner input) {
        
    	if (player == 1) {
    		info("Name 1. Spieler:");
    	} else {
    		info("Name 2. Spieler:");
    	}
    	
        final String eingabe = input.next();
    	return eingabe;

    }
    
    /*
     * Setzt das jeweilige Feld der eigengeben Spieler (x oder o) in das entsprechende Feld
     * 
     */    
    private static void setField(String player, Scanner input, short gridplayer, TicTacToeGrid grid) {
        
    	int zeile, spalte = 0;
        
		try {
            info(player + " Zeile setzen (1-3):");
            zeile = input.nextInt()-1;
            info("und Spalte (1-3):");
            spalte = input.nextInt()-1;
            grid.setField(zeile, spalte, gridplayer);
        }

        catch (IllegalArgumentException e) {
            error("nicht erlaubtes Feld", e);
        }
        finally {
            grid.print();
            if (grid.isWinner(gridplayer)) {
                info(player + " gewinnt diese Runde!");
            return;
          } 
        }
    }
    
    private static void info(String line) {
        System.out.printf(PREFIX + line + "\n");
    }

    private static void error(String line, Throwable e) {
        System.err.printf(PREFIX + line + "\n" + e.getMessage() + "\n");
    }

}
