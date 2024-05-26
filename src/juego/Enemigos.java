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
    	this.imagen= new Image[6];
 
    	for (int i = 0; i < imagen.length; i++) {
    		imagen[i] = Herramientas.cargarImagen("dino"+i+".png");
    	}
    }
   public void dibujarse(Entorno entorno) {
	   entorno.dibujarImagen(imagen[this.direccion], this.x, this.y, 0, 0.2);
   }
   public void moverse() {
	  /*AGREGAR EL Y DESPUES SI ES NECESARIO*/
	  // y+=1;						/*PARA QUE CAIGAN LOS ENEMIGOS*/
	   
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
	   if(direccion == 4 && !debeFrenar) {
		   x+=velocidad;
	   }
	   if(direccion == 5 && !debeFrenar) {
		   x-=velocidad;
	   }
	   
	   if(this.x > 916 || this.x<88) {
		  this.direccion = (this.direccion + 1) % 6 ; 
	   }
	   if(this.x<88) {
		   x=88;
		   velocidad+=0;
	   }
	   if(this.y>630) {
		   y=630;
	   }
   
   }
 
    
}
