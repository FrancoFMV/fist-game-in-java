package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Bloque {
    double x, y, ancho, alto, escala;
    boolean rompible = false;
    Image bloqueRomp, bloqueIromp;

    public Bloque(int x, int y, boolean rompible) {
        this.x = x;
        this.y = y;
        this.rompible = rompible;
        this.escala = 0.15;

        bloqueRomp = Herramientas.cargarImagen("lava_breackeable_block.png");
        bloqueIromp = Herramientas.cargarImagen("lava_block.png");
        this.ancho = bloqueRomp.getWidth(null) * this.escala;
        this.ancho = bloqueIromp.getWidth(null) * this.escala;
        this.alto = bloqueRomp.getHeight(null) * this.escala;
        this.alto = bloqueIromp.getHeight(null) * this.escala;
    }
 
    public void dibujarse(Entorno entorno) {
        if (rompible) {
            entorno.dibujarImagen(bloqueRomp, this.x, this.y, 0, this.escala);
        } else {
            entorno.dibujarImagen(bloqueIromp, this.x, this.y, 0, this.escala);
        }
    }

    void dibujarHitboxRomp(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
    }

    void dibujarHitboxIromp(Entorno entorno) {
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.darkGray);
    }

    public double getY() {
		return y;
	}
	public void setY(double y) { 
		this.y=y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) { 
		this.x=x;
	}
	public double getAlto() {     
		return alto;
	} 
	public double getAncho() {   
		return ancho;
	}
    
}
