package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Enemigos {
    double x;
    double y;
    Image[] imagen;
    int direccion;
    double velocidad = 2;
    boolean debeFrenar;
    
    public Enemigos(double x, double y, int direccion) {
    	this.x=x;
    	this.y=y;
    	this.direccion=direccion;
    	this.imagen= new Image[4];
    	
    	for (int i = 0; i < imagen.length; i++) {
    		imagen[i] = Herramientas.cargarImagen("dino"+i+".png");
    	}
    }
   public void dibujarse(Entorno entorno) {
	   entorno.dibujarImagen(imagen[this.direccion], this.x, this.y, 0, 0.26);
   }
   public void moverse() {
	  /*AGREGAR EL Y DESPUES SI ES NECESARIO*/
	   if(direccion == 0 && !debeFrenar) {
		   x+=velocidad;
	   }
	   if(direccion==1 && !debeFrenar) {
		   x-=velocidad;
	   }
	   if(direccion==2 && !debeFrenar) {
		   x+=velocidad;
	   }
	   if(direccion==3 && !debeFrenar) {
		   x-=velocidad;
	   }
	   if(this.x > 913 || this.x<98) {
		   velocidad*=-1;
	   }
	   if(this.x<98) {
		   x=98;
		   velocidad+=0;
	   }
   
   }
    
}
