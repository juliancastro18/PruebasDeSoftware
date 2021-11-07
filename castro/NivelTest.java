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
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;

public class NivelTest {

	Nivel nivel;
	Avion avion1;
	Avion avion2;
	List<Pista> pistas;
	Posicion posicionPista;
	
	@Before
	public void setUp() throws Exception {
		posicionPista = new Posicion(100, 75);
		pistas = new ArrayList<Pista>();
		pistas.add(new PistaSimple(posicionPista));
		nivel = new Nivel(new Mapa(pistas), new Dificultad(1,2,3));
		List<Posicion> posicionesExtremo1 = nivel.getPosicionesExtremos();
		List<Posicion> posicionesExtremo2 = nivel.getPosicionesExtremos();
		avion1 = new AvionSimple(posicionesExtremo1.get(0),posicionesExtremo1.get(1),nivel.getMapa());
		avion2 = new AvionSimple(posicionesExtremo2.get(0),posicionesExtremo2.get(1),nivel.getMapa());
		nivel.colocarAvionEnAire(avion1);
		nivel.colocarAvionEnAire(avion2);
	}

	@Test
	public void AvionesTienenMismaPosicionAlAvanzar() {
		Posicion posAvion1 = avion1.getPosicionActual();
		Posicion posAvion2 = avion2.getPosicionActual();
		nivel.avanzarAvionesEnAire();
		Posicion nuevaPosAvion1 = avion1.getPosicionActual();
		Posicion nuevaPosAvion2 = avion2.getPosicionActual();
		
		assertFalse(posAvion1.igualA(nuevaPosAvion1) && posAvion2.igualA(nuevaPosAvion2));
	}
	
	@Test
	public void DetectaAvionesColocadosEnAire(){
		Mapa mapa = nivel.getMapa();
		assertTrue(mapa.getAvionesEnAire().size() == 2);
	}
	
	@Test
	public void AterrizaAvionEncimaDePista(){
		List<Posicion> posicionesExtremo = nivel.getPosicionesExtremos();
		// instancio el avion para posicionarlo en la misma ubicacion que la pista
		Avion avion3 = new AvionSimple(this.posicionPista, posicionesExtremo.get(0), nivel.getMapa());
		nivel.colocarAvionEnAire(avion3);
		assertTrue(nivel.aterrizarAviones() == 1);
	}
	
	@Test 
	public void ColisionAvionesTrayectoriasOpuestas(){
		Posicion pos1 = new Posicion(25, 250);
		Posicion pos2 = new Posicion(475, 250);
		Avion avion1 = new AvionSimple(pos1,pos2,nivel.getMapa());
		Avion avion2 = new AvionSimple(pos2,pos1,nivel.getMapa());
		nivel.colocarAvionEnAire(avion1);
		nivel.colocarAvionEnAire(avion2);
		int i = 0;
		while(!nivel.huboChoque() && i<500){
			nivel.avanzarAvionesEnAire();
			i++;
		}
		
		assertTrue(nivel.huboChoque());
	}

}
