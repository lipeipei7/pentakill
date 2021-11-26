package com.learning.pentaQ.data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
* 787. Cheapest Flights Within K Stops
* https://leetcode.com/problems/cheapest-flights-within-k-stops/
*/
class Graph787 {
    static class TripEdge {
        int dst;
        int cost;
        int stops;
        int minCost;
        int tmpMinCost;
        int minCostStops;

        TripEdge (int dst, int cost, int stops) {
            this.dst = dst;
            this.cost = cost;
            this.stops = stops;
            this.minCost = cost;
            this.tmpMinCost = Integer.MAX_VALUE;
            this.minCostStops = stops;
        }
        TripEdge (TripEdge edge) {
            this.dst = edge.dst;
            this.cost = edge.cost;
            this.stops = edge.stops;
            this.minCost = edge.minCost;
            this.tmpMinCost = edge.tmpMinCost;
            this.minCostStops = edge.minCostStops;
        }
    }

    static class Vertex {
        int id;
        int cost;

        Vertex(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    public int findCheapestPriceDIJK(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<TripEdge>> graph = new HashMap<>();

        for (int[] f: flights) {
            List<TripEdge> trips = graph.getOrDefault(f[0], new ArrayList<>());
            trips.add(new TripEdge(f[1], f[2], 0));
            graph.put(f[0], trips);
        }

        PriorityQueue<TripEdge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.minCost));
        PriorityQueue<TripEdge> resPq = new PriorityQueue<>(Comparator.comparingInt(o -> o.minCost));
        pq.addAll(graph.getOrDefault(src, Collections.emptyList()));

        while(!pq.isEmpty()) {
            TripEdge curr = pq.poll();
            if (curr.dst == dst) {
                resPq.add(new TripEdge(curr));
            }

            if (curr.minCostStops == k) {
                continue;
            }

            for (TripEdge trip : graph.getOrDefault(curr.dst, Collections.emptyList())) {
                trip.minCost = curr.minCost + trip.cost;
                trip.minCostStops = curr.minCostStops + 1;
                pq.add(new TripEdge(trip));
                if (trip.dst == dst) {
                    resPq.add(new TripEdge(trip));
                }
            }
        }

        while (!resPq.isEmpty() && resPq.peek().minCostStops > k) {
            resPq.poll();
        }

        return resPq.isEmpty() ? -1 : resPq.poll().minCost;
    }

