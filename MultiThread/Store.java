public class Store{

	int items;
		
	public Store(){

		items=0;
	}

	public synchronized void add(){
		items++;
	}
	
	public synchronized void remove(){
		items--;
	}

	public int getStore(){
		return items;
	}

}
