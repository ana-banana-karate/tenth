import java.util.*;

public class Main {

	// LeetCode 403 - Frog Jump
	// Given a list of stones' positions in ascending order, determine if the frog
	// can cross to the last stone. The frog starts at position 0 and the first
	// jump must be 1. After a jump of k, the next jump can be k-1, k, or k+1.

	public static boolean canCross(int[] stones) {
		int n = stones.length;
		if (n == 0) return true;
		if (n > 1 && stones[1] != 1) return false; // first jump must be 1

		// map stone position to index for O(1) lookups
		Map<Integer, Integer> posToIndex = new HashMap<>();
		for (int i = 0; i < n; i++) posToIndex.put(stones[i], i);

		// dp[i] -> set of jump sizes that can land on stone i
		@SuppressWarnings("unchecked")
		Set<Integer>[] dp = new Set[n];
		for (int i = 0; i < n; i++) dp[i] = new HashSet<>();
		dp[0].add(0);

		for (int i = 0; i < n; i++) {
			for (int k : dp[i]) {
				for (int step = Math.max(1, k - 1); step <= k + 1; step++) {
					int nextPos = stones[i] + step;
					Integer j = posToIndex.get(nextPos);
					if (j != null) {
						dp[j].add(step);
					}
				}
			}
		}

		return !dp[n - 1].isEmpty();
	}

	// Simple test harness
	public static void main(String[] args) {
		int[][] tests = {
			{0,1,3,5,6,8,12,17}, // true
			{0,1,2,3,4,8,9,11}, // false
			{0}, // true
			{0,1}, // true
			{0,2} // false
		};

		for (int i = 0; i < tests.length; i++) {
			boolean res = canCross(tests[i]);
			System.out.printf("Test %d: %s -> %b\n", i+1, Arrays.toString(tests[i]), res);
		}
	}
}

public class main {
    
}
