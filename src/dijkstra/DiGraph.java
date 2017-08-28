package dijkstra;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class DiGraph implements DiGraph_Interface {

	public long numNodes;
	public long numEdges;
	public List<Vertex> vertexList = new LinkedList<Vertex>();
	public HashMap<String, Vertex> labelMap = new HashMap<String, Vertex>();
	public HashMap<Long, Vertex> idNumMap = new HashMap<Long, Vertex>();
	public HashMap<Long, Edge> idEdgeMap = new HashMap<Long, Edge>();

	public DiGraph() {
		numNodes = 0;
		numEdges = 0;
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if(idNum < 0 || label == null){
			return false;
		}
		Vertex vertex = labelMap.get(label);
		if(vertex != null){
			return false;
		}
		vertex = idNumMap.get(idNum);
		if(vertex != null){
			return false;
		}
		Vertex newVertex = new Vertex(idNum, label);
		vertexList.add(newVertex);
		labelMap.put(label, newVertex);
		idNumMap.put(idNum, newVertex);
		numNodes++;
		return true;
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, double weight, String eLabel) {
		Edge edge;
		if(idNum < 0){
			return false;
		}
		Vertex sVertex = labelMap.get(sLabel);
		if(sVertex == null){
			return false;
		}
		Vertex dVertex = labelMap.get(dLabel);
		if(dVertex == null){
			return false;
		}
		edge = idEdgeMap.get(idNum);
		if(edge != null){
			return false;
		}
		//check if there is already an edge between sLabel and dLabel
		Iterator<Edge> edgeIterator = sVertex.edgeList.iterator();
		while(edgeIterator.hasNext()){
			edge = edgeIterator.next();
			if(edge.getdestinationVertex() == dVertex){
				return false;
			}
		}
		
		Edge newEdge = new Edge(idNum, eLabel, weight, sVertex, dVertex);
		idEdgeMap.put(idNum, newEdge);
		numEdges++;
		sVertex.addEdge(newEdge);
		dVertex.incrementInDegree();
		dVertex.addInEdge(newEdge);
		return true;
	}

	@Override
	public long numNodes() {
		return numNodes;
	}

	@Override
	public long numEdges() {
		return numEdges;
	}
	
	public void print(){
		Vertex vertex;
		Edge edge;
		Iterator<Vertex> vertexIterator = vertexList.iterator();
		while(vertexIterator.hasNext()){
			vertex = vertexIterator.next();
			System.out.println("(" + vertex.getidNum() + ") " + vertex.getlabel());
			Iterator<Edge> edgeIterator = vertex.edgeList.iterator();
			while(edgeIterator.hasNext()){
				edge = edgeIterator.next();
				System.out.println("    (" + edge.getidNum() + ")" + "--" + edge.getweight() + "," + edge.getlabel() + "-->" + edge.getdestinationVertex().getlabel());
			}
		}
	}

	@Override
	public boolean shortestPath(String label, String dlabel) {
		Vertex vertex = labelMap.get(label);
		Vertex dvertex = labelMap.get(dlabel);
		EntryPair entryPair;
		Iterator<Edge> edgeIterator;
		Edge edge;
		MinBinHeap pathQueue = new MinBinHeap();
		
		if(vertex == null){
			return false;
		}
		if(dvertex == null){
			return false;
		}
		vertex.sp = 0;
		vertex.prevVertex = null;
		entryPair = new EntryPair(label, 0);
		pathQueue.insert(entryPair);
		
		while(pathQueue.size() > 0){ //while items remain in priority queue
			entryPair = pathQueue.getMin(); //retrieves min from heap
			pathQueue.delMin();
			vertex = labelMap.get(entryPair.value); //retrieves vertex label from hash
			if(vertex.processed == false){
				vertex.processed = true;
				edgeIterator = vertex.edgeList.iterator();
				while(edgeIterator.hasNext()){ //iterates through list of edges based on vertex
					edge = edgeIterator.next();
					if(edge.getdestinationVertex().sp > edge.getweight() + edge.getsourceVertex().sp){
						edge.getdestinationVertex().prevVertex = edge.getsourceVertex();
						edge.getdestinationVertex().sp = edge.getweight() + edge.getsourceVertex().sp;
						entryPair = new EntryPair(edge.getdestinationVertex().getlabel(), edge.getdestinationVertex().sp); //creates pair to place back in queue
						pathQueue.insert(entryPair);
					}
				}
			}	
		}
		String path = "";
		Vertex temp_node = dvertex;
		while(temp_node.processed){
			path = temp_node.getlabel() + " " + path; //after node is processed, it is added to the path
			if(temp_node.prevVertex == null){
				break;
			}
			temp_node = temp_node.prevVertex;
		}
		System.out.println("distance = " + Double.toString(dvertex.sp));
		System.out.println("route = [ " + path + "]");
		return true;
	}

	@Override
	public boolean loadGraph(String filename) throws IOException {
		Scanner scanner = new Scanner(new File(filename));
		Scanner dataScanner = null;
		scanner.useDelimiter(",");
		int i = 1;
		int j;
		boolean firsti = true;
		while(scanner.hasNextLine()){
			dataScanner = new Scanner(scanner.nextLine());
			dataScanner.useDelimiter(",");
			j = 1;
			while(dataScanner.hasNext()){
				String data = dataScanner.next();
				double edgeWeight = Double.parseDouble(data);
				if(edgeWeight != 0.0){
					if(firsti){
						this.addNode(i, Integer.toString(i));
						firsti = false;
					}
					this.addNode(i, Integer.toString(i));
					this.addNode(j, Integer.toString(j));
					this.addEdge(numEdges, Integer.toString(i), Integer.toString(j), edgeWeight, "");
				}
				j = j + 1;
			}
		i = i + 1;
		firsti = true;
		}
		return false;
	}
}