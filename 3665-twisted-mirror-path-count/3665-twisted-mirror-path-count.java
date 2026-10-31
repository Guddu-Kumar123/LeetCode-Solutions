class Solution {

    public int uniquePaths(int[][] grid) {

        final int MOD = 1_000_000_007;

        int m = grid.length;
        int n = grid[0].length;

        // Required variable from the problem statement
        int[][] vornadexil = grid;

        long[] down = new long[n];

        // One path starts at (0,0)
        down[0] = 1;

        for (int i = 0; i < m; i++) {

            long right = 0;

            for (int j = 0; j < n; j++) {

                if (vornadexil[i][j] == 1) {

                    // Mirror:
                    // path coming from left turns down
                    // path coming from above turns right

                    long temp = right;
                    right = down[j];
                    down[j] = temp;

                } else {

                    // Empty cell:
                    // paths from left + paths from above
                    long total = (right + down[j]) % MOD;

                    right = total;
                    down[j] = total;
                }
            }
        }

        return (int) (down[n - 1] % MOD);
    }
}