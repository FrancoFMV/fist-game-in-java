package juego;

import java.awt.Image;
<<<<<<< HEAD

import entorno.Entorno;
import entorno.Herramientas;

public class Jugador {
	double x;
	double y;
	int direccion;
	Image[] imagen;
	double velocidad = 5;
	/*VARIABLE DE INSTNCIA PARA EL SALTO*/
	boolean saltando;
	
	
	public Jugador(double x, double y) {
		this.x=x;
		this.y=y;
		this.direccion=0;
		this.imagen = new Image[4]; /*quizas sean mas porque son mas imagenes*/
		
		for (int i = 0; i < imagen.length;i++ ) {
			imagen[i] = Herramientas.cargarImagen("cuadrado"+ i + ".png"); //<--- agregar imagenes
		}
	}
	void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imagen[this.direccion], this.x, this.y, 0, 0.3);
	}
	public void mover(int d) {
		this.direccion=d;

		if(direccion==0) {
			x-=velocidad;
		}
		if(direccion==1) {
			y+=velocidad;
		}
		if(direccion==2) {
			x+=velocidad;
		}
//		if(direccion==3) {
//			y-=velocidad;
//		}

		//limite del PJ
		if (this.x>1080) {
			x=1080;
		}
		if (this.x<20) {
			x=20;
		}
		if(this.y>500) {
			y=500;
		}
		if(this.y<20) {
			y=20;
		}
	}	
		/*para saltar*/
	public void saltar() {
			if(direccion==3) {
				y+=velocidad;
			}else {
				y-=velocidad;
			}
			if (this.x>1080) {
				x=1080;
			}
			if (this.x<20) {
				x=20;
			}
			if(this.y>500) {
				y=500;
			}
			if(this.y<20) {
				y=20;
			}
		}
	
	public void caer() {
		y+=velocidad;
		
		if(this.y>500) {
			y=500;
		}
		if(this.y<20) {
			y=20;
		}
	}
	
	
}
=======
import entorno.Entorno;
import entorno.Herramientas;

public class Jugador{
    double x;
	double y;
	int direccion;
	Image[] imagen;
	double velocidad = 4;
}
>>>>>>> a49a320cf14dde0d8f491fed72b3ea914f2a763a
