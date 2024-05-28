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
	boolean saltando = false;
	Image[] imagen;
	double velocidad = 5;
	/* VARIABLE DE INSTNCIA PARA EL SALTO */
	double velocidadY = 0;
	private int contadorSaltos = 0; /* VERIFICAR PNG DE SALTO */

	public Jugador(double x, double y) {
		this.x = x;
		this.y = y;
		this.escala = 0.15;
		this.direccion = 0;
		this.imagen = new Image[5]; /* quizas seagn mas porque son mas imagenes */

		for (int i = 0; i < imagen.length; i++) {
			imagen[i] = Herramientas.cargarImagen("kratos" + i + ".png"); // <--- agregar imagenes
			this.ancho = imagen[i].getWidth(null) * this.escala / 2;
			this.alto = imagen[i].getHeight(null) * this.escala /2;
		}

	}

	void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imagen[this.direccion], this.x, this.y, 0, this.escala); /* O 0.15 */
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
		// if(this.y>616) {
		// y=616;
		// resetSalto();
		// }
		if (this.y < 20) {
			y = 20;
		}
	}

	/* para saltar */
	// public void saltar(int d) {
	// 	this.direccion = d;
	// 	if (contadorSaltos < 1 && direccion == 2) {
	// 		velocidadY -= 18;
	// 		contadorSaltos++;
	// 	}
	//}

	public void caer() {
		y += velocidad;
		saltando = false;
	}

	/* para saltar ver.2*/
	public void saltar() {
		y -= velocidad; // Ajusta la velocidad de salto según sea necesario
		saltando = true;
	
	}

	// public void resetSalto() {
	// 	caer();
	// 	saltando = false;
	// }

	// public void gravedad() {
	// 	this.y += velocidadY;
	// 	// velocidadY += 0.5; // Gravedad

	// 	// Limitar la velocidad de caída
		// if (velocidadY > 10) {
		// 	velocidadY = 10;
		// }
	// }



	// public void quieto(int d) { /*UNA IDEA PARA LAS ANIMACIONES*/
	// 	this.direccion=d;
	// 	if(direccion == 1) {
	// 		x+=0;
	// 	}
	// 	if(direccion==4) {
	// 		x-=0;
	// 	}
	// }

}