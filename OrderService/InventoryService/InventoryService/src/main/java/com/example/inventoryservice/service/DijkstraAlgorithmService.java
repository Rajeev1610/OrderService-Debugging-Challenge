// DijkstraAlgorithmService.java
package com.example.inventoryservice.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DijkstraAlgorithmService {

    public Map<String, Object> findShortestPath(List<String> nodes, List<int[]> edges, String start, String end) {
        Map<String, List<int[]>> graph = buildGraph(nodes, edges);
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (String node : nodes) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.add(new int[]{nodes.indexOf(start), 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            String currentNode = nodes.get(current[0]);

            if (currentNode.equals(end)) break;

            if (!graph.containsKey(currentNode)) continue;

            for (int[] neighbor : graph.get(currentNode)) {
                String neighborNode = nodes.get(neighbor[0]);
                int newDist = distances.get(currentNode) + neighbor[1];

                if (newDist < distances.get(neighborNode)) {
                    distances.put(neighborNode, newDist);
                    previous.put(neighborNode, currentNode);
                    pq.add(new int[]{neighbor[0], newDist});
                }
            }
        }

        return reconstructPath(nodes, distances, previous, start, end);
    }

    private Map<String, List<int[]>> buildGraph(List<String> nodes, List<int[]> edges) {
        Map<String, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges) {
            String from = nodes.get(edge[0]);
            System.out.println("from:"+from);
            String to = nodes.get(edge[1]);
            System.out.println("toValue:"+to);
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new int[]{nodes.indexOf(to), edge[1]});
        }
        return graph;
    }

    private Map<String, Object> reconstructPath(List<String> nodes, Map<String, Integer> distances, Map<String, String> previous, String start, String end) {
        List<String> path = new ArrayList<>();
        String current = end;

        while (current != null && !current.equals(start)) {
            path.add(0, current);
            current = previous.get(current);
        }

        if (current == null) {
            return Map.of("error", "Path not found");
        }

        path.add(0, start);
        return Map.of("path", path, "distance", distances.get(end));
    }
}