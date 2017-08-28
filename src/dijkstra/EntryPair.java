package dijkstra;

public class EntryPair implements EntryPair_Interface {
  public String value;
  public double priority;

  public EntryPair(String aValue, double aPriority) {
    value = aValue;
    priority = aPriority;
  }

  public String getValue() { return value; }
  public double getPriority() { return priority; }
}