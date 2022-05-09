package dataStructure.graph;

import java.util.*;

/*
* 675. Cut Off Trees for Golf Event [hard]
* https://leetcode.com/problems/cut-off-trees-for-golf-event/
* You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix.
* In this matrix:
*   - 0 means the cell cannot be walked through.
*   - 1 represents an empty cell that can be walked through.
*   - A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
*
* In one step, you can walk in any of the four directions: north, east, south, and west.
* If you are standing in a cell with a tree, you can choose whether to cut it off.
*
* You must cut off the trees in order from shortest to tallest. When you cut off a tree,
* the value at its cell becomes 1 (an empty cell).
* Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees.
* If you cannot cut off all the trees, return -1.
*
* You are guaranteed that no two trees have the same height, and there is at least one tree needs to be cut off.
*/
public class Q675 {
    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> treeCoordinates = new ArrayList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                if (forest.get(i).get(j) > 1) {
                    treeCoordinates.add(new int[]{forest.get(i).get(j), i, j});
                }
            }
        }
        treeCoordinates.sort((a, b) -> Integer.compare(a[0], b[0]));
        int res = 0, startX = 0, startY = 0;
        for (int[] it: treeCoordinates) {
            int distance = bfs(startX, startY, it[1], it[2], forest);
            if (distance < 0) {
                return -1;
            }
            res += distance;
            startX = it[1];
            startY = it[2];
        }


        return res;
    }

    private int bfs(int startX, int startY, int endX, int endY, List<List<Integer>> forest) {
        int level = 0;
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        q.offerLast(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            int currSize = q.size();
            for (int i = 0; i < currSize; i++) {
                int[] curr = q.pollFirst();
                if (curr == null) {
                    continue;
                }
                if (curr[0] == endX && curr[1] == endY) {
                    return level;
                }
                int[] deltaX = new int[]{0, 0, -1, 1};
                int[] deltaY = new int[]{1, -1, 0, 0};
                for (int k = 0; k < 4; k++) {
                    int nextX = curr[0] + deltaX[k];
                    int nextY = curr[1] + deltaY[k];
                    if (isValidBound(forest, nextX, nextY) &&
                            !visited[nextX][nextY] &&
                            forest.get(nextX).get(nextY) > 0) {
                        q.offerLast(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private boolean isValidBound(List<List<Integer>> grid, int x, int y) {
        return 0 <= x && x < grid.size() && 0 <= y && y < grid.get(0).size();
    }
}
