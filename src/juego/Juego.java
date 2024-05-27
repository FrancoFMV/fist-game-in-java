package juego;

import java.awt.Color;
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
	Image vida;
	Image logo;
	Image puntajes;
	Image enemigosDert;
	Bloque[] bloque;
	ArrayList<Proyectil> proyectilesJugador = new ArrayList<Proyectil>();
	ArrayList<Proyectil> proyectilesDino = new ArrayList<Proyectil>();
	
	long cooldownDino0 = 0L;
	long cooldownDino1 = 0L;
	long cooldownDino2 = 0L;
	long cooldownDino3 = 0L;
	long cooldownDino4 = 0L;
	long cooldownDino5 = 0L;
	
	int respawnDino0=50;
	int respawnDino1=50;
	int respawnDino2=50;
	int respawnDino3=50;
	int respawnDino4=50;
	int respawnDino5=50;
	
	long currentTime;
	boolean proyectilEnPantalla = false;
	
	int vidasJugador=3;
	int puntaje=0;
	int enemigosDerrotados=0;
	int respawnJugador=50;
	
	
	
	Juego() {

		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "TP1 - Grupo 14", 980, 680);

		fondo = Herramientas.cargarImagen("background.jpg");
		kratos = new Jugador(785,600);  /*<--- Ajustar posicion*/
		vida = Herramientas.cargarImagen("vida.png");
		logo=Herramientas.cargarImagen("kratosLogo.jpg");
		puntajes=Herramientas.cargarImagen("puntaje.png");
		enemigosDert= Herramientas.cargarImagen("enemigosDerrotados.png");
		Random random = new Random();
		
		/*PARA PONER LA CANT. DE VIDAS DE KRATOS*/
	
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
			
        /*PARA ESTABLECER LA POSICION DE LOS DINOSAURIOS*/
		dino = new Enemigos[6];
		int posXDino0 = random.nextInt(916-98) + 98;   /*PARA QUE SPAWNEE DE FORMA RANDOM*/
		int posYDino0 = 450;
		dino[0] = new Enemigos(posXDino0, posYDino0, 0);
		
		int posXDino1= random.nextInt(916-98) + 98;
		int posYDino1= 450;
		dino[1]= new Enemigos(posXDino1,posYDino1, 1);
		
		int posXDino2= random.nextInt(916-98) + 98;
		int posYDino2= 300;
		dino[2] = new Enemigos(posXDino2, posYDino2,2);
		
		int posXDino3= random.nextInt(916-98) + 98 ;
		int posYDino3= 300;
		dino[3] = new Enemigos(posXDino3, posYDino3,3);
		
		int posXDino4= random.nextInt(916-98) + 98 ;
		int posYDino4= 150;
		dino[4] = new Enemigos(posXDino4, posYDino4,4);
		
		int posXDino5= random.nextInt(916-98) + 98 ;
		int posYDino5= 150;
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
			if(d!=null) {
				if(colisionMultipleBloqueEnemigo(bloque, d)==2){
					d.moverse();

				} else {
					d.caerD();
				}
			}
			
		}
		/* VERIFICA LAS COLISIONES DE LOS ENEMIGOS EN LA TERMINAL */


	/*PARA QUE LOS ENEMIGOS DESAPAREZCAN CUANDO LES IMPACTA UN PROYECTIL*/
		if((dino[0] == null) && respawnDino0 > 0) {
			respawnDino0--;
		}else {
			if(dino[0] == null) {
				respawnDino0=50;
			}
		}
		if((dino[1] == null) && respawnDino1 > 0) {
			respawnDino1--;
		}else {
			if(dino[1] == null) 
				respawnDino1=50;
			}
		if((dino[2] == null) && respawnDino2 > 0) {
			respawnDino2--;
		}else {
			if(dino[2] == null) 
				respawnDino2=50;
		}
		if((dino[3] == null) && respawnDino3 > 0) {
			respawnDino3--;
		}else {
			if(dino[3] == null) 
				respawnDino3=50;
			}
		if((dino[4] == null) && respawnDino4 > 0) {
			respawnDino4--;
		}else {
			if(dino[4] == null) 
				respawnDino4=50;
			}
		if((dino[5] == null) && respawnDino5 > 0) {
			respawnDino5--;
		}else {
			if(dino[5] == null) 
				respawnDino5=50;
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
		
		/*TEMA DE LAS VIDAS*/
		if((kratos == null) && respawnJugador > 0) {
			respawnJugador--;
		}else {
			if(kratos == null && vidasJugador > 0) {
				kratos = new Jugador(785,616);
				respawnJugador=50;
			}
		}
		/*Tick Movimiento PJ*/
		if ((kratos!=null) && entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			kratos.mover(1);
		}
		if((kratos!=null) && entorno.estaPresionada(entorno.TECLA_IZQUIERDA)){
			kratos.mover(0);
		}

		/* GODMODE - requiere comentar de la linea 252 a 254 (caer)*/
		//*---------------------*/
		if((kratos!=null) && entorno.estaPresionada(entorno.TECLA_ABAJO) && colisionMultipleBloqueJugador(bloque, kratos) !=2 ){
			kratos.mover(4);
		}
		if((kratos!=null) && entorno.estaPresionada(entorno.TECLA_ARRIBA) && colisionMultipleBloqueJugador(bloque, kratos) !=0 ){
			kratos.mover(3);
		}
		//*----------------------*/

//		if(!entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !entorno.estaPresionada(entorno.TECLA_DERECHA)) {
//			kratos.quieto(4);
//		}
//		
		if((kratos!=null) && entorno.estaPresionada(entorno.TECLA_ESPACIO) && !proyectilEnPantalla) { /*VER DE MEJORAR DISPARO*/
			dispararJugador();
			
		}

//		if(colisionMultipleBloque(bloque, kratos)!=3) {
//			System.out.println("colision");
//		}
		if((kratos!=null) && entorno.sePresiono('X') && colisionMultipleBloqueJugador(bloque, kratos) != 0 && colisionMultipleBloqueJugador(bloque, kratos) == 2){
			kratos.saltar(2) ;//<---- PARA QUE SALTE
		}
		//*----------------------*/
		// if((kratos!=null) && colisionMultipleBloqueJugador(bloque, kratos) != 2) {
		// 	kratos.caer();
		// }	
		//*----------------------*/

	
		/* VERIFICA LAS COLISIONES DEL JUGADOR EN LA TERMINAL */
		// if(colisionMultipleBloqueJugador(bloque, kratos)==0) {
		// 	System.out.println("colisionAbajo");
		// }
		// if(colisionMultipleBloque(bloque, kratos)==1) {
		// 	System.out.println("colisionIzquierda");
		// }
		// if(colisionMultipleBloqueJugador(bloque, kratos)==2) {
		// 	System.out.println("colisionArriba");
		// }
		// if(colisionMultipleBloque(bloque, kratos)==3) {
		// 	System.out.println("colisionDerecha");
		// }
//		if(colisionMultipleBloque(bloque, kratos)==5) {
//			System.out.println("sin colision");
//		}
//		
//	
		entorno.dibujarImagen(fondo, 490, 340, 0, 0.78);
		
		/* COLISION CON BLOQUES ROMPIBLES */

		List<Bloque> bloquesTemporales = new ArrayList<>();
       for (int i = 0; i < bloque.length; i++) {
            if (bloque[i] != null) {
                bloquesTemporales.add(bloque[i]);
            }
        }

        List<Integer> bloquesARemover = new ArrayList<>();
        for (int i = 0; i < bloque.length; i++) {
            if (bloque[i] != null && bloque[i].rompible) {
                if (colisionBloqueJugador(bloque[i], kratos) == 0) {
                    System.out.println("Bloque en (" + bloque[i].x + ", " + bloque[i].y + ") es rompible y hay colisión.");
                    bloquesARemover.add(i);
                }
            }
        }

        // Eliminar los bloques marcados
        for (int index : bloquesARemover) {
            bloque[index] = null;
        }


		/* DIBUJA LA PLATAFORMA DE BLOQUES */
		dibujarBloques(bloque);

		int posicion=160;
		for(int i = 0; i < vidasJugador; i++ ) {
			entorno.dibujarImagen(vida, posicion, 60, 0, 0.3);
			posicion+=60;
			if(i == 3) {
				posicion= 160;
			}
			
		}
		
		entorno.dibujarImagen(logo, 85, 68, 0, 0.4);
		/*FALTARIA EL TEMA DEL PUNTAJE*/
		entorno.dibujarImagen(puntajes, 830, 70, 0, 0.3);
		entorno.dibujarImagen(enemigosDert, 832, 120, 0, 0.15);
		entorno.cambiarFont("New york", 30, Color.orange);
		entorno.escribirTexto("" + puntaje ,860 , 75);
		entorno.escribirTexto("" + enemigosDerrotados, 860, 130);
//		for (Plataforma plataforma : plataformas) {
//			plataforma.dibujar(entorno);
//		}
//		/*DIBUJA LOS DISPAROS DEL JUGADOR*/
		for(int i = 0; i < proyectilesJugador.size(); i++) {
			if(!proyectilFueraPantalla(proyectilesJugador.get(i)) && !proyectilChocaConOtro(proyectilesJugador.get(i)) && !proyectilChocaDino(proyectilesJugador.get(i))) {
				proyectilesJugador.get(i).dibujarJugador(this.entorno);
				proyectilesJugador.get(i).mover();
			}
			else {
				proyectilesJugador.remove(i);
			}
			
		}
		proyectilEnPantalla=false;
		for (Proyectil proyectil : proyectilesJugador) {
		    if (!proyectilFueraPantalla(proyectil) ) {
		        proyectilEnPantalla = true;
		        break;
		    }
		    
		}
		/*PARA VERIFICAR LAS COLISIONES CON LOS ENEMIGOS*/
		if(colisionJugadorEnemigo() || jugadorContraProyectil()) {
			vidasJugador--;
			kratos=null;
		}
		if(kratos!=null) {
			kratos.dibujarHitbox(entorno);
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
        for (Bloque b : bloque) {
            if (b != null) {
				b.dibujarHitbox(this.entorno);
                b.dibujarse(this.entorno);
            }
        }
    }
	
	/*FUNCION PARA LAS COLISIONES CON LOS BLOQUES Y EL JUGADOR*/
	public int colisionBloqueJugador (Bloque b, Jugador kratos) {
		double zona1 = b.x-(b.ancho/2); //Izquierda
		double zona3 = b.x+(b.ancho/2); //Derecha
		double zona2 = b.y-(b.alto/2); //Arriba
		double zona0 = b.y+(b.alto/2); //Abajo
		
		if((kratos != null) && kratos.y > zona2-17 && kratos.y < zona0+17 && kratos.x>zona1-32 && kratos.x<zona3+17) {
			return 1; //Colision Izquierda
		}
		if((kratos != null) && kratos.x > zona1-12 && kratos.x < zona3+12 && kratos.y>zona2-22 && kratos.y<zona0+12) {
			return 2; //Colision Arriba
		}
		if((kratos != null) && kratos.x > zona1-12 && kratos.x < zona3+12 && kratos.y>zona2-12 && kratos.y<zona0+22) {
			return 0; //Colision Abajo
		}
		if((kratos != null) && kratos.x > zona1-17 && kratos.x < zona3+32 && kratos.y>zona2-17 && kratos.y<zona0+17) {
			return 3; //Colision Derecha
		}
		return 5; //Sin Colision
	}
 
    public int colisionMultipleBloqueJugador(Bloque[] mb, Jugador l) {
        for (int i = 0; i < mb.length; i++) {
            if (mb[i] != null && colisionBloqueJugador(mb[i], l) != 5) {
                return colisionBloqueJugador(mb[i], l);
            }
        }
        return 5;
    }

	/*FUNCION PARA LAS COLISIONES DE LOS BLOQUES Y UN ENEMIGO*/
	public int colisionBloqueEnemigo(Bloque b, Enemigos enemigo) {
		double zona1 = b.x - (b.ancho / 2); // Izquierda
		double zona3 = b.x + (b.ancho / 2); // Derecha
		double zona2 = b.y - (b.alto / 2); // Arriba
		double zona0 = b.y + (b.alto / 2); // Abajo 

		if (enemigo != null) {
			if (enemigo.y > zona2 - 17 && enemigo.y < zona0 + 17 && enemigo.x > zona1 - 32 && enemigo.x < zona3 + 17) {
				return 1; // Colision Izquierda
			} else if (enemigo.x > zona1 - 12 && enemigo.x < zona3 + 12 && enemigo.y > zona2 - 22 && enemigo.y < zona0 + 12) {
				return 2; // Colision Arriba
			} else if (enemigo.x > zona1 - 12 && enemigo.x < zona3 + 12 && enemigo.y > zona2 - 12 && enemigo.y < zona0 + 22) {
				return 0; // Colision Abajo
			} else if (enemigo.x > zona1 - 17 && enemigo.x < zona3 + 32 && enemigo.y > zona2 - 17 && enemigo.y < zona0 + 17) {
				return 3; // Colision Derecha
			}
		}
		return 5; // Sin Colision
	}


 
	public int colisionMultipleBloqueEnemigo(Bloque[] mb, Enemigos e) {
		for (int i = 0; i < mb.length; i++) {
			if (mb[i] != null) {
				int colision = colisionBloqueEnemigo(mb[i], e);
				if (colision != 5) {
					System.out.println("Colision TRUE");
					return colision;
				}
			}
		}
		System.out.println("Colision FALSE");
		return 5;
	}
	

	
	/*FUNCION PARA EL DISPARO*/
	
	public void dispararJugador() {
		if(kratos!=null) {
			Proyectil bola = new Proyectil(kratos.x, kratos.y, 5, kratos.direccion, 0);
			proyectilesJugador.add(bola);
		}
	}
	
	/*FUNCION PARA DIBUJAR ENEMIGOS*/
	public void dibujarDinos(Enemigos[] dino) {
		for(int i = 0; i < dino.length; i++) {
			if(dino[i]!=null) {
				dino[i].dibujarHitbox(entorno);
				dino[i].dibujarse(entorno);
			}
		}
	}
	/*FUNCION PARA DISPARO ENEMIGO*/
	public void dispararDino(Enemigos d) {
		if(d!=null) {
			Proyectil fuego = new Proyectil(d.x, d.y, 4, d.direccion, 1);
			proyectilesDino.add(fuego);
		}
	}
	/*FUNCIONES PARA LAS COLISIONES DE LOS PROYECTILES, Y JUGADOR-ENEMIGO*/
	public boolean proyectilChocaConOtro(Proyectil j) { 
		for(Proyectil p : this.proyectilesDino) {
			double radioColision = 25.0; /*VERIFICAR VALORES DESPUES POR LAS DUDAS*/
			if(Math.abs(j.x - p.x) < radioColision && Math.abs(j.y-p.y) < radioColision) {
				proyectilesDino.remove(p);
				return true;
			}
		}
		return false;
	}
	public boolean colisionJugadorEnemigo() { /* PROBAR */
		for(Enemigos d : dino) {
			double radioColision= 25.0;
			if((kratos!=null && d !=null) && Math.abs(kratos.x - d.x) < radioColision && Math.abs(kratos.y - d.y) < radioColision ) {
				return true;
			}
		}
		return false;
	}
	
	public boolean jugadorContraProyectil() {
		for(Proyectil p : this.proyectilesDino) {
			double radioColision = 25.0;
			if((kratos!=null) && Math.abs(kratos.x - p.x) < radioColision && Math.abs(kratos.y - p.y) < radioColision) {
				proyectilesDino.remove(p);
				return true;
			}
		}
		return false;
	}
	
	public boolean proyectilChocaDino(Proyectil j) {
		for(int i = 0; i < dino.length; i++) {
			double radioColision = 25.0;
			if((dino[i] != null) && Math.abs(j.x - dino[i].x) < radioColision && Math.abs(j.y - dino[i].y) < radioColision ) {
				puntaje += 2;
				enemigosDerrotados++;
				dino[i] = null;
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
