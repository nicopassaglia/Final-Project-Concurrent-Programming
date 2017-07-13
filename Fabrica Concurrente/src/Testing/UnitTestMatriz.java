package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import Extra.Matriz;

public class UnitTestMatriz {

	
	
	@Test
	public void SumaMatrices(){
		Matriz A = new Matriz(1,1);
		Matriz B = new Matriz(1,1);
		
		A.setDato(0, 0, 1);
		B.setDato(0, 0, 2);
		
		Matriz C = new Matriz(1,1);
		C.setDato(0, 0, 3);
		
		assertEquals(C.toString(),A.plus(B).toString());
	}
	@Test
	public void multAndMatrices(){
		Matriz A = new Matriz(1,1);
		Matriz B = new Matriz(1,1);
		
		A.setDato(0, 0, 10);
		B.setDato(0, 0, 2);
		
		Matriz C = new Matriz(1,1);
		C.setDato(0, 0, 20);
		
		assertEquals(C.toString(),A.multAnd(B).toString());
	}
	
	
	@Test
	public void MenorNumMayorCero(){
		
		
		Matriz A = new Matriz(3,1);
		A.setDato(0, 0, 5);
		A.setDato(1, 0, -2);
		A.setDato(2, 0, 10);
		
		
		assertEquals(0,A.menorNumMayorCero());
	}
	
	@Test
	public void transpose(){
		
		Matriz A = new Matriz(3,1);
		
		A.setDato(0, 0, 5);
		A.setDato(1, 0, -2);
		A.setDato(2, 0, 10);
		
		Matriz B = new Matriz(1,3);
		B.setDato(0, 0, 5);
		B.setDato(0, 1, -2);
		B.setDato(0, 2, 10);
		assertEquals(B.toString(),A.transpose().toString());
	}
	
}
