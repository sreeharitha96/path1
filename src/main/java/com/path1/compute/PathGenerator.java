package com.path1.compute;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class PathGenerator {

  public static List<List<Vertex>> generatePaths(Input input) {
   
    List<Vertex> vertices = input.getVertices();

    int clusters = input.getNumOfCabs();

    int perCar = input.getNumPerCar();

    Map<String, List<Vertex>> group = cluster(vertices, clusters, createCentroids(clusters, vertices), perCar);

    Map<String, List<Vertex>> group1 = cluster(vertices, clusters, reiterate(group, vertices), perCar);

    int count = 0;
    while(!group1.equals(group) && count < 1000) {
      group = group1;
      group1 = cluster(vertices, clusters, reiterate(group, vertices), perCar);
      count++;
    }

    Vertex s = input.getSource();

    return findAllPaths(group1, s);
    
  }
  
  private static Map<String, List<Vertex>> cluster(List<Vertex> vertices, int k, List<Vertex> centroids, int perCar) {
    PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a,b)-> Double.compare(a.distance, b.distance));
    Set<String> grouped = new HashSet<>();
    Map<String, List<Vertex>> map = new HashMap<>();
    for(Vertex centroid : centroids) {
      map.put(centroid.name, new ArrayList<>());
      for(Vertex vertex : vertices) {
        pq.add(new Edge(centroid, vertex, getDistance(centroid.x, centroid.y, vertex.x, vertex.y)));
      }
    }

    while(!pq.isEmpty()) {
      if(grouped.size() == vertices.size()) {
        break;
      }
      Edge current = pq.poll();
      if(!grouped.contains(current.destination.name) && map.get(current.source.name).size() < perCar) {
        map.get(current.source.name).add(current.destination);
        grouped.add(current.destination.name);
      }
    }

    return map;

  }
  
  private static double getDistance(double x1, double y1, double x2, double y2) {
    return Point2D.distance(x1, y1, x2, y2);
  }
  
  private static List<Vertex> createCentroids(int clusters, List<Vertex> vertices) {
    List<Vertex> centroids = new ArrayList<>();

    List<Double> xCoords = new ArrayList<>();
    List<Double> yCoords = new ArrayList<>();

    for(Vertex v : vertices) {
      xCoords.add(v.x);
      yCoords.add(v.y);
    }

    Collections.sort(xCoords);
    Collections.sort(yCoords);

    int n = xCoords.size();

    int count = 1;

    int factor = n-1/clusters-1;

    while(count <= clusters) {
      int current = 0;
      centroids.add(new Vertex("v"+count++, xCoords.get(current), yCoords.get(current)));
      current += factor;
    }

    return centroids;
  }
  
  private static List<Vertex> reiterate(Map<String, List<Vertex>> group, List<Vertex> vertices) {
    List<Vertex> res = new ArrayList<>();
    Set<String> centroids = group.keySet();

    List<String> list = new ArrayList<String>(centroids);

    double sumx = 0;
    double sumy = 0;

    for(int i=0;i<centroids.size();i++) {
      List<Vertex> current = group.get(list.get(i));
      int n = current.size();
      for(int j=0;j<n;j++){
        sumx+=current.get(j).x;
        sumy+=current.get(j).y;
      }
      res.add(new Vertex(list.get(i), sumx/n, sumy/n));
    }

    return res;
  }
  
  private static List<List<Vertex>> findAllPaths(Map<String, List<Vertex>> group1, Vertex startingPoint) {
    List<Integer> tour = new ArrayList<>();
    List<List<Vertex>> allPaths = new ArrayList<>();
    double minCost = Double.POSITIVE_INFINITY;
    for(List<Vertex> list : group1.values()) {
      double[][] distance = new double[list.size() + 1][list.size() + 1];
      list.add(0, startingPoint);

      for(int i =0; i < list.size() -1; i++) {
        for(int j =i; j < list.size(); j++) {
          distance[i][j] = getDistance(list.get(i).x, list.get(i).y, list.get(j).x, list.get(j).y);
          distance[j][i] = distance[i][j];
        }
      }

      //start
      int n = distance.length;
      int finalState = (int) Math.pow(2, n) - 1;
      double[][] dp = new double[n][(int) Math.pow(2, n)];

      int start = 0;
      
      for (int end = 0; end < n; end++) {
        if (end == start) {
          continue;
        }
        dp[end][(1 << start) | (1 << end)] = distance[start][end];
      }

      for (int r = 3; r <= n; r++) {
        for (int subset : perumatations(r, n)) {
          if (((1 << start) & subset) == 0) {
            continue;
          }
          for (int next = 0; next < n; next++) {
            if (next == start || ((1 << next) & subset) == 0) {
              continue;
            }
            int subsetWithoutNext = subset ^ (1 << next);
            double min = Double.POSITIVE_INFINITY;
            for (int end = 0; end < n; end++) {
              if (end == start || end == next || ((1 << end) & subset) == 0) {
                continue;
              }
              double dis = dp[end][subsetWithoutNext] + distance[end][next];
              if (dis < min) {
                min = dis;
              }
            }
            dp[next][subset] = min;
          }
        }
      }

      for (int i = 0; i < n; i++) {
        if (i == start) continue;
        double tourCost = dp[i][finalState] + distance[i][start];
        if (tourCost < minCost) {
          minCost = tourCost;
        }
      }

      int lastIndex = start;
      int state = finalState;
      tour.add(start);

      for (int i = 1; i < n; i++) {

        int index = -1;
        for (int j = 0; j < n; j++) {
          if (j == start || ((1 << j) & state) == 0) {
            continue;
          }
          if (index == -1) index = j;
          double prevDist = dp[index][state] + distance[index][lastIndex];
          double newDist  = dp[j][state] + distance[j][lastIndex];
          if (newDist < prevDist) {
            index = j;
          }
        }

        tour.add(index);
        state = state ^ (1 << index);
        lastIndex = index;
      }

      tour.add(start);
      Collections.reverse(tour);

      List<Vertex> path = new ArrayList<>();

      for(int i=0; i< tour.size(); i++) {
        path.add(list.get(tour.get(i)));
      }

      allPaths.add(path);
      tour = new ArrayList<>();

    }

    return allPaths;
  }
  
  private static List<Integer> perumatations(int n1, int n2) {
    List<Integer> subsets = new ArrayList<>();
    combinations(0, 0, n1, n2, subsets);
    return subsets;
  }

  private static void combinations(int set, int current, int r, int n, List<Integer> subsets) {

    int eleLeft = n - current;
    if (eleLeft < r) {
        return;
    }

    if (r != 0) {
      for (int i = current; i < n; i++) {
        set |= 1 << i;
        combinations(set, i + 1, r - 1, n, subsets);
        set &= ~(1 << i);
      }
    } else {
      subsets.add(set);
    }
  }
  
}
