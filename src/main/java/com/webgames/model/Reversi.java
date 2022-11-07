package com.webgames.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class Reversi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1562836520930701257L;
	private static final int NUM_SPACES = 64;
	private Map<Marker, Integer> scoreBoard;
	private int turn;
	private boolean playing;

	// two pieces, and a third in case the game comes out as a tie.
	public enum Marker {
		WHITE, BLACK, TIE;

		public String getMarker() {
			return name();
		}

	}

	// every legal move in Reversi is + or - one of these amounts.
	enum Moves {
		ROW(1), COL(8), shortDiag(7), longDiag(9);

		private final int value;

		Moves(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private Marker[] grid;

	public Reversi() {
		scoreBoard = new HashMap<Marker, Integer>();
		grid = new Marker[NUM_SPACES];
		setUpBoard();
		turn = 0;
	}

	/**
	 * Places beginning pieces, increments score board, game set to playing.
	 */
	private void setUpBoard() {
		scoreBoard.put(Marker.BLACK, 2);
		scoreBoard.put(Marker.WHITE, 2);
		grid[28] = Marker.BLACK;
		grid[35] = Marker.BLACK;
		grid[27] = Marker.WHITE;
		grid[36] = Marker.WHITE;
		playing = true;
	}

	/**
	 * Return scoreBoard used for game. Uses a map so that it can associate a Marker
	 * key to its corresponding value.
	 * 
	 * @return
	 */
	public Map<Marker, Integer> getScoreBoard() {
		return scoreBoard;
	}

	/**
	 * Returns boolean true if mark was placed, check placeMark(Marker, int) for
	 * details.
	 * 
	 * @param loc
	 * @return
	 */
	public boolean placeMark(int loc) {
		return placeMark(getCurrentPlayer(), loc);
	}

	/**
	 * Takes the current player's mark and the location they've chosen, checks if it
	 * is a valid move. If the move is valid it will take all possible captures and
	 * replace them with player's mark as well as update score. Turn updates. After
	 * a mark is placed, game checks if the next player can play a move, if not,
	 * turn is skipped. If no players can place a move the game ends.
	 * 
	 * @param playerMark
	 * @param loc
	 * @return
	 */
	private boolean placeMark(Marker playerMark, int loc) {
		boolean placed = false;
		if (isValidMove(playerMark, loc)) { // check if the move is valid before finding all captures.
			ArrayList<Integer> capturesFromMove = new ArrayList<Integer>();
			for (Moves move : Moves.values()) {
				capturesFromMove.addAll(checkLocUp(playerMark, loc, move.getValue()));
				capturesFromMove.addAll(checkLocDown(playerMark, loc, move.getValue()));
			}

			grid[loc] = playerMark;
			for (Integer integer : capturesFromMove) { // updates the score on captures
				grid[integer] = playerMark;
				if (grid[integer] == Marker.BLACK) {
					addScore(Marker.BLACK);
					loseScore(Marker.WHITE);
				} else if (grid[integer] == Marker.WHITE) {
					addScore(Marker.WHITE);
					loseScore(Marker.BLACK);
				}
			}
			addScore(playerMark); // the chosen tile
			turn++;
			placed = true;
		}
		if (!hasValidMove(getCurrentPlayer())) { // if the following player does not have a legal move, pass. pass rule
			turn++;
		}
		if (!hasValidMove(Marker.BLACK) && !hasValidMove(Marker.WHITE)) { // if at the end of a turn, nobody can make a
																			// move - game over.
			gameOver();
		}

		if (placed) { // logging successful moves.
			Logger.getAnonymousLogger().info(playerMark + " placed " + loc);
		}
		return placed;
	}

	/**
	 * Takes a key for scoreBoard map, adds a point to their value.
	 * 
	 * @param playerMark
	 */
	public void addScore(Marker playerMark) {
		scoreBoard.put(playerMark, scoreBoard.get(playerMark) + 1);

	}

	/**
	 * Takes a key for scoreBoard map, decrements a points to their value.
	 * 
	 * @param playerMark
	 */
	public void loseScore(Marker playerMark) {
		scoreBoard.put(playerMark, scoreBoard.get(playerMark) - 1);

	}

	/**
	 * Turns playing off, JSP notices and the winner will be displayed
	 * 
	 */
	private void gameOver() {
		playing = false;
		String winner = getWinner().getMarker();
		Logger.getAnonymousLogger().info(winner); // log winner
	}

	/**
	 * Compares scores in scoreBoard, returns winner.
	 * 
	 * @return
	 */
	public Marker getWinner() {
		if (scoreBoard.get(Marker.BLACK) > scoreBoard.get(Marker.WHITE)) {
			return Marker.BLACK;
		} else if (scoreBoard.get(Marker.BLACK) == scoreBoard.get(Marker.WHITE)) {
			return Marker.TIE;
		} else {
			return Marker.WHITE;
		}

	}

	/**
	 * Returns a copy of the playing grid of this game
	 * 
	 * @return a copy of the playing grid
	 */
	public Marker[] getGrid() {
		return grid;
	}

	/**
	 * Returns the current player. Black moves first so they get even numbered
	 * turns, white gets odd.
	 * 
	 * @return
	 */
	public Marker getCurrentPlayer() {
		return turn % 2 == 0 ? Marker.BLACK : Marker.WHITE;
	}

	/**
	 * Returns state of the game, used by the JSP to announce game over.
	 * 
	 * @return
	 */
	public boolean getPlaying() {
		return playing;
	}

	/**
	 * Takes a location tile and checks all possible legal moves returns true if so
	 * else false.
	 * 
	 * @param playerMark
	 * @param loc
	 * @return
	 */
	public boolean isValidMove(Marker playerMark, int loc) {
		ArrayList<Integer> capturesFromMove = new ArrayList<Integer>();
		if (loc >= 0 && loc < 64) {
			for (Moves move : Moves.values()) { // each legal move is a set number, run them all through +- checker.
				capturesFromMove.addAll(checkLocUp(playerMark, loc, move.getValue()));
				capturesFromMove.addAll(checkLocDown(playerMark, loc, move.getValue()));
			}
			if (capturesFromMove.size() != 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns whether a player has a valid move left to play. Loops through grid
	 * checking for possible moves. If it has one it will return true else false;
	 * Conditional split to check by row, column, or diagonal.
	 * 
	 * @param playerMark
	 * @return
	 */
	public boolean hasValidMove(Marker playerMark) {
		boolean hasValid = false;
		for (int i = 0; i < 64; i++) {
			if (grid[i] != playerMark && grid[i] != null) {
				if (isValidMove(playerMark, i + 1) || isValidMove(playerMark, i - 1)) {
					hasValid = true;
				} else if (isValidMove(playerMark, i + 7) || isValidMove(playerMark, i - 7)) {
					hasValid = true;
				} else if (isValidMove(playerMark, i + 8) || isValidMove(playerMark, i - 8)) {
					hasValid = true;
				} else if (hasValid = isValidMove(playerMark, i + 9) || isValidMove(playerMark, i - 9)) {
					hasValid = true;
				}
			}
		}

		return hasValid;
	}

	/**
	 * Returns an ArrayList of all captures following a legal move. Takes a step
	 * increment of 1, 8, 7, 9 which translates to a movement by row, column, or
	 * diagonal. Method will check 'location + step' (1, 7, 8, 9) for all legal
	 * moves and checks one move beyond step for legal move or it gets discarded.
	 * The right edges under h column needed special protection. If step tries
	 * stepping over, it discards.
	 * 
	 * @param player
	 * @param loc
	 * @param step
	 * @return
	 */
	public ArrayList<Integer> checkLocUp(Marker player, int loc, int step) {
		ArrayList<Integer> sequence = new ArrayList<Integer>();

		if (loc + step > 63 || grid[loc] != null) { // if the step is about to be out of range or loc invalid, exit
			return sequence;
		}
		boolean playerHasEnd = false;

		while (grid[loc + step] != null && grid[loc + step] != player) {
			// always check if its about to go out of range.
			if (!(loc + step * 2 > 63) && (grid[loc + step * 2] != null || grid[loc + step * 2] == player)) {
				sequence.add(loc + step);
				loc += step;
				if (grid[loc + step] == player) {
					playerHasEnd = true;
				}
			} else {
				break;
			}
			if ((loc + 1) % 8 == 0 && (step == 1 || step == 9)) {
				sequence.clear();
				return sequence; // it's an edge break out can't go any farther
			}
		}
		if (playerHasEnd == false) { // moves collect but if White places a tile and there isn't another white piece
										// on the other end clear
			sequence.clear();
		}

		return sequence;
	}

	/**
	 * Returns an ArrayList of all captures following a legal move. Takes a step
	 * decrement of 1, 8, 7, 9 which translates to a movement by row, column, or
	 * diagonal. Method will check 'location - step' (1, 7, 8, 9) for all legal
	 * moves and checks one move beyond step for legal move or it gets discarded.
	 * The left edges under a column needed special protection. If step tries
	 * stepping under, it discards.
	 * 
	 * @param player
	 * @param loc
	 * @param step
	 * @return
	 */
	public ArrayList<Integer> checkLocDown(Marker player, int loc, int step) {
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		if (loc - step < 0 || grid[loc] != null) { // if the step is about to be out of range or if loc invalid, exit
			return sequence;
		}

		boolean playerHasEnd = false;
		while (grid[loc - step] != null && grid[loc - step] != player) {
			// check if it would be out of range or invalid before continuing.
			if (!(loc - step * 2 < 0) && (grid[loc - step * 2] != null || grid[loc - step * 2] == player)) {
				sequence.add(loc - step);
				loc -= step;
				if (grid[loc - step] == player) {
					playerHasEnd = true;
				}
			} else {
				break;
			}
			if (loc % 8 == 0 && (step == 1 || step == 9)) {
				sequence.clear();
				return sequence; // only make legal edge moves
			}
		}
		if (playerHasEnd == false) {
			sequence.clear();
		}
		return sequence;
	}
}
