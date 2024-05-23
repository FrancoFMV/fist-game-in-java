package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Bloque {
    double x, y, ancho, alto, escala;
    boolean rompible;
    Image bloqueRomp, bloqueIromp;

    public Bloque(int x, int y, boolean rompible) {
        this.x = x;
        this.y = y;
        this.rompible = rompible;
        this.escala = 0.3;


        bloqueRomp = Herramientas.cargarImagen("lava_breackeable_block.jpg");
        bloqueIromp = Herramientas.cargarImagen("lava_block.jpg");
        this.ancho=bloqueRomp.getWidth(null)*this.escala;
        this.ancho=bloqueIromp.getWidth(null)*this.escala;
		this.alto=bloqueRomp.getHeight(null)*this.escala;
		this.alto=bloqueIromp.getHeight(null)*this.escala;
    }

    public void dibujarse(Entorno entorno) {
        if (rompible) {
            entorno.dibujarImagen(bloqueRomp, this.x, this.y, 0, 0.7);
        } else {
            entorno.dibujarImagen(bloqueIromp, this.x, this.y, 0, 0.2);
        }
    }
}
