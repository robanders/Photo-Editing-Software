package dijkstra;

import java.io.IOException;

public class ShortestPathMain{

	public static void main(String[] args) throws IOException {
		if(args.length != 3){
			System.out.println("enter in arguments as source destination filename");
			return;
		}
		  String input1 = args[0];
		  String input2 = args[1];
		  String input3 = args[2];
		  //String input1 = "61";
		  //String input2 = "151";
		  //String input3 = "C:\\Users\\robertan\\Documents\\Junior Year\\COMP550\\connectivity_matrix.csv"; 
		  //C:\Users\robertan\Documents\Junior Year\COMP550\connectivity_matrix.csv
		  try {
			 long in1 = Long.parseLong(input1);
 
		  } catch (Exception e){
			  System.out.println("invalid source node");
			  return;
		  }
		  try {
		     long in2 = Long.parseLong(input2);
	 
		  } catch (Exception e){
			  System.out.println("invalid destination node");
			  return;
		  }
	      DiGraph d = new DiGraph();
	      try {
	    	  d.loadGraph(input3);
	      } catch (IOException e) {
	    	  System.out.println("error reading file: " + e);
	      }
	      
	      //d.print();
	      
	      if(d.shortestPath(input1, input2)){
	    	  
	      } else {
	    	  System.out.println("no path");
	      }
	}

}
