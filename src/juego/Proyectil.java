package juego;


import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	double x;
	double y;
	double velocidad;
	int direccion;
	Image imagenJ;
	Image imagenD;
	
	
	public Proyectil(double x, double y, double velocidad, int direccion,int tipo) {
		this.x = x;
		this.y = y;
		this.velocidad=velocidad;
		this.direccion=direccion;
		
		if(tipo==0) {
			imagenJ = Herramientas.cargarImagen("bola"+ tipo + ".png");
		}else {
			imagenD = Herramientas.cargarImagen("bola"+ tipo + ".png");
		}		
	}
	
	 public void mover() {
	     if(direccion == 0) {
	      x-= velocidad;
	     }
	     if(direccion == 1) {
	      x+= velocidad;
	     }
	     if(direccion == 2) {
	      x+= velocidad;
	     }
	    }
	 public void moverD() {
		 if(direccion == 0) {  
			 x+=velocidad;
		 }
		 if(direccion == 1) {
			 x-=velocidad;
		 }
		 if(direccion == 2) {
			 x+=velocidad;
		 }
		 if(direccion == 3) {
			 x-=velocidad;
		 }
		 if(direccion == 4) {
			 x+=velocidad;
		 }
		 if(direccion == 5) {
			 x-=velocidad;
		 }
	 }
	 public void dibujarJugador(Entorno entorno) {
		 entorno.dibujarImagen(imagenJ, x, y, 0, 0.14);
	 }

	 public void dibujarseDino(Entorno entorno) {
		 entorno.dibujarImagen(imagenD, x, y, 0, 0.15);
	 }
}