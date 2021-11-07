package castro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import copControl.Dificultad;

public class DificultadTest {

	Dificultad d;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void NoPermiteCrearDificultadConVelocidadNegativa() {
		int velocidad = -3;
		d = new Dificultad(3, 5, velocidad);
		
		assertFalse(d.getVelocidad() < 0 );
	}
	
	@Test
	public void DificultadIgualSumaDeParametros() {
		int cantidadDeAvionesPorAterrizar = 10;
		int frecuenciaDeAparicion = 5;
		int velocidad = 3;
		d = new Dificultad(cantidadDeAvionesPorAterrizar, frecuenciaDeAparicion, velocidad);
		
		int sumaParametros = cantidadDeAvionesPorAterrizar + frecuenciaDeAparicion + velocidad;
		
		assertTrue(d.getValorDeDificultad() == sumaParametros);
	}

}
