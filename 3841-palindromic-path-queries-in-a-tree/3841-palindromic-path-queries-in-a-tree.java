import java.util.*;

class Solution {

    List<Integer>[] graph;
    int[] parent;
    int[] depth;
    int[] tin;
    int[] tout;
    int[][] up;

    int timer;
    int LOG;

    public List<Boolean> palindromePath(
            int n,
            int[][] edges,
            String s,
            String[] queries) {

        // Build graph
        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        parent = new int[n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];

        // Euler tour
        dfs(0, -1);

        // Binary lifting table
        LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int j = 1; j < LOG; j++) {

            for (int i = 0; i < n; i++) {

                up[j][i] =
                        up[j - 1][up[j - 1][i]];
            }
        }

        // bitMask[i] represents character of node i
        int[] bitMask = new int[n];

        for (int i = 0; i < n; i++) {

            bitMask[i] =
                    1 << (s.charAt(i) - 'a');
        }

        Fenwick tree = new Fenwick(n);

        // Add each node at entry and exit
        for (int i = 0; i < n; i++) {

            tree.update(tin[i], bitMask[i]);

            if (tout[i] < n) {
                tree.update(tout[i], bitMask[i]);
            }
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {

            String[] part = query.split(" ");

            String operation = part[0];

            int u = Integer.parseInt(part[1]);

            // ---------------- UPDATE ----------------
            if (operation.equals("update")) {

                char newChar = part[2].charAt(0);

                int newMask =
                        1 << (newChar - 'a');

                int oldMask = bitMask[u];

                if (oldMask != newMask) {

                    int change =
                            oldMask ^ newMask;

                    tree.update(tin[u], change);

                    if (tout[u] < n) {
                        tree.update(tout[u], change);
                    }

                    bitMask[u] = newMask;
                }
            }

            // ---------------- QUERY ----------------
            else {

                int v =
                        Integer.parseInt(part[2]);

                int lca =
                        findLCA(u, v);

                int rootToU =
                        tree.query(tin[u]);

                int rootToV =
                        tree.query(tin[v]);

                int pathMask =
                        rootToU
                        ^ rootToV
                        ^ bitMask[lca];

                // At most one odd-frequency character
                boolean possible =
                        (pathMask & (pathMask - 1)) == 0;

                answer.add(possible);
            }
        }

        return answer;
    }

    // Euler Tour
    private void dfs(int node, int par) {

        if (par == -1) {
            parent[node] = node;
        } else {
            parent[node] = par;
        }

        tin[node] = timer++;

        for (int next : graph[node]) {

            if (next != par) {

                depth[next] =
                        depth[node] + 1;

                dfs(next, node);
            }
        }

        tout[node] = timer;
    }

    // Lowest Common Ancestor
    private int findLCA(int u, int v) {

        if (depth[u] < depth[v]) {

            int temp = u;
            u = v;
            v = temp;
        }

        int difference =
                depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {

            if ((difference & (1 << j)) != 0) {

                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {

            if (up[j][u] != up[j][v]) {

                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }


    // Fenwick Tree for XOR
    static class Fenwick {

        int[] tree;
        int n;

        Fenwick(int n) {

            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int value) {

            index++;

            while (index <= n) {

                tree[index] ^= value;

                index +=
                        index & -index;
            }
        }

        int query(int index) {

            index++;

            int result = 0;

            while (index > 0) {

                result ^= tree[index];

                index -=
                        index & -index;
            }

            return result;
        }
    }
}