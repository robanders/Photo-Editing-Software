package dijkstra;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	private static final int arraySize = 10000; 
												
	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); 
		size = 0;								
	}

	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		size = size + 1;
		array[size] = entry;
		this.bubble(size);
		
	}
	
	public void bubble(int child){
		int parent = child / 2;
		if(parent == 0){
			return;
		}
		EntryPair temp = null;
		if(array[child].priority < array[parent].priority){
			//System.out.println("child priority: " + array[child].priority + " parent priority: " + array[parent].priority + " swapping");
			temp = array[parent];
			array[parent] = array[child];
			array[child] = temp;
			this.bubble(parent);
		}
	}

	@Override
	public void delMin() {
		if(size == 0){
			//do nothing
		} else if (size == 1){
			size = 0;
		} else {
			array[1] = array[size];
			size = size - 1;
			this.bubbleDown(1);
		}
	}
	
	public void bubbleDown(int parent){
		int leftChild = 2*parent;
		int rightChild = (2*parent) + 1;
		EntryPair temp = null;
		if(leftChild > size){
			//do nothing
		} else if (rightChild > size){
			if(array[leftChild].priority < array[parent].priority){
				temp = array[parent];
				array[parent] = array[leftChild];
				array[leftChild] = temp;
				this.bubbleDown(leftChild);
			}
		} else {
			if(array[leftChild].priority < array[rightChild].priority){
				if(array[leftChild].priority < array[parent].priority){
					temp = array[parent];
					array[parent] = array[leftChild];
					array[leftChild] = temp;
					this.bubbleDown(leftChild);
				}
			} else {
				if(array[rightChild].priority < array[parent].priority){
					temp = array[parent];
					array[parent] = array[rightChild];
					array[rightChild] = temp;
					this.bubbleDown(rightChild);
				}
			}
		}
	}

	@Override
	public EntryPair getMin() {
		if(size == 0){
			return null;
		} else {
			return array[1];
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries){
		size = entries.length;
		for(int i = 1; i <= size; i++){
			array[i] = entries[i-1];
		}
		int lastParent = size / 2;
		for(int i = lastParent; i >= 1; i--){
			this.bubbleDown(i);
		}
	}
	
}