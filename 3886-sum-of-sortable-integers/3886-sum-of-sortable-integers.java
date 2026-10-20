class Solution {

    public int sortableIntegers(int[] nums) {

        int n = nums.length;

        int[] prefixMax = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixMax[i + 1] =
                Math.max(prefixMax[i], nums[i]);
        }

        int[] suffixMin = new int[n + 1];

        suffixMin[n] = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            suffixMin[i] =
                Math.min(nums[i], suffixMin[i + 1]);
        }

        int[] prefixDrop = new int[n];

        for (int i = 1; i < n; i++) {

            prefixDrop[i] =
                prefixDrop[i - 1];

            if (nums[i - 1] > nums[i]) {
                prefixDrop[i]++;
            }
        }

        int answer = 0;

        for (int i = 1; i <= n / i; i++) {

            if (n % i == 0) {

                if (check(nums, prefixMax, suffixMin, prefixDrop, i)) {
                    answer += i;
                }

                int other = n / i;

                if (other != i &&
                    check(nums, prefixMax, suffixMin, prefixDrop, other)) {

                    answer += other;
                }
            }
        }

        return answer;
    }


    private boolean check(
        int[] nums,
        int[] prefixMax,
        int[] suffixMin,
        int[] prefixDrop,
        int k
    ) {

        int n = nums.length;

        for (int start = 0; start < n; start += k) {

            int end = start + k - 1;

            int drops =
                prefixDrop[end] - prefixDrop[start];

            // circular comparison:
            // last element -> first element
            if (nums[end] > nums[start]) {
                drops++;
            }

            // More than one circular drop:
            // cannot become sorted by rotation
            if (drops > 1) {
                return false;
            }

            // Elements before this block must not be
            // greater than elements from this block onward
            if (prefixMax[start] > suffixMin[start]) {
                return false;
            }
        }

        return true;
    }
}