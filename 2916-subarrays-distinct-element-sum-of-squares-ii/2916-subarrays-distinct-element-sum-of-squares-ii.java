import java.util.*;

class Solution {

    static final long MOD = 1_000_000_007L;

    long[] sum;
    long[] squareSum;
    long[] lazy;

    public int sumCounts(int[] nums) {

        int n = nums.length;

        sum = new long[4 * n];
        squareSum = new long[4 * n];
        lazy = new long[4 * n];

        HashMap<Integer, Integer> last = new HashMap<>();

        long answer = 0;

        for (int i = 0; i < n; i++) {

            int previous = last.getOrDefault(nums[i], -1);

            // Increment distinct count for start indexes
            // previous + 1 to i
            update(
                1,
                0,
                n - 1,
                previous + 1,
                i,
                1
            );

            last.put(nums[i], i);

            // Root contains sum of squares
            answer = (answer + squareSum[1]) % MOD;
        }

        return (int) answer;
    }

    private void update(
        int node,
        int left,
        int right,
        int qLeft,
        int qRight,
        long value
    ) {

        if (qLeft > right || qRight < left) {
            return;
        }

        if (qLeft <= left && right <= qRight) {

            long length = right - left + 1;

            squareSum[node] =
                (
                    squareSum[node]
                    + 2 * value * sum[node]
                    + value * value * length
                ) % MOD;

            sum[node] =
                (sum[node] + value * length) % MOD;

            lazy[node] =
                (lazy[node] + value) % MOD;

            return;
        }

        push(node, left, right);

        int mid = left + (right - left) / 2;

        update(
            node * 2,
            left,
            mid,
            qLeft,
            qRight,
            value
        );

        update(
            node * 2 + 1,
            mid + 1,
            right,
            qLeft,
            qRight,
            value
        );

        sum[node] =
            (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        squareSum[node] =
            (
                squareSum[node * 2]
                + squareSum[node * 2 + 1]
            ) % MOD;
    }

    private void push(
        int node,
        int left,
        int right
    ) {

        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;

        apply(
            node * 2,
            left,
            mid,
            lazy[node]
        );

        apply(
            node * 2 + 1,
            mid + 1,
            right,
            lazy[node]
        );

        lazy[node] = 0;
    }

    private void apply(
        int node,
        int left,
        int right,
        long value
    ) {

        long length = right - left + 1;

        squareSum[node] =
            (
                squareSum[node]
                + 2 * value * sum[node]
                + value * value * length
            ) % MOD;

        sum[node] =
            (sum[node] + value * length) % MOD;

        lazy[node] =
            (lazy[node] + value) % MOD;
    }
}