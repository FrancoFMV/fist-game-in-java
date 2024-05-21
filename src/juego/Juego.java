package juego;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	Jugador kratos;
	Enemigos[] dino;
	Image fondo;
	ArrayList<Proyectil> proyectilesJugador = new ArrayList<Proyectil>();
	List<Plataforma> plataformas;
	long cooldownJugador = 0L;
	long currentTime;
	

	
	
	
	Juego() {

		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "TP1 - Grupo 14", 980, 680);

		fondo = Herramientas.cargarImagen("background.jpg");
		kratos = new Jugador(785,610);  /*<--- Ajustar posicion*/

		plataformas = new ArrayList<>();
        // Crear 5 plataformas en diferentes posiciones
        for (int i = 0; i < 5; i++) {
            plataformas.add(new Plataforma(100, 100 + i * 100, 10)); // 10 bloques por plataforma
        }
        /*PARA DIBUJAR A LOS DINOSAURIOS*/
		dino = new Enemigos[2];
		int posXDino0 = 96;
		int posYDino0 = 500;
		dino[0] = new Enemigos(posXDino0, posYDino0, 0);
		
		int posXDino1= 910;
		int posYDino1= 500;
		dino[1]= new Enemigos(posXDino1,posYDino1, 1);

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
		/*TICK MOVIMIENTO DE ENEMIGO*/
		for(Enemigos d : this.dino) {
			dino[d.direccion].moverse();
		}
		
		
		/*Tick Movimiento PJ*/
		if (entorno.estaPresionada(entorno.TECLA_DERECHA) && (!entorno.estaPresionada(entorno.TECLA_ABAJO) && !entorno.estaPresionada(entorno.TECLA_ARRIBA))) {
			kratos.mover(2);
		}
		if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)&& (!entorno.estaPresionada(entorno.TECLA_ABAJO) && !entorno.estaPresionada(entorno.TECLA_ARRIBA))){
			kratos.mover(0);
		}
//		if(!entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !entorno.estaPresionada(entorno.TECLA_DERECHA)&&!entorno.estaPresionada('X')) {
//			kratos.mover(1);
//		
//		}
//		
		if(entorno.estaPresionada(entorno.TECLA_ESPACIO) && currentTime - cooldownJugador >=500) {
			dispararJugador();
			cooldownJugador = currentTime;
		}
		if(entorno.estaPresionada('X')){
			kratos.mover(3); //<---- PARA QUE SALTE
		}else {
			kratos.caer();
		}
		
		entorno.dibujarImagen(fondo, 490, 340, 0, 0.78);

		// Dibuja las plataformas
//		for (Plataforma plataforma : plataformas) {
//			plataforma.dibujar(entorno);
//		}
//		
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
		
		/*DIBUJAR DINO*/
		dibujarDinos(dino);
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
		Proyectil bola = new Proyectil(kratos.x, kratos.y, 5, kratos.direccion, 0);
		proyectilesJugador.add(bola);
	}
	
	/*FUNCION PARA DIBUJAR ENEMIGOS*/
	public void dibujarDinos(Enemigos[] dino) {
		for(int i = 0; i < dino.length; i++) {
			dino[i].dibujarse(entorno);
		}
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
