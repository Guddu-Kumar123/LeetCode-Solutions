class Solution {
    // 2D array to store binomial coefficients (Pascal's triangle)
    private int[][] binomialCoefficients;
    private final int MOD = (int) 1e9 + 7;

    public int numOfWays(int[] nums) {
        int n = nums.length;
      
        // Precompute binomial coefficients using Pascal's triangle
        // binomialCoefficients[i][j] represents C(i, j) = i choose j
        binomialCoefficients = new int[n][n];
        binomialCoefficients[0][0] = 1;
      
        for (int i = 1; i < n; ++i) {
            binomialCoefficients[i][0] = 1; // C(i, 0) = 1
            for (int j = 1; j <= i; ++j) {
                // Pascal's triangle formula: C(i, j) = C(i-1, j) + C(i-1, j-1)
                binomialCoefficients[i][j] = (binomialCoefficients[i - 1][j] + 
                                              binomialCoefficients[i - 1][j - 1]) % MOD;
            }
        }
      
        // Convert array to list for easier manipulation in recursion
        List<Integer> numbersList = new ArrayList<>();
        for (int num : nums) {
            numbersList.add(num);
        }
      
        // Calculate total ways and subtract 1 (the original sequence itself)
        return (dfs(numbersList) - 1 + MOD) % MOD;
    }

    /**
     * Recursively calculates the number of ways to reorder the BST sequence
     * @param nums Current list of numbers to process
     * @return Number of valid BST sequences that can be formed
     */
    private int dfs(List<Integer> nums) {
        // Base case: empty list or single element has only 1 way to arrange
        if (nums.size() < 2) {
            return 1;
        }
      
        // The first element will be the root of the BST
        int root = nums.get(0);
      
        // Partition elements into left and right subtrees
        List<Integer> leftSubtree = new ArrayList<>();
        List<Integer> rightSubtree = new ArrayList<>();
      
        for (int num : nums) {
            if (num < root) {
                leftSubtree.add(num);  // Elements less than root go to left subtree
            } else if (num > root) {
                rightSubtree.add(num);  // Elements greater than root go to right subtree
            }
            // Skip the root itself (num == root case)
        }
      
        // Get sizes of left and right subtrees
        int leftSize = leftSubtree.size();
        int rightSize = rightSubtree.size();
      
        // Recursively calculate ways for left and right subtrees
        int leftWays = dfs(leftSubtree);
        int rightWays = dfs(rightSubtree);
      
        // Total ways = leftWays * rightWays * C(leftSize + rightSize, rightSize)
        // The binomial coefficient represents ways to interleave left and right sequences
        return (int) ((long) leftWays * rightWays % MOD * 
                      binomialCoefficients[leftSize + rightSize][rightSize] % MOD);
    }
}
