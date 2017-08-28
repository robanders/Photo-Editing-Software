package dijkstra;

public class Edge {

	private long idNum;
	private String label;
	private double weight;
	private Vertex sourceVertex;
	private Vertex destinationVertex;
	
	public Edge(long idNum, String label, double weight, Vertex sourceVertex, Vertex destinationVertex){
		this.idNum = idNum;
		this.label = label;
		this.weight = weight;
		this.sourceVertex = sourceVertex;
		this.destinationVertex = destinationVertex;
	}
	
	public long getidNum(){
		return idNum;
	}
	
	public String getlabel(){
		return label;
	}
	
	public double getweight(){
		return weight;
	}
	
	public Vertex getsourceVertex(){
		return sourceVertex;
	}
	
	public Vertex getdestinationVertex(){
		return destinationVertex;
	}
}
