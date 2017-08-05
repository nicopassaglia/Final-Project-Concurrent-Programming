package Testing;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Extra.LeerArchivo;
import Extra.Matriz;
public class UnitTestleerTxt {

	@Test
	public void leerTxtFile(){
		LeerArchivo la = new LeerArchivo();
	
		List<Matriz> A = la.leerTxtFile("hilos");
		
		List<Matriz> matrices = new ArrayList<>();
		Matriz uno = new Matriz(1,4);
		uno.setDato(0, 0, 13);
		uno.setDato(0, 1, 11);
		uno.setDato(0, 2, 0);
		uno.setDato(0, 3, 12);
		
		//assertEquals(A.get(0).getVal(0, 3),uno.getVal(0, 3));
		assertArrayEquals(A.get(0).getDato(),uno.getDato());
		
	}
}
