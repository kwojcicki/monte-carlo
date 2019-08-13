package mcts;

import java.util.ArrayList;
import java.util.List;

public class Board {
    int[][] boardValues;
    int totalMoves = 0;
    public static final int DEFAULT_BOARD_SIZE = 3;
    public static final int IN_PROGRESS = -1;
    public static final int DRAW = 0;
    public static final int P1 = 1;
    public static final int P2 = 2;

    public Board(Board board2) {
        this.boardValues = new int[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
        for (int i = 0; i < boardValues.length; i++) {
            for (int j = 0; j < boardValues[0].length; j++) {
                this.boardValues[i][j] = board2.boardValues[i][j];
            }
        }
    }

    public Board() {
        boardValues = new int[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
    }

    // getters and setters
    public void performMove(int player, Position p) {
        this.totalMoves++;
        boardValues[p.getX()][p.getY()] = player;
    }

    public int checkStatus() {
        /*
         * Evaluate whether the game is won and return winner. If it is draw return 0
         * else return -1
         */
        int boardSize = boardValues.length;
        int maxIndex = boardSize - 1;
        int[] diag1 = new int[boardSize];
        int[] diag2 = new int[boardSize];

        for (int i = 0; i < boardSize; i++) {
            int[] row = boardValues[i];
            int[] col = new int[boardSize];
            for (int j = 0; j < boardSize; j++) {
                col[j] = boardValues[j][i];
            }

            int checkRowForWin = checkForWin(row);
            if (checkRowForWin != 0)
                return checkRowForWin;

            int checkColForWin = checkForWin(col);
            if (checkColForWin != 0)
                return checkColForWin;

            diag1[i] = boardValues[i][i];
            diag2[i] = boardValues[maxIndex - i][i];
        }

        int checkDia1gForWin = checkForWin(diag1);
        if (checkDia1gForWin != 0)
            return checkDia1gForWin;

        int checkDiag2ForWin = checkForWin(diag2);
        if (checkDiag2ForWin != 0)
            return checkDiag2ForWin;

        if (getEmptyPositions().size() > 0)
            return IN_PROGRESS;
        else
            return DRAW;
    }

    private int checkForWin(int[] row) {
        boolean isEqual = true;
        int size = row.length;
        int previous = row[0];
        for (int i = 0; i < size; i++) {
            if (previous != row[i]) {
                isEqual = false;
                break;
            }
            previous = row[i];
        }
        if (isEqual)
            return previous;
        else
            return 0;
    }

    public List<Position> getEmptyPositions() {
        int size = this.boardValues.length;
        List<Position> emptyPositions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardValues[i][j] == 0)
                    emptyPositions.add(new Position(i, j));
            }
        }
        return emptyPositions;
    }

    public void printBoard() {
        int size = this.boardValues.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(boardValues[i][j] + " ");
            }
            System.out.println();
        }
    }
}