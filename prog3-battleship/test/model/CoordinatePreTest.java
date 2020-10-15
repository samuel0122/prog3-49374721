package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/* Para realizar los test se sugiere usar métodos de la librería de junit como:
 * assertEquals, assertNotEquals, assertSame; assertNotSame, assertTrue; assertFalse
 */
public class CoordinatePreTest {
	
    List vcoordinates = new ArrayList<int[]>();
    int []vcoor= {0,0,-70,-2,20}; //Para crear Coordinates
    final int DIM = vcoor.length;
    List<Coordinate> lcoor;
    
	@Before
	public void setUp() throws Exception {
		lcoor = new ArrayList<Coordinate>();
		//Se crean las Coordinates (0,0),(0,-70), (-70,-2),(-2,20);
		for (int i=0; i<DIM-1; i++) {
			lcoor.add(new Coordinate(vcoor[i],vcoor[i+1]));
		}
		
	}

	/* Se comprueba que cuando dos Coordinate son iguales, el resultado del hash ha 
	 * de ser el mismo. 
	 * Si los Coordinate son distintos el hashCode puede ser igual o no.
	 */
	@Test
	public void testHashCode() {
		Coordinate c1 = lcoor.get(2);
		Coordinate c2 = new Coordinate(c1);
		
		assertEquals (c1,c2);
		//
		System.out.println("c1: "+c1.hashCode() + "; c2: "+c2.hashCode());
		assertEquals (c1.hashCode(), c2.hashCode());
	}

	/* Se comprueba que el Constructor funciona bien. Para ello se analiza que las 
	 * las componentes  '0' y '1' de cada Coordinate creada en el setUp() son las 
	 * correctas.
	 */
	@Test
	public void testCoordinateConstructor() {
		int j=0;
		for (int i=0; i<DIM-1; i++) {			
			assertEquals("x",vcoor[i],lcoor.get(j).get(0));
			assertEquals("y",vcoor[i+1],lcoor.get(j).get(1));
			j++;
		}
	}

	/* Comprobar que el constructor de copia crea una nueva Coordinate con
	 * los mismos valores que las componentes respectivas del Coordinate copiado.
	 * Y eso se hace para cada Coordinate creada en setUp();
	 */
	@Test
	public void testCoordinateConstructorCopy() {
		//Coordinate c1 = 
		//fail("Realiza el tes del constructor copia");
	}

	/* Se comprueba que el método get(int) para cada componente de una Coordinate 
	 * funciona correctamente.
	 * Modificar los valores de las componentes de la Coordinate anterior con el 
	 * método set(int, int) y comprobar con get(int) que los valores de sus 
	 * componentes han cambiado a dichos valores.
	 */
	@Test
	public void testGetSet() {
		Coordinate c = lcoor.get(2);
		assertEquals("x==-70", -70, c.get(0));
		assertEquals("x==-2", -2, c.get(1));
		
		//fail ("Modifica los valores de los componentes de 'c' y comprueba que se han modificado");
	}

	/* Se suman las Coordinate creadas en el setUp() y comprueba, conforme se van 
	 * sumando, que los valores de sus componentes van tomando los valores correctos 
	 * y que el Coordinate que devuelve no es el mismo que el Coordinate que invoca 
	 * al método.
	 */
	@Test  //{0,0,-70,-2,20}
	public void testAdd() {
		Coordinate caux1 = lcoor.get(0);
		Coordinate caux2;
		
		int sumx = caux1.get(0);
		int sumy = caux1.get(1);
		for (int i=0; i<DIM-2; i++) {	
		   caux2 = caux1;
		   caux1 = caux1.add(lcoor.get(i+1));	  
		   sumx += (vcoor[i+1]);
		   sumy += (vcoor[i+2]);
		   
		   /*Usa aquí los métodos de junit adecuados para comprobar:
		    * - que sumx y sumy son iguales a los componentes '0' y '1' 
		    *   respectivamente de caux1.
		    * - que el Coordinate que devuelve 'add' no es el mismo que
	        *   el Coordinate que invocó al método.
		    */
		   
		   //fail ("Realiza las comprobaciones sugeridas anteriormente");
		}
	}

	/* Resta las Coordinate creadas en el setUp() y comprueba, conforme se van restando, 
	 * que los valores de sus componentes van tomando los valores correctos y que el 
	 * Coordinate que devuelve no es el mismo que el Coordinate que invoca 
	 * al método.
	 */
	@Test
	public void testSubtract() {
		Coordinate m=new Coordinate(0,0);
		
		//fail("Realiza la resta de los Coordinate propuesta");
	}

	
	/* Se comprueba, para el método toString(), que las Coordinate creadas en el setUp() 
	 * tienen el formato correcto.
	 */
	@Test
	public void testToString() {
		assertEquals ("(0, 0)",lcoor.get(0).toString());
		assertEquals ("(0, -70)",lcoor.get(1).toString());
		assertEquals ("(-70, -2)",lcoor.get(2).toString());
		assertEquals ("(-2, 20)",lcoor.get(3).toString());
	}

	/* Se toma una Coordinate y se comprueba todas las posibles condiciones bajo 
	 * las cuales, nuestra función equals() devuelve true o false
	 */
	@Test
	public void testEqualsObject() {
		Object obj = new String("(0, 0)");
		Coordinate c = lcoor.get(2);
		// equals() devuelve falso cuando le paso null
		assertFalse(c.equals(null));
		//System.out.println("Comparacion con null (f): "+c.equals(null));
		// equals() devuelve falso cuando le paso un objeto que no es de tipo Coordinate
		assertFalse(c.equals(obj));
		//System.out.println("Comparacion con un objeto que no es Coordinate (f): "+c.equals(obj));
		
		/* Sigue comprobando lo siguiente:
		 *  1. equals() devuelve false cuando el valor de alguno de los componentes de los 
		 *     Coordinate es distinto
		 *  2. equals() devuelve true cuando se compara un objeto Coordinate consigo mismo
		 *  3. equals() devuelve true cuando comparo dos objetos Coordinate distintos
		 *     y los valores de sus componentes respectivos son iguales.
		 */
		Coordinate cd = new Coordinate (c);
		cd=c.add(cd);
	/*	System.out.println("cd: "+cd + "\n c: "+c);
		System.out.println("Comparacion con dos Coordinate con diferentes valores (f): "+c.equals(cd));
		System.out.println("Comparacion consigo mismo (t): "+c.equals(c));
		cd=cd.subtract(c);
		System.out.println("cd: "+cd + "\n c: "+c);
		System.out.println("Comparacion con dos Coordinate diferentes pero mismos valores (t): "+c.equals(cd));
		fail ("Completa el test equals()");*/
	}

}