    public int findCheapestPriceBFS(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<TripEdge>> graph = new HashMap<>();

        for (int[] f: flights) {
            List<TripEdge> trips = graph.getOrDefault(f[0], new ArrayList<>());
            trips.add(new TripEdge(f[1], f[2], 0));
            graph.put(f[0], trips);
        }

        int minCost = Integer.MAX_VALUE;
        Deque<TripEdge> q = new ArrayDeque<>();
        Deque<TripEdge> tq = new ArrayDeque<>();
        for (TripEdge trip: graph.getOrDefault(src, Collections.emptyList())) {
            q.offerLast(trip);
        }

        for (int s = 0; !q.isEmpty() && s <= k; s++) {
            int stepSize = q.size();

            for (int i = 0; i < stepSize; i++) {
                TripEdge curr = q.pollFirst();

                assert curr != null;
                if (curr.dst == dst) {
                    minCost = Math.min(minCost, curr.minCost);
                    continue;
                }

                for (TripEdge base: graph.getOrDefault(curr.dst, Collections.emptyList())) {
                    base.tmpMinCost = Math.min(base.tmpMinCost, base.cost + curr.minCost);
                    tq.offerLast(base);
                }
            }

            Set<TripEdge> visited = new HashSet<>();
            while (!tq.isEmpty()) {
                TripEdge next = tq.pollFirst();

                if (visited.add(next)) {
                    next.minCost = next.tmpMinCost;
                    next.tmpMinCost = Integer.MAX_VALUE;
                    next.stops = s;
                    q.offerLast(next);
                }
            }

        }

        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    public int findCheapestPriceBF(int V, int[][] flights, int src, int dst, int k) {
        int[] distances = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;

        for (int v = 0; v < V - 1 && v <= k; v++) {
            int[] temp = Arrays.copyOf(distances, distances.length);
            for(int[] f : flights ) {
                if( distances[ f[0] ] != Integer.MAX_VALUE ) {
                    temp[ f[1] ] = Math.min( temp[ f[1] ], distances[ f[0] ] + f[2] );
                }
            }
            distances = temp;
        }
        return distances[dst] == Integer.MAX_VALUE ? -1 : distances[dst];
    }

    public static void main(String[] args) {
        Graph787 s = new Graph787();
        int res = s.findCheapestPriceBFS(17,
        new int[][]{new int[]{0,12,28}, new int[]{5,6,39}, new int[]{8,6,59}, new int[]{13,15,7}, new int[]{13,12,38}, new int[]{10,12,35}, new int[]{15,3,23}, new int[]{7,11,26}, new int[]{9,4,65}, new int[]{10,2,38},
                new int[]{4,7,7}, new int[]{14,15,31}, new int[]{2,12,44}, new int[]{8,10,34}, new int[]{13,6,29}, new int[]{5,14,89}, new int[]{11,16,13}, new int[]{7,3,46}, new int[]{10,15,19}, new int[]{12,4,58},
                new int[]{13,16,11}, new int[]{16,4,76}, new int[]{2,0,12}, new int[]{15,0,22}, new int[]{16,12,13}, new int[]{7,1,29}, new int[]{7,14,100}, new int[]{16,1,14}, new int[]{9,6,74}, new int[]{11,1,73},
                new int[]{2,11,60}, new int[]{10,11,85}, new int[]{2,5,49}, new int[]{3,4,17}, new int[]{4,9,77}, new int[]{16,3,47}, new int[]{15,6,78}, new int[]{14,1,90}, new int[]{10,5,95}, new int[]{1,11,30},
                new int[]{11,0,37}, new int[]{10,4,86}, new int[]{0,8,57}, new int[]{6,14,68}, new int[]{16,8,3}, new int[]{13,0,65}, new int[]{2,13,6}, new int[]{5,13,5}, new int[]{8,11,31}, new int[]{6,10,20},
                new int[]{6,2,33}, new int[]{9,1,3}, new int[]{14,9,58}, new int[]{12,3,19}, new int[]{11,2,74}, new int[]{12,14,48}, new int[]{16,11,100}, new int[]{3,12,38}, new int[]{12,13,77}, new int[]{10,9,99},
                new int[]{15,13,98}, new int[]{15,12,71}, new int[]{1,4,28}, new int[]{7,0,83}, new int[]{3,5,100}, new int[]{8,9,14}, new int[]{15,11,57}, new int[]{3,6,65}, new int[]{1,3,45}, new int[]{14,7,74},
                new int[]{2,10,39}, new int[]{4,8,73}, new int[]{13,5,77}, new int[]{10,0,43}, new int[]{12,9,92}, new int[]{8,2,26}, new int[]{1,7,7}, new int[]{9,12,10}, new int[]{13,11,64}, new int[]{8,13,80},
                new int[]{6,12,74}, new int[]{9,7,35}, new int[]{0,15,48}, new int[]{3,7,87}, new int[]{16,9,42}, new int[]{5,16,64}, new int[]{4,5,65}, new int[]{15,14,70}, new int[]{12,0,13}, new int[]{16,14,52},
                new int[]{3,10,80}, new int[]{14,11,85}, new int[]{15,2,77}, new int[]{4,11,19}, new int[]{2,7,49}, new int[]{10,7,78}, new int[]{14,6,84}, new int[]{13,7,50}, new int[]{11,6,75}, new int[]{5,10,46},
                new int[]{13,8,43}, new int[]{9,10,49}, new int[]{7,12,64}, new int[]{0,10,76}, new int[]{5,9,77}, new int[]{8,3,28}, new int[]{11,9,28}, new int[]{12,16,87}, new int[]{12,6,24}, new int[]{9,15,94},
                new int[]{5,7,77}, new int[]{4,10,18}, new int[]{7,2,11}, new int[]{9,5,41}},
        13,
        4,
        13);
//        int res = s.findCheapestPriceBFS2(5,
//                new int[][]{new int[]{0,1,1}, new int[]{0,2,5}, new int[]{1,2,1}, new int[]{2,3,1}, new int[]{3,4,1}},
//                0,
//                4,
//                2
//        );
//        int res = s.findCheapestPriceBFS2(18,
//                 new int[][]{ new int[]{16,1,81}, new int[]{15,13,47}, new int[]{1,0,24}, new int[]{5,10,21},
//                         new int[]{7,1,72}, new int[]{0,4,88}, new int[]{16,4,39}, new int[]{9,3,25}, new int[]{10,11,28},
//                         new int[]{13,8,93}, new int[]{10,3,62}, new int[]{14,0,38}, new int[]{3,10,58}, new int[]{3,12,46},
//                         new int[]{3,8,2}, new int[]{10,16,27}, new int[]{6,9,90}, new int[]{14,8,6}, new int[]{0,13,31},
//                         new int[]{6,4,65}, new int[]{14,17,29}, new int[]{13,17,64}, new int[]{12,5,26}, new int[]{12,1,9},
//                         new int[]{12,15,79}, new int[]{16,11,79}, new int[]{16,15,17}, new int[]{4,0,21}, new int[]{15,10,75},
//                         new int[]{3,17,23}, new int[]{8,5,55}, new int[]{9,4,19}, new int[]{0,10,83}, new int[]{3,7,17},
//                         new int[]{0,12,31}, new int[]{11,5,34}, new int[]{17,14,98}, new int[]{11,14,85}, new int[]{16,7,48},
//                         new int[]{12,6,86}, new int[]{5,17,72}, new int[]{4,12,5}, new int[]{12,10,23}, new int[]{3,2,31},
//                         new int[]{12,7,5}, new int[]{6,13,30}, new int[]{6,7,88}, new int[]{2,17,88}, new int[]{6,8,98},
//                         new int[]{0,7,69}, new int[]{10,15,13}, new int[]{16,14,24}, new int[]{1,17,24}, new int[]{13,9,82},
//                        new int[]{13,6,67}, new int[]{15,11,72}, new int[]{12,0,83}, new int[]{1,4,37}, new int[]{12,9,36},
//                        new int[]{9,17,81}, new int[]{9,15,62}, new int[]{8,15,71}, new int[]{10,12,25}, new int[]{7,6,23},
//                        new int[]{16,5,76}, new int[]{7,17,4}, new int[]{3,11,82}, new int[]{2,11,71}, new int[]{8,4,11},
//                        new int[]{14,10,51}, new int[]{8,10,51}, new int[]{4,1,57}, new int[]{6,16,68}, new int[]{3,9,100},
//                        new int[]{1,14,26}, new int[]{10,7,14}, new int[]{8,17,24}, new int[]{1,11,10}, new int[]{2,9,85},
//                        new int[]{9,6,49}, new int[]{11,4,95}},
//        7,
//        2,
//        6
//        );

        System.out.println(res);

    }
}
