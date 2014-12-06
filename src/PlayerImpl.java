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
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (lastMove == -1) {
			int successor = 2;
			while (successor < ((double) n / 2)) {
				list.add(successor);
				successor += 2;
			}
		} else {
			// Add all factors not crossed
			for (int i = 1; i <= lastMove / 2; i++) {
				if (crossedList[i] == 0 && lastMove % i == 0)
					list.add(i);
			}
			// Add all multiples not crossed
			int limit = n / lastMove;
			for (int i = 2; i <= limit; i++) {
				int multiple = i * lastMove;
				if (crossedList[multiple] == 0)
					list.add(multiple);
			}
		}
		return list;
	}

	// The max value function
	@Override
	public int max_value(GameState s) {
		List<Integer> list = generateSuccessors(s.lastMove, s.crossedList);
		int size = list.size();
		if (size==0) {
			s.leaf = true;
			s.bestMove = -1;
			return -1;
		} else {
			//Collections.sort(list);
			for (int i = size - 1; i >= 0; i--) {
				int possibleMove = list.get(i);
				s.crossedList[possibleMove] = 1;
				GameState gameState = new GameState(s.crossedList, possibleMove);
				int game_value = min_value(gameState);
				s.crossedList[possibleMove] = 0;

				if (game_value == 1) {
					s.bestMove = possibleMove;
					return 1;
				}
			}

			s.bestMove = list.get(list.size() - 1);
			return -1;
		}
	}

	// The min value function
	@Override
	public int min_value(GameState s) {
		List<Integer> list = generateSuccessors(s.lastMove, s.crossedList);
		int size = list.size();
		if (size==0) {
			s.leaf = true;
			s.bestMove = -1;
			return 1;
		} else {
			//Collections.sort(list);
			for (int i = (size-1); i >= 0; i--) {
				int possibleMove = list.get(i);
				s.crossedList[possibleMove] = 1;
				GameState gameState = new GameState(s.crossedList, possibleMove);
				int game_value = max_value(gameState);
				s.crossedList[possibleMove] = 0;

				if (game_value == -1) {
					s.bestMove = possibleMove;
					return -1;
				}
			}

			s.bestMove = list.get(list.size() - 1);
			return 1;
		}
	}

	// Function to find the next best move
	@Override
	public int move(int lastMove, int[] crossedList) {
		GameState gameState = new GameState(crossedList, lastMove);
		max_value(gameState);
		return gameState.bestMove;
	}
}