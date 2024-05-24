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
	double velocidadY = 2;
	private int contadorSaltos = 0;
	/*VERIFICAR PNG DE SALTO*/
	
	public Jugador(double x, double y) {
		this.x=x;
		this.y=y;
		this.direccion=0;
		this.imagen = new Image[5]; /*quizas sean mas porque son mas imagenes*/
		
		for (int i = 0; i < imagen.length;i++ ) {
			imagen[i] = Herramientas.cargarImagen("kratos" + i +".png"); //<--- agregar imagenes
		}
		
	}
	void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imagen[this.direccion], this.x, this.y, 0, 0.15); /*O 0.15*/
	}
	
	public void mover(int d) {
		this.direccion=d;

		if(direccion==0 ) {
			x-=velocidad;
		}
		if(direccion==1 ) {
			x+=velocidad;
		}

//		if(direccion==3) {
//			y-=velocidad;
//		}

//		if(direccion==5) {
//			y-=velocidad;
//		}
//		
		//limite del PJ
		if (this.x>883) {
			x=883;
		}
		if (this.x<99) {
			x=99;
		}
		if(this.y>616) {
			y=616;
		}
		if(this.y<20) {
			y=20;
		}
	}	
		/*para saltar*/
	public void saltar(int d) {
		
		this.direccion=d;
		if(contadorSaltos < 1 &&  direccion==2) {
			velocidadY-= 18; 
			contadorSaltos++;
		}
	
		
	}
	
	public void caer() {
		this.y+=velocidadY;
		this.velocidadY+=0.5;
		if(this.y>616) {
			y=616;
			velocidadY=5;
			contadorSaltos=0;
		}
	
	}
	
//	public void quieto(int d) { /*UNA IDEA PARA LAS ANIMACIONES*/ 
//		this.direccion=d;
//		if(direccion == 1) {
//			x+=0;
//		}
//		if(direccion==4) {
//			x-=0;
//		}
//	}
//	
}
