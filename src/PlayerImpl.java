import java.util.*;

public class PlayerImpl implements Player {
	// Identifies the player
	private int name = 0;
	int n = 0;

	// Constructor
	public PlayerImpl(int name, int n) {
		this.name = 0;
		this.n = n;
	}

	// Function to find possible successors
	@Override
	public ArrayList<Integer> generateSuccessors(int lastMove, int[] crossedList) {
		// TODO Add your code here

		return null;
	}

	// The max value function
	@Override
	public int max_value(GameState s) {
		// TODO Add your code here

		return -1;
	}

	// The min value function
	@Override
	public int min_value(GameState s) {
		// TODO Add your code here

		return -1;
	}

	// Function to find the next best move
	@Override
	public int move(int lastMove, int[] crossedList) {
		// TODO Add your code here

		return -1;
	}
}