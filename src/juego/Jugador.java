package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;	

public class Jugador {
	double x;
	double y;
	double alto, ancho, escala;
	int direccion;
	Image[] imagen;
	double velocidad = 5;
	/* VARIABLE DE INSTNCIA PARA EL SALTO */
	boolean estaSaltando;
	double velocidadY = 0;
	double alturaMaxSalto;
	Bloque[] bloques;
	
	public Jugador(double x, double y) {
		this.x = x;
		this.y = y;
		this.escala = 0.12;
		this.direccion = 0;
		this.imagen = new Image[5]; /* quizas seagn mas porque son mas imagenes */
		estaSaltando=false;
		alturaMaxSalto=0;
		
		
		for (int i = 0; i < imagen.length; i++) {
			imagen[i] = Herramientas.cargarImagen("kratos" + i + ".png"); // <--- agregar imagenes
			this.ancho = imagen[i].getWidth(null) * this.escala / 2;
			this.alto = imagen[i].getHeight(null) * this.escala /2;
		}

	}

	void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imagen[this.direccion], this.x, this.y-5, 0, this.escala);
	}

	void dibujarHitbox(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.RED);
	}

	public void mover(int d) {
		this.direccion = d;

		if (direccion == 0) {
			x -= velocidad;
		}
		if (direccion == 1) {
			x += velocidad;
		}
		// ---------------------
		if (direccion == 3) {
			y -= velocidad;
		}

		if (direccion == 4) {
			y += velocidad;
		}
		// ---------------------

		// limite del PJ
		if (this.x > 883) {
			x = 883;
		}
		if (this.x < 99) {
			x = 99;
		}
		if (this.y < 30) {
			y = 30;
		}
	}
	
	public double getY() {
		return y;
	}
	public void setY(double y) { //<-- X LAS DUDAS
		this.y=y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) { //<-- X LAS DUDAS
		this.x=x;
	}
	public double getAlto() {     //<-- X LAS DUDAS
		return alto;
	} 
	public double getAncho() {  //< --- X LAS DUDAS
		return ancho;
	}
	/*SET Y GET BOOLEAN DE SALTO*/
	public void setEstaSaltando(boolean b) {
		this.estaSaltando=b;
	}
	public boolean estaSaltando() {
		return this.estaSaltando;
	}

	public void setAlturaMaxSalto(double i) {
		this.alturaMaxSalto=i;
	}
	
	public void caer(Entorno e) {   //<-- O moverHaciaAbajo()
		if(this.y+(this.alto/2) < e.alto()) {
			// this.direccion=2;
			this.y+=velocidad;
		}
	}

	/* para saltar ver.2*/
	public void saltar(Entorno e) {  //  <-- O moverHaciaArriba()
		if(this.y-(this.alto/2) > 0) {
			if(this.y>this.alturaMaxSalto) {
				this.direccion=2;
				this.y-=5;
			}else {
				this.estaSaltando=false;
			}
		}else {
			this.estaSaltando=false;
		}
		
	
	}
	public void correjirColision(Bloque[] bloques) {
		Punto supIzqu= new Punto(this.x-(this.ancho/2), this.y-(this.alto/2));
		Punto supDerch= new Punto(this.x+(this.ancho/2), this.y-(this.alto/2));
		Punto infIzqu= new Punto(this.x-(this.ancho/2), this.y+(this.alto/2));
		Punto infDerch= new Punto(this.x+(this.ancho/2), this.y+(this.alto/2));
		
		for(Bloque bloque : bloques) {
			if(bloque == null) {
				continue;
			}
			if(estaDentro(supIzqu, bloque) || estaDentro(supDerch,bloque)) {	//VER ESTO
				estaSaltando();
			}
			else if(estaDentro(infIzqu, bloque) || estaDentro(infDerch,bloque)){
				estaSaltando();
			}
		}
	}
	private boolean estaDentro(Punto p, Bloque b) {
		return(p.getX() > b.getX() - (b.getAncho()/2) &&  p.getX() < b.getX() + (b.getAncho()/2) &&
				p.getY() > b.getY() -( b.getAlto()/2) && p.getY() < b.getY() + (b.getAlto()/2)); 
	}

	// public void quieto(int d) { /*UNA IDEA PARA LAS ANIMACIONES*/
	// 	this.direccion=d;
	// 	if(direccion == 1) {
	// 		x+=0;
	// 	}
	// 	if(direccion==4) {
	// 		x-=0;
	// 	}
	// }

	// Método para reposicionar Kratos fuera de un bloque si está dentro de uno
	public void reposicionarFueraDeBloque(Bloque bloque) {
		Punto kratosCentro = new Punto(this.x, this.y);
		double bloqueIzquierda = bloque.getX() - (bloque.getAncho() / 2);
		double bloqueDerecha = bloque.getX() + (bloque.getAncho() / 2);
		double bloqueArriba = bloque.getY() - (bloque.getAlto() / 2);
		double bloqueAbajo = bloque.getY() + (bloque.getAlto() / 2);

		// Calcula las distancias a cada lado del bloque
		double distanciaIzquierda = Math.abs(kratosCentro.getX() - bloqueIzquierda);
		double distanciaDerecha = Math.abs(bloqueDerecha - kratosCentro.getX());
		double distanciaArriba = Math.abs(kratosCentro.getY() - bloqueArriba);
		double distanciaAbajo = Math.abs(bloqueAbajo - kratosCentro.getY());

		// Determina la menor distancia y reposiciona Kratos fuera del bloque
		double minDistancia = Math.min(Math.min(distanciaIzquierda, distanciaDerecha), Math.min(distanciaArriba, distanciaAbajo));
		if (minDistancia == distanciaIzquierda) {
			this.x = bloqueIzquierda - (this.ancho / 2);
		} else if (minDistancia == distanciaDerecha) {
			this.x = bloqueDerecha + (this.ancho / 2);
		} else if (minDistancia == distanciaArriba) {
			this.y = bloqueArriba - (this.alto / 2);
		} else if (minDistancia == distanciaAbajo) {
			this.y = bloqueAbajo + (this.alto / 2);
		}
	}


}