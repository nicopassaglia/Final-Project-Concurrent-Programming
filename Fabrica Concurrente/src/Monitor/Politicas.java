package Monitor;
import java.util.ArrayList;

import Extra.Matriz;



public class Politicas {
	private ArrayList<Integer> fifo = new ArrayList<>();
	
	public Politicas(){


	}

	public int cual(Matriz and){
		for(int i=0;i<and.getFilCount();i++){

			if(and.getVal(i, 0)==1){
				return i;

			}else{

			}
		}
		return -1;
	}
	
	public int cualFifo(Matriz and){
		int a;
		for(int i =0;i<fifo.size();i++){
			
			if(and.getVal(fifo.get(i), 0)==1){
				a = fifo.get(i);
				fifo.remove(i);
				return a;
			}
		}
		
		
		return -1;
	}
	
	public void insertFifo(int transicion){
		fifo.add(transicion);
	}
}
