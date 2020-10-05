package com.spring.euler.solutions;

public abstract class Problem31 {
    public static String run() {
        int target = 200;
        int[] coins = { 1, 2, 5, 10, 20, 50, 100, 200 };
        int[] ways = new int[target + 1];
        // Dynamic programming solution solves all the simpler versions of the problem and adds them together
        // e.g. All ways of making 5p with only 1p coins, then 5p using 1p and 2p coins etc etc
        ways[0] = 1;
        for (int coin: coins) {
            for (int j = coin; j <= target; j++) { ways[j] += ways[j - coin]; }
        }
        return String.valueOf(ways[200]);
    }
}
