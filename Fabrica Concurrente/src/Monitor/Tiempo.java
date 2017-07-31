package Monitor;

import java.util.ArrayList;
import java.util.Date;

import Extra.Matriz;

public class Tiempo {
	
	private ArrayList<Date> alfas = new ArrayList<>();
	private ArrayList<Date> betas = new ArrayList<>();
	private ArrayList<Date> sensibilizacion = new ArrayList<>();
	private Matriz esperando;
	private GestorDeMonitor gdm;
	
	public Tiempo(int cantTransiciones, GestorDeMonitor gdm){
		esperando = new Matriz(cantTransiciones,1);
		esperando.productoPorEscalar(0);
		this.gdm = gdm;
		
	
	}
	
	public int estaSensibilizado(int transicion){
		long alfa = alfas.get(transicion).getTime();
		long ahora = System.currentTimeMillis();
		long timeStamp = sensibilizacion.get(transicion).getTime();
		if(testVentanaTiempo(transicion)){
			if(estaEsperando(transicion)){
				return 0;
			}else{
				//setNuevoTimeStamp(null); //PASAR MATRIZ..NULL ESTA MAL
				return 1;
				
			}
		}else{
			
			if(antesVentana(transicion)){
				gdm.releaseMutex();
				setEsperando(transicion);
				try {
					System.out.println("Estoy antes de la ventana..tengo sue�o me voy a dormir zzzz");
					wait(timeStamp+alfa-ahora);
					resetEsperando(transicion);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 2; ///REVISAR ESTO..CREO QUE ES RESUME AND EXIT.
			}else{
				return 0;
			}
		}
	}
	
	public void setNow(Date date){
		date.setTime(System.currentTimeMillis());
	}
	
	public void setNuevoTimeStamp(Matriz nuevasSensi){
		
		for(int i=0;i<nuevasSensi.getFilCount();i++){
			if(nuevasSensi.getVal(i, 0) == 1){
				setNow(sensibilizacion.get(i));
			}
		}
	}
	
	public boolean testVentanaTiempo(int transicion){
		long ahora = System.currentTimeMillis();
		long timeStamp = sensibilizacion.get(transicion).getTime();
		
		if(ahora - timeStamp < betas.get(transicion).getTime() && ahora-timeStamp > alfas.get(transicion).getTime())
			return true;
		
		return false;
	}
	
	public boolean antesVentana(int transicion){
		long ahora = System.currentTimeMillis();
		long timeStamp = sensibilizacion.get(transicion).getTime();
		
		if(ahora - timeStamp < alfas.get(transicion).getTime()){
			setEsperando(transicion);
			return true;
		}
			
		
		return false;
	}
	
	public void setEsperando(int transicion){
		esperando.setDato(0, transicion, 1);
	}
	
	public boolean estaEsperando(int transicion){
		if(esperando.getVal(0, transicion) == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public void resetEsperando(int transicion){
		esperando.setDato(0, transicion, 0);
	}

}