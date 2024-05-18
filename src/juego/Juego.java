package juego;

import java.awt.Image;
import java.util.ArrayList;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	Jugador kratos;
	Image fondo;
	ArrayList<Proyectil> proyectilesJugador = new ArrayList<Proyectil>();
	long cooldownJugador = 0L;
	long currentTime;
	

	
	
	
	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "God of war 2D", 800, 600);


		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "TP1 - Grupo 14 - v1", 800, 600);
		fondo = Herramientas.cargarImagen("fondov.jpg");
		kratos = new Jugador(400,500);  /*<--- Ajustar posicion*/
		// Inicializar lo que haga falta para el juego
		// ...
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y
	 * por lo tanto es el método más importante de esta clase. Aquí se debe
	 * actualizar el estado interno del juego para simular el paso del tiempo
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		currentTime = System.currentTimeMillis();
		/*Tick Movimiento PJ*/
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) && (!entorno.estaPresionada(entorno.TECLA_ABAJO) && !entorno.estaPresionada(entorno.TECLA_ARRIBA))) {
			kratos.mover(2);
		}
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)&& (!entorno.estaPresionada(entorno.TECLA_ABAJO) && !entorno.estaPresionada(entorno.TECLA_ARRIBA))){
			kratos.mover(0);
		}
		if(entorno.estaPresionada(entorno.TECLA_ESPACIO) && currentTime - cooldownJugador >=500) {
			dispararJugador();
			cooldownJugador = currentTime;
		}
		if(entorno.estaPresionada('X')){
			kratos.saltar(); //<---- PARA QUE SALTE
		}else {
			kratos.caer();
		}
		
		entorno.dibujarImagen(fondo, 400, 300, 0, 0.7);
		
		for(int i = 0; i < proyectilesJugador.size(); i++) {
			if(!proyectilFueraPantalla(proyectilesJugador.get(i))) {
				proyectilesJugador.get(i).dibujarJugador(this.entorno);
				proyectilesJugador.get(i).mover();
			}
			else {
				proyectilesJugador.remove(i);
			}
			
		}
		kratos.dibujarse(this.entorno);
	}
	private boolean proyectilFueraPantalla(Proyectil p) {

    	if (p.x>1150) {  //<--- ver de modificar esto
			return true;
		}
		if (p.x<-50) {
			return true;
		}
		if(p.y>1000) {
			return true;
		}
		if(p.y<-50) {
			return true;
		}
		return false;
    }
	

	/*FUNCION PARA EL DISPARO*/
	
	public void dispararJugador() {
		Proyectil bola = new Proyectil(kratos.x,kratos.y, 5 , kratos.direccion, 0);
		proyectilesJugador.add(bola);
	}
	
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
