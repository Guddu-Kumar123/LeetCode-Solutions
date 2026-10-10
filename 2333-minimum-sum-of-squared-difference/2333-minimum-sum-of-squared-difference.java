class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] differences = new int[n];
        long totalSum = 0;
        int maxDifference = 0;
        int totalOperations = k1 + k2;

        // Calculate absolute differences and find the maximum difference
        for (int i = 0; i < n; i++) {
            differences[i] = Math.abs(nums1[i] - nums2[i]);
            totalSum += differences[i];
            maxDifference = Math.max(maxDifference, differences[i]);
        }

        // If we can reduce all differences to 0, return 0
        if (totalSum <= totalOperations) {
            return 0;
        }

        // Binary search using the template to find the optimal threshold
        int left = 0;
        int right = maxDifference - 1;
        int firstTrueIndex = maxDifference;  // Default if no smaller threshold is feasible

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long operationsNeeded = 0;

            // Calculate operations needed to reduce all values to at most mid
            for (int value : differences) {
                operationsNeeded += Math.max(value - mid, 0);
            }

            if (operationsNeeded <= totalOperations) {
                // Feasible: can achieve this threshold
                firstTrueIndex = mid;
                right = mid - 1;  // Try to find smaller threshold
            } else {
                left = mid + 1;
            }
        }

        int optimalThreshold = firstTrueIndex;

        // Reduce all differences greater than threshold to the threshold value
        for (int i = 0; i < n; i++) {
            totalOperations -= Math.max(0, differences[i] - optimalThreshold);
            differences[i] = Math.min(differences[i], optimalThreshold);
        }

        // Use remaining operations to further reduce values at the threshold
        // This distributes the remaining operations optimally
        for (int i = 0; i < n && totalOperations > 0; i++) {
            if (differences[i] == optimalThreshold) {
                totalOperations--;
                differences[i]--;
            }
        }

        // Calculate the sum of squares of the final differences
        long sumOfSquares = 0;
        for (int value : differences) {
            sumOfSquares += (long) value * value;
        }

        return sumOfSquares;
    }
}
