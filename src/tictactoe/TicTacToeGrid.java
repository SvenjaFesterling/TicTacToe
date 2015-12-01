package tictactoe;

public class TicTacToeGrid {

    public static final short STATE_FREE   = 0;
    public static final short STATE_PL_ONE = 1;
    public static final short STATE_PL_TWO = 2;

    private static final String SIGN_FREE   = " ";
    private static final String SIGN_PL_ONE = "x";
    private static final String SIGN_PL_TWO = "o";

    private boolean     playerOneWins   = false;
    private boolean     playerTwoWins   = false;

    private short[][]   grid            = new short[3][3];

    public void setField(int x, int y, short player) throws IllegalArgumentException {
        if (!validPosition(x, y)) {
            throw new IllegalArgumentException("no valid position: " + x + " - " + y);
        }
        else if (grid[x][y] != STATE_FREE) {
            throw new IllegalArgumentException("field is not free: " + x + " - " + y);
        }
        grid[x][y] = player;

        for (int row = 0; row < grid.length; row++) {
            if (winsRowForPlayer(row, player)) {
                setWinner(player, true);
            }
        }
        for (int column = 0; column < grid[0].length; column++) {
            if (winsColumnForPlayer(column, player)) {
                setWinner(player, true);
            }
        }
        if (winsDiagForPlayer(player)) {
            setWinner(player, true);
        }
    }  

    public void print() {
        try {
            System.out.println(this.toString());
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isWinner(short player) {
        switch (player) {
            case STATE_PL_ONE:
                return playerOneWins;
            case STATE_PL_TWO:
                return playerTwoWins;
            default:
                return false;
        }
    }

    public void setWinner(short player, boolean wins) {
        switch (player) {
            case STATE_PL_ONE:
                playerOneWins = wins;
            case STATE_PL_TWO:
                playerTwoWins = wins;
            default:
        }
    }

    private boolean winsRowForPlayer(int row, short player) {
        return grid[row][0] == grid[row][1] && grid[row][1] == grid[row][2] && grid[row][0] == player;
    }

    private boolean winsColumnForPlayer(int column, short player) {
        return grid[0][column] == grid[1][column] && grid[1][column] == grid[2][column] && grid[0][column] == player;
    }

    private boolean winsDiagForPlayer(short player) {
        return  (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[1][1] == player) ||
                (grid[0][2] == grid[1][1] && grid[1][1] == grid[0][2] && grid[1][1] == player);
    }

    @Override
    public String toString() throws IllegalArgumentException {
        return    "\t\t" + stateToSign(grid[0][0]) + "|" + stateToSign(grid[0][1]) + "|" + stateToSign(grid[0][2]) + "\n"
                + "\t\t" + "-----\n"
                + "\t\t" + stateToSign(grid[1][0]) + "|" + stateToSign(grid[1][1]) + "|" + stateToSign(grid[1][2]) + "\n"
                + "\t\t" + "-----\n"
                + "\t\t" + stateToSign(grid[2][0]) + "|" + stateToSign(grid[2][1]) + "|" + stateToSign(grid[2][2]) + "\n";
    }

    private boolean validPosition(int x, int y) {
        return (x >= 0 || x < grid.length) || (y >= 0 || y < grid[0].length);
    }

    private String stateToSign(short state) throws IllegalArgumentException {
        String sign = SIGN_FREE;

        switch (state) {
            case STATE_FREE: 
                sign = SIGN_FREE;
                break;
            case STATE_PL_ONE: 
                sign = SIGN_PL_ONE;
                break;
            case STATE_PL_TWO: 
                sign = SIGN_PL_TWO;
                break;
            default:
                throw new IllegalArgumentException("no valid state: " + state);
        }
        return sign;
    }

}
