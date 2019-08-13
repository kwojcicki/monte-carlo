package mcts;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class State {
    Board board;
    int playerNo;
    int visitCount;
    double winScore;
 
    // copy constructor, getters, and setters
 
    public State(Board board2) {
        this.board = new Board(board2);
    }

    public State() {
        board = new Board();
	}

	public State(State state) {
        this.board = new Board(state.getBoard());
        this.playerNo = state.playerNo;
        this.visitCount = state.visitCount;
        this.winScore = state.winScore;
	}

	public List<State> getAllPossibleStates() {
        List<State> states = new ArrayList<>();
        List<Position> empties = board.getEmptyPositions();
        for(Position empty: empties){
            State newState = new State(this.board);
            newState.setPlayerNo(3 - this.playerNo);
            newState.getBoard().performMove(newState.getPlayerNo(), empty);
            states.add(newState);
        }
        return states;
    }

    public void randomPlay() {
        /* get a list of all possible positions on the board and 
           play a random move */
        List<Position> empties = board.getEmptyPositions();
        Random random = new Random();
        this.board.performMove(this.getPlayerNo(), empties.get(random.nextInt(empties.size())));    
    }

	public void setBoard(Board board2) {
        board = board2;
    }
    
	public void setPlayerNo(int opponent) {
        playerNo = opponent;
    }
    
	public Board getBoard() {
		return board;
    }
    
	public int getOpponent() {
		return 3 - playerNo;
    }
    
	public void incrementVisit() {
        visitCount++;
	}

	public void addScore(int winScore2) {
        winScore += winScore2;
	}

	public int getPlayerNo() {
		return playerNo;
	}

	public void setWinScore(int minValue) {
        winScore = minValue;
	}

	public void togglePlayer() {
        this.playerNo = 3 - this.playerNo;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public double getWinScore() {
		return winScore;
	}
}