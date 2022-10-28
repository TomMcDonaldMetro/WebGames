package com.webgames.model;

import java.io.Serializable;

public class TicTacToe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int NUM_SPACES = 9; // total number of moves possible
	private int turn;

	public enum Mark {
		X, O;
		
		public String getMark() {
			return name();
		}
	}
	
	enum WinLocs {
		LOC(new int[][] {{0, 1, 2},
						 {3, 4, 5},
						 {6, 7, 8},
						 {0, 3, 6},
						 {1, 4, 7},
						 {2, 5, 8},
						 {0, 4, 8},
						 {2, 4, 6}});
		private int locs[][];
		WinLocs(int[][] locs){
			this.locs = locs;
		}
		
	}

	private Mark[] board;
	private boolean[] winLoc;
	public TicTacToe() {
		board = new Mark[NUM_SPACES];
		winLoc = new boolean[NUM_SPACES];
		turn = 0;
	}

	public boolean isOver() {
		return isWon() || turn == NUM_SPACES;
	}

	private boolean isWon() {
		for (WinLocs locs : WinLocs.values()) {
			for (int[] loc : locs.locs) {
				// if a == b and b == c then a == c and thats 3 in a row for a win
				if(board[loc[0]] != null && board[loc[0]] == board[loc[1]] && board[loc[1]] == board[loc[2]]) {
					setWinLoc(loc);
					return true;
				}
			}
		}
		return false;
	}

	private void setWinLoc(int[] loc) {
		// TODO Auto-generated method stub
		// This will be used to highlight the board displaying the location
	}

	public boolean placeMark(int loc) {

		return placeMark(getCurrentPlayer(), loc);
	}

	private boolean placeMark(Mark currentPlayer, int loc) {
		// check if the move is valid
		if ((board[loc] == null && currentPlayer != null)) {
			// set the move on the board
			board[loc] = currentPlayer;
			
			if(!isOver()) {
				turn++;
			}
			return true;
		}
		return false;
	}
	
	public Mark getWinner() {
		if(isWon()) {
			return getCurrentPlayer();
		}
		else {
			return null;
		}
	}

	private Mark getCurrentPlayer() {
		return turn % 2 == 0 ? Mark.X : Mark.O;
	}

	public Mark[] getBoard() {
		return this.board;
	}
	
	public static void main(String[] args) {
		TicTacToe s = new TicTacToe();
		
	}
}
