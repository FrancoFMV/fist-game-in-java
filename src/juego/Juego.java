package juego;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;
import java.util.Random;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	// ...
	Jugador kratos;
	Enemigos[] dino;
	Image fondo;
	Bloque[] bloque;
	ArrayList<Proyectil> proyectilesJugador = new ArrayList<Proyectil>();
	ArrayList<Proyectil> proyectilesDino = new ArrayList<Proyectil>();
	long cooldownJugador = 0L;
	long cooldownJugadorSalto = 0L;
	long cooldownDino0 = 0L;
	long cooldownDino1 = 0L;
	long cooldownDino2 = 0L;
	long cooldownDino3 = 0L;
	long cooldownDino4 = 0L;
	long cooldownDino5 = 0L;
	long currentTime;
	boolean proyectilEnPantalla = false;
	
	
	
	Juego() {

		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "TP1 - Grupo 14", 980, 680);

		fondo = Herramientas.cargarImagen("background.jpg");
		kratos = new Jugador(785,616);  /*<--- Ajustar posicion*/
		Random random = new Random();
		//int posXRandom= random.nextInt(916-98) + 98;

		
		/*PARA ESTABLECER LA POSICION DE LOS BLOQUES*/
		bloque = new Bloque[52];
		int posXbloq = 98;
		int posYbloq = 70;
		for(int i=0; i<bloque.length; i++) {
			boolean rompible = random.nextBoolean();
			bloque[i] = new Bloque(posXbloq, posYbloq, rompible);
			if(posXbloq <= entorno.ancho()-67) {
				posXbloq += 98;
			}
			if(posXbloq > entorno.ancho()-67) {
				posXbloq = 98;
				posYbloq += 150;
			}
		}
		
//		
        /*PARA ESTABLECER LA POSICION DE LOS DINOSAURIOS*/
		dino = new Enemigos[6];
		int posXDino0 = random.nextInt(916-98) + 98;   /*PARA QUE SPAWNEE DE FORMA RANDOM*/
		int posYDino0 = 480;
		dino[0] = new Enemigos(posXDino0, posYDino0, 0);
		
		int posXDino1= random.nextInt(916-98) + 98;
		int posYDino1= 480;
		dino[1]= new Enemigos(posXDino1,posYDino1, 1);
		
		int posXDino2= random.nextInt(916-98) + 98;
		int posYDino2= 330;
		dino[2] = new Enemigos(posXDino2, posYDino2,2);
		
		int posXDino3= random.nextInt(916-98) + 98 ;
		int posYDino3= 330;
		dino[3] = new Enemigos(posXDino3, posYDino3,3);
		
		int posXDino4= random.nextInt(916-98) + 98 ;
		int posYDino4= 180;
		dino[4] = new Enemigos(posXDino4, posYDino4,4);
		
		int posXDino5= random.nextInt(916-98) + 98 ;
		int posYDino5= 180;
		dino[5] = new Enemigos(posXDino5, posYDino5,5);
		
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
		
		if(currentTime - cooldownDino0 >= 4000) {
			dispararDino(dino[0]);
			cooldownDino0 = currentTime;
		}																
		if(currentTime - cooldownDino1 >= 5000) {		/*SOLUCIONAR EL TEMA DE LOS DISPAROS DE LOS ENEMIGOS*/ 
			dispararDino(dino[1]);
			cooldownDino1 = currentTime;
		}
		if(currentTime - cooldownDino2 >=4800) {
			dispararDino(dino[2]);
			cooldownDino2 = currentTime;
		}
		if(currentTime - cooldownDino3 >=3000) {
			dispararDino(dino[3]);
			cooldownDino3 = currentTime;
		}
		if(currentTime - cooldownDino4 >=4500) {
			dispararDino(dino[4]);
			cooldownDino4 = currentTime;
		}
		if(currentTime - cooldownDino5 >=5000) {
			dispararDino(dino[5]);
			cooldownDino5 = currentTime;
		}
//		
		
		/*Tick Movimiento PJ*/
		if ((kratos!=null) && entorno.estaPresionada(entorno.TECLA_DERECHA) && colisionMultipleBloque(bloque, kratos) !=0 ) {
			kratos.mover(1);
		}
		if((kratos!=null) && entorno.estaPresionada(entorno.TECLA_IZQUIERDA)){
			kratos.mover(0);
		}
//		if(!entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !entorno.estaPresionada(entorno.TECLA_DERECHA)) {
//			kratos.quieto(4);
//		}
//		
		if((kratos!=null) && entorno.estaPresionada(entorno.TECLA_ESPACIO) && !proyectilEnPantalla) { /*VER DE MEJORAR DISPARO*/
			dispararJugador();
			//cooldownJugador = currentTime;
		}
