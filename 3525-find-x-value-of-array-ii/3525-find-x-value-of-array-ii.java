class Solution {
  // Each node stores the product of the whole segment modulo k
  // and the number of prefixes for every possible remainder.
  static class Node {
    int prod = 1;
    int[] cnt = new int[5];
  }

  private Node[] tree;
  private int n, k;

  // Merge two adjacent segments.
  // Keep all prefixes from the left segment.
  // Prefixes from the right segment are multiplied by the full left product.
  private Node mergeNodes(Node left, Node right) {
    Node res = new Node();
    res.prod = (left.prod * right.prod) % k;

    for(int r = 0; r < k; r++) res.cnt[r] += left.cnt[r];

    for(int r = 0; r < k; r++) {
      int nr = (left.prod * r) % k;
      res.cnt[nr] += right.cnt[r];
    }

    return res;
  }

  // Build the segment tree.
  // A leaf has exactly one non-empty prefix: the element itself.
  private void build(int node, int l, int r, int[] nums) {
    if(l == r) {
      tree[node] = new Node();

      int rem = nums[l] % k;
      tree[node].prod = rem;
      tree[node].cnt[rem] = 1;

      return;
    }

    int mid = l + (r - l) / 2;

    build(node * 2, l, mid, nums);
    build(node * 2 + 1, mid + 1, r, nums);

    tree[node] = mergeNodes(tree[node * 2], tree[node * 2 + 1]);
  }

  // Permanently update nums[index] and rebuild the affected tree nodes.
  private void update(int node, int l, int r, int index, int value) {
    if(l == r) {
      tree[node] = new Node();

      int rem = value % k;
      tree[node].prod = rem;
      tree[node].cnt[rem] = 1;

      return;
    }

    int mid = l + (r - l) / 2;

    if(index <= mid) update(node * 2, l, mid, index, value);
    else update(node * 2 + 1, mid + 1, r, index, value);

    tree[node] = mergeNodes(tree[node * 2], tree[node * 2 + 1]);
  }

  // Return the combined information for range [ql, qr].
  // For each query this range is [start, n - 1].
  private Node query(int node, int l, int r, int ql, int qr) {
    if(ql <= l && r <= qr) return tree[node];

    int mid = l + (r - l) / 2;

    if(qr <= mid) return query(node * 2, l, mid, ql, qr);
    if(ql > mid) return query(node * 2 + 1, mid + 1, r, ql, qr);

    Node left = query(node * 2, l, mid, ql, qr);
    Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

    return mergeNodes(left, right);
  }

  public int[] resultArray(int[] nums, int k, int[][] queries) {
    this.n = nums.length;
    this.k = k;

    // Build the tree for the initial array.
    tree = new Node[4 * n];
    build(1, 0, n - 1, nums);

    int[] result = new int[queries.length];

    // Apply each permanent update, then analyze nums[start..n - 1].
    for(int i = 0; i < queries.length; i++) {
      int index = queries[i][0];
      int value = queries[i][1];
      int start = queries[i][2];
      int x = queries[i][3];

      update(1, 0, n - 1, index, value);

      // cnt[x] is the number of prefixes of nums[start..n - 1]
      // whose product modulo k equals x.
      Node res = query(1, 0, n - 1, start, n - 1);
      result[i] = res.cnt[x];
    }

    return result;
  }
}