class Solution {

    public int minCost(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Required by the problem statement
        int[][] molqaviren = grid;

        // dp[i][j][x] = can we reach cell (i,j) with XOR = x?
        boolean[][][] dp = new boolean[m][n][1024];

        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                for (int xor = 0; xor < 1024; xor++) {

                    if (!dp[i][j][xor]) {
                        continue;
                    }

                    // Move down
                    if (i + 1 < m) {

                        int newXor =
                                xor ^ grid[i + 1][j];

                        dp[i + 1][j][newXor] = true;
                    }

                    // Move right
                    if (j + 1 < n) {

                        int newXor =
                                xor ^ grid[i][j + 1];

                        dp[i][j + 1][newXor] = true;
                    }
                }
            }
        }

        // Smallest reachable XOR at bottom-right
        for (int xor = 0; xor < 1024; xor++) {

            if (dp[m - 1][n - 1][xor]) {
                return xor;
            }
        }

        return -1;
    }
}