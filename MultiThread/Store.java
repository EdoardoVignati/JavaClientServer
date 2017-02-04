public class Store{

	int items;
		
	public Store(){

		items=0;
	}

	public void add(){
		items++;
	}
	
	public void remove(){
		items--;
	}

	public int getStore(){
		return items;
	}

}
