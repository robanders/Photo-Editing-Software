package dijkstra;

import java.io.IOException;

public interface DiGraph_Interface {
  boolean addNode(long idNum, String label);
  boolean addEdge(long idNum, String sLabel, String dLabel, double weight, String eLabel);
  long numNodes();
  long numEdges();
  boolean loadGraph(String filename) throws IOException;
  boolean shortestPath(String label, String dlabel);
}