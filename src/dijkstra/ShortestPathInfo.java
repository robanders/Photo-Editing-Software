package dijkstra;

public class ShortestPathInfo {

  private String dest;
  private double totalWeight;
  
  public ShortestPathInfo(String dest, double totalWeight){
    this.dest=dest;
    this.totalWeight=totalWeight;
  }

  public String getDest() {
    return dest;
  }

  public double getTotalWeight() {
    return totalWeight;
  }
  
  public String toString(){
    return "dest: "+dest+"\ttotalWeight: "+totalWeight;
  }
}