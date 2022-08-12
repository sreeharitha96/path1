package com.path1.compute;

class Edge {
  Vertex source;
  Vertex destination;
  double distance;

  public Edge(Vertex source, Vertex destination, double distance) {
    this.source = source;
    this.destination = destination;
    this.distance = distance;
  }
}
