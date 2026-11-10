
class Solution {
    public int climbStairs(int n, int[] costs) {

        int[] dp = new int[n + 1];

        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            int minCost = Integer.MAX_VALUE;

            for (int jump = 1; jump <= 3; jump++) {

                int previous = i - jump;

                if (previous >= 0) {

                    int totalCost = dp[previous]
                                  + costs[i - 1]
                                  + jump * jump;

                    minCost = Math.min(minCost, totalCost);
                }
            }

            dp[i] = minCost;
        }

        return dp[n];
    }
}
