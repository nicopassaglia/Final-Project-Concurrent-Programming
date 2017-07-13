package Monitor;
import java.util.ArrayList;

import Extra.Matriz;

public class RDP {
	
	public int[][] marcado;
	public int[][] incidencia;
	private Matriz incidenciaM;
	private Matriz marcadoM;
	
	public RDP(int[][] marcadoInicial, int[][] incidencia){
		this.marcado = marcadoInicial;
		this.incidencia = incidencia;
		
		incidenciaM = new Matriz(this.incidencia);
		this.marcadoM = new Matriz(this.marcado);
	}
	
	public Matriz sensibilizadas(){
		
		Matriz marcadoT = marcadoM.transpose();
	
		Matriz sensibilizadas = new Matriz(incidenciaM.getColCount(),1);
		Matriz incidenciaNueva = incidenciaM.productoPorEscalar(-1);
		
		for(int i=0;i<incidenciaM.getColCount();i++){
			int transicion = 1;
			for(int j=0;j<marcadoT.getFilCount();j++){
				if(incidenciaNueva.getVal(j, i) > 0){
					if(marcadoT.getVal(j, 0)>=incidenciaNueva.getVal(j, i)){
						continue;
					}else{
						transicion = 0;
						break;
					}
				}
			}
			sensibilizadas.setDato(i, 0, transicion);
		}
		
	
		
		return sensibilizadas;
	}
	
	public boolean disparar(int transicion){
		
		Matriz sensi;
		sensi = sensibilizadas();
		if(sensi.getVal(transicion, 0) == 1){
			nuevoMarcado(transicion);
			return true;
		}
		else
			return false;
		
		
	}
	
	public int[][] getMarcado(){
		return this.marcado;
	}
	
	public int[][] getIncidencia(){
		return this.incidencia;
	}
	
	public void nuevoMarcado(int transicion){
		Matriz marcadoT = marcadoM.transpose();
		Matriz vectorDisparo = new Matriz(incidenciaM.getColCount(),1);
		
		for(int i=0;i<incidenciaM.getColCount();i++){
			if(i!=transicion)
				vectorDisparo.setDato(i,0,0 );
			else{
				vectorDisparo.setDato(i,0,1 );
			}
		}
		
		Matriz temporal = incidenciaM.mult(vectorDisparo);
	
		this.marcadoM = marcadoT.plus(temporal).transpose();
		
		
	}
	
	public Matriz getMarcadoM(){
		return this.marcadoM;
	}
	
	
}