//		if(colisionMultipleBloque(bloque, kratos)!=3) {
//			System.out.println("colision");
//		}
		if(entorno.sePresiono('X')){
			kratos.saltar(2) ;//<---- PARA QUE SALTE
		}else {
			kratos.caer();
			}
		
		
		entorno.dibujarImagen(fondo, 490, 340, 0, 0.78);

		/* DIBUJA LA PLATAFORMA DE BLOQUES */
		dibujarBloques(bloque);
		
		
		

//		for (Plataforma plataforma : plataformas) {
//			plataforma.dibujar(entorno);
//		}
//		/*DIBUJA LOS DISPAROS DEL JUGADOR*/
		for(int i = 0; i < proyectilesJugador.size(); i++) {
			if(!proyectilFueraPantalla(proyectilesJugador.get(i))) {
				proyectilesJugador.get(i).dibujarJugador(this.entorno);
				proyectilesJugador.get(i).mover();
			}
			else {
				proyectilesJugador.remove(i);
			}
			
		}
		
		for (Proyectil proyectil : proyectilesJugador) {
		    if (!proyectilFueraPantalla(proyectil) && !proyectilFueraPantalla(proyectil)) {
		        proyectilEnPantalla = true;
		        break;
		    }
		    else {
		    	proyectilEnPantalla=false;
		    }
		}
		if(kratos!=null) {
			kratos.dibujarse(this.entorno);
		}
		/*DIBUJAR DINO*/
		dibujarDinos(dino);
		/*DIBUJA LOS DISPAROS DE LA PLANTA*/
		for(int j = 0; j < proyectilesDino.size();j++) {
			if(!proyectilFueraPantalla2(proyectilesDino.get(j))) {
				proyectilesDino.get(j).dibujarseDino(this.entorno);
				proyectilesDino.get(j).moverD();
			}else {
				proyectilesDino.remove(j);
			}
		}
	}
	private boolean proyectilFueraPantalla(Proyectil p) {

    	if (p.x>920) {  /*LIMITE DEL DISPARO DEL PJ*/
			return true;
		}
		if (p.x<68) {
			return true;
		}
	
		return false;
    }
	private boolean proyectilFueraPantalla2(Proyectil p) {

    	if (p.x>870) {  /*LIMITE DEL DISPARO DE LOS ENEMIGOS(DE ULTIMA USAR UNA IMAGEN CON LOS MISMOS VALORES DEL PJ)*/
			return true;
		}
		if (p.x<68) {
			return true;
		}
	
		return false;
    }
	
	/*FUNCION PARA DIBUJAR BLOQUES*/

	public void dibujarBloques(Bloque[] bloque) {
		for(int i=0; i<bloque.length; i++) {
			bloque[i].dibujarse(this.entorno);
		}
	}
	
	/*FUNCION PARA LAS COLISIONES CON LOS BLOQUES*/
	
	public int colisionBloque (Bloque b, Jugador kratos) {
		double zona1 = b.x-(b.ancho/2);
		double zona3 = b.x+(b.ancho/2);
		double zona2 = b.y-(b.alto/2);
		double zona0 = b.y+(b.alto/2);
		
		
		if((kratos != null) && kratos.y > zona2-75 && kratos.y < zona0+75 && kratos.x>zona1-90 && kratos.x<zona3+75) {
			return 1;
		}
		
		if((kratos != null) && kratos.x > zona1-70 && kratos.x < zona3+70 && kratos.y>zona2-80 && kratos.y<zona0+70) {
			return 2;
		}
		if((kratos != null) && kratos.x > zona1-7 && kratos.x < zona3+7 && kratos.y>zona2-7 && kratos.y<zona0+8) {
			return 0;
		}
		if((kratos != null) && kratos.x > zona1-75 && kratos.x < zona3+90 && kratos.y>zona2-75 && kratos.y<zona0+75) {
			return 3;
		}
		return 5;
		
	}

	public int colisionMultipleBloque(Bloque[] mb, Jugador l) {
		for(int i= 0; i<mb.length; i++) {
			if((l != null) && colisionBloque(mb[i],l) != 5) {
				return colisionBloque(mb[i],l);
			}
		}
		return 5;
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
	/*FUNCION PARA DISPARO ENEMIGO*/
	public void dispararDino(Enemigos d) {
		Proyectil fuego = new Proyectil(d.x, d.y, 4, d.direccion, 1);
		proyectilesDino.add(fuego);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
