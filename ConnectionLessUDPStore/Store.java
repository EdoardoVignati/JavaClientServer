public class Store{

	int item; 

	public Store(){
		item = 0;
	}

	public synchronized void add(int n){
		if(n>0)
		item = item +n;
	}


	public synchronized void remove(int n){
		if(n>0)
		item = item - n;
	}

	public int getStore(){
		return item;
	}

}
