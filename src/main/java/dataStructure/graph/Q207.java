package dataStructure.graph;

import lombok.val;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 207. Course Schedule
* https://leetcode.com/problems/course-schedule/
* There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
* You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
* you must take course bi first if you want to take course ai.
*
* For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
* Return true if you can finish all courses. Otherwise, return false.
*/
public class Q207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            List<Integer> l = graph.getOrDefault(prerequisite[1], new ArrayList<>());
            l.add(prerequisite[0]);
            graph.put(prerequisite[1], l);
            inDegree[prerequisite[0]]++;
        }

        Deque<Integer> q = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.offerLast(i);
            }
        }
        while (!q.isEmpty()) {
            int course = q.pollFirst();
            count++;

            for (int nextCourse: graph.getOrDefault(course, new ArrayList<>())) {
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    q.offerLast(nextCourse);
                }
            }
        }

        return count == numCourses;
    }

    public static void main(String[] args) {
        Q207 solution = new Q207();
        val a = solution.canFinish(2, new int[][]{{1, 0}});
        System.out.println(a);

        val b = solution.canFinish(2, new int[][]{{1, 0}, {0, 1}});
        System.out.println(b);
    }
}
