package juego;

import java.awt.Image;

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
	/*VERIFICAR PNG DE SALTO*/
	
	public Jugador(double x, double y) {
		this.x=x;
		this.y=y;
		this.direccion=0;
		this.imagen = new Image[6]; /*quizas sean mas porque son mas imagenes*/
		
		for (int i = 0; i < imagen.length;i++ ) {
			imagen[i] = Herramientas.cargarImagen("kratos" + i +".png"); //<--- agregar imagenes
		}
		
	}
	void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imagen[this.direccion], this.x, this.y, 0, 0.2); /*O 0.15*/
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
		if(direccion==3) {
			y-=velocidad;
		}
		if(direccion==4) {
			
		}
		if(direccion==5) {
			y-=velocidad;
		}
		

		//limite del PJ
		if (this.x>864) {
			x=864;
		}
		if (this.x<117) {
			x=117;
		}
		if(this.y>610) {
			y=610;
		}
		if(this.y<20) {
			y=20;
		}
	}	
		/*para saltar*/
	public void saltar() {
		y-=velocidad;
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
		
		if(this.y>608) {
			y=608;
		}
		if(this.y<20) {
			y=20;
		}
	}
	
	
}
