package Maquinas;

import Extra.Matriz;
import Monitor.GestorDeMonitor;

public class Maquina_2 extends Maquina {
	private Matriz secuencia;
	public Maquina_2(GestorDeMonitor gdm){
		super(gdm);
		secuencia = new Matriz(2,20).productoPorEscalar(0);
		definirTransiciones();
		definirSecuencia();
	}
	
	
	public void definirTransiciones(){
		for(int i=0;i<20;i++){
			if(i!=11 || i!=16){
				transiciones.setDato(0, i, 0);
			}else{
				transiciones.setDato(0, i, 1);
			}
		}
	}
	
	public void definirSecuencia(){
		secuencia.setDato(0,11, 1);
		secuencia.setDato(0, 0, 1);
		
		secuencia.setDato(1, 17, 1);
		secuencia.setDato(1, 16, 1);
	}
	
}
