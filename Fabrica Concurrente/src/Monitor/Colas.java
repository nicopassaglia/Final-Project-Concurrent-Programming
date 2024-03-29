package Monitor;
import java.util.ArrayList;
import java.util.Vector;

import Extra.Matriz;

public class Colas {

	private Cola[] arregloColas;
	private Matriz arregloEstan;
	
	public Colas(int transiciones){
		arregloColas = new Cola[transiciones];
		this.arregloEstan = new Matriz(transiciones,1);
		for(int i=0; i<transiciones; i++){
			arregloColas[i]=new Cola("Comun");
			arregloEstan.setDato(i, 0, 0);
		}
	}
	
	public void acquire(int transicion, Thread proceso){
		arregloEstan.setDato(transicion, 0, 1);
		arregloColas[transicion].meterEnCola(proceso);
		
	
		//arregloEstan[transicion]=true;
	}
	
	public void release(int transicion){
		
		Cola ColaLiberadora;
		ColaLiberadora = arregloColas[transicion];
		//System.out.println("TRANSICION:" +transicion);
		Runnable r = ColaLiberadora.obtenerProceso();
		synchronized(r){
		r.notify();
		}
		if(arregloColas[transicion].isEmpty())
			arregloEstan.setDato(transicion, 0, 0);
	}
	
	public Matriz quienesEstan(){
		
		return arregloEstan;
	}
	
}