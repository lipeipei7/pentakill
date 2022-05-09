package dataStructure.graph;

import algorithm.UnionFind;

import java.util.ArrayDeque;
import java.util.Deque;

/*
* 200. Number of Islands
* https://leetcode.com/problems/number-of-islands/
* Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.
*
* An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
* You may assume all four edges of the grid are all surrounded by water.
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
*/
public class Q200 {
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }

                res++;
                grid[i][j] = '0';
                Deque<Integer> q = new ArrayDeque<>();
                int nc = grid[0].length;

                q.offerLast(i * nc + j); //x and y axis
                while (!q.isEmpty()) {
                    int id = q.pollFirst();
                    int row = id / nc, col = id % nc;

                    int[] rowI = new int[]{row - 1, row, row + 1, row};
                    int[] colI = new int[]{col, col - 1, col, col + 1};
                    for (int k = 0; k < 4; k++) {
                        if (rowI[k] >= 0 && rowI[k] < grid.length &&
                                colI[k] >= 0 && colI[k] < grid[0].length &&
                                grid[ rowI[k] ][ colI[k] ] == '1') {
                            q.offerLast((rowI[k]) * nc + colI[k]);
                            grid[rowI[k]][colI[k]] = '0';
                        }
                    }
                }

            }
        }
        return res;
    }

    public int numIslandsUF(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;

        UnionFind uf = new UnionFind(nr * nc);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '0') {
                    uf.root[r * nc + c] = -1;
                    continue;
                }

                grid[r][c] = '0';

                int[] rI = new int[]{r - 1, r, r + 1, r};
                int[] cI = new int[]{c, c - 1, c, c + 1};
                for (int i = 0; i < 4; i++) {
                    if (rI[i] >= 0 && cI[i] >= 0 &&
                            rI[i] < nr && cI[i] < nc &&
                            grid[rI[i]][cI[i]] == '1') {
                        uf.union(r * nc + c, rI[i] * nc + cI[i]);
                    }
                }
            }
        }

        return uf.getNRoots();
    }

    public static void main(String[] args) {
        Q200 solution = new Q200();
        System.out.println(solution.numIslandsUF(new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}}));

        System.out.println(solution.numIslandsUF(new char[][]{
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}}));
    }
}