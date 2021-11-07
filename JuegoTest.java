package castro;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pista.Pista;
import pista.PistaSimple;

import avion.Avion;
import avion.AvionSimple;

import copControl.Dificultad;
import copControl.Juego;
import copControl.Jugador;
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;

public class JuegoTest {

	Juego juego;
	Jugador jugador;
	List<Nivel> niveles;
	List<Pista> pistas;
	
	@Before
	public void setUp() throws Exception {
		jugador = new Jugador("Julián Castro");
		niveles = new ArrayList<Nivel>();
		pistas = new ArrayList<Pista>();
		pistas.add(new PistaSimple(new Posicion(100, 75)));
		
		for(int i=0; i<3; i++){
			Nivel nuevoNivel = new Nivel(new Mapa(pistas), new Dificultad(i+2, (20-i), i+5));
			niveles.add(nuevoNivel);
		}
		juego = new Juego(jugador, niveles);
	}

	@Test
	public void JuegoComienzaEnPrimerNivel() {
		assertTrue(juego.getNivelActual().equals(niveles.get(0)));
	}
	
	@Test
	public void JuegoComienzaGanado() {
		assertFalse(juego.esJuegoGanado());
	}
	
	@Test
	public void JuegoComienzaSinAvionesAterrizados() {
		assertTrue(juego.getCantidadAvionesAterrizados() == 0);
	}
	
	@Test
	public void AvionesNuevosSePosicionanEnExtremos() {
		
		Nivel nivelActual = juego.getNivelActual();
		
		while(nivelActual.getAvionesVolando().isEmpty()){
			juego.vivir(); // coloca un avion
		}

		boolean avionesEnExtremos = false;
		
		List<Avion> aviones = nivelActual.getAvionesVolando();
		System.out.println(aviones);
		int dimensionMapa = nivelActual.getMapa().getDimension();
		
		for(Avion avion : aviones){
			Posicion pos = avion.getPosicionActual();
			double posX = pos.getCoordenadaX();
			double posY = pos.getCoordenadaY();
			System.out.println(posX);
			System.out.println(posY);
			boolean coordenadaEnExtremo = posX == 0 || posX == dimensionMapa || posY == 0 || posY == dimensionMapa;
			avionesEnExtremos = avionesEnExtremos || coordenadaEnExtremo;
			
		}

		assertTrue(avionesEnExtremos);
	}

}
