package juego;

import java.awt.Color;
import entorno.Entorno;
import java.awt.Image;
import entorno.Herramientas;

public class Lava {
    double x, y, ancho, alto, escala, velocidadY;
    Color colorLava;
    Image imagenLava;

    public Lava(int x, int y, double velocidadY) {
        this.ancho = 980; // Ancho de la pantalla
        this.alto = 680; // Altura de la lava (ajustar según tus necesidades)
        this.x = x;
        this.y = 1150;
        this.escala = 1;
        this.velocidadY = velocidadY;
        this.colorLava = Color.ORANGE; // Cambiando el color a naranja

        imagenLava = Herramientas.cargarImagen("Lava.jpg");
        this.ancho = imagenLava.getWidth(null) * this.escala;
        this.alto = imagenLava.getHeight(null) * this.escala;
    }

    public void moverse() {
        this.y -= velocidadY; // La lava se mueve hacia arriba
        
        if(this.y<350) {
        	y=350;
        }
    }
    public void parar() {
    	this.y+=1;
    	
    }

    public void dibujarHitbox(Entorno entorno) {
        // Dibujar el rectángulo que representa la lava
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.colorLava);
    }
    public void dibujarse(Entorno entorno){
        entorno.dibujarImagen(imagenLava, this.x, this.y, 0, this.escala);
    }

    public void respawnear() {
        this.y = 1150; 
    }
  
}