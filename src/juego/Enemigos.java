package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Enemigos {
    double x;
    double y;
	double alto, ancho, escala;
    Image[] imagen;
    int direccion;
    double velocidad = 2;
    boolean debeFrenar;
    
    public Enemigos(double x, double y, int direccion) {
    	this.x=x;
    	this.y=y;
		this.escala = 0.12;
    	this.direccion=direccion;
    	this.imagen= new Image[6];
 
    	for (int i = 0; i < imagen.length; i++) {
    		imagen[i] = Herramientas.cargarImagen("dino"+i+".png");
			this.ancho = imagen[i].getWidth(null) * ((this.escala/2)-0.02);
			this.alto = imagen[i].getHeight(null) * this.escala/2;
    	}
    }
   public void dibujarse(Entorno entorno) {
	   entorno.dibujarImagen(imagen[this.direccion], this.x+10, this.y, 0, this.escala+0.03);
   }
	public void dibujarHitbox(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.BLUE);
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

   public void caerD(){
		this.y+=velocidad;
   }
 
    
}
