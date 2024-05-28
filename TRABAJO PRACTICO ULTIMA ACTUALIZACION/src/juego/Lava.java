package juego;

import java.awt.Color;
import entorno.Entorno;

public class Lava {
    double x, y, ancho, alto, velocidadY;
    Color colorLava;

    public Lava(int x, int y, double velocidadY) {
        this.ancho = 980; // Ancho de la pantalla
        this.alto = 680; // Altura de la lava (ajustar según tus necesidades)
        this.x = x;
        this.y = 1150;
        this.velocidadY = velocidadY;
        this.colorLava = Color.ORANGE; // Cambiando el color a naranja
    }

    public void moverse() {
        this.y -= velocidadY; // La lava se mueve hacia arriba
    }

    public void dibujarse(Entorno entorno) {
        // Dibujar el rectángulo que representa la lava
        entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, this.colorLava);
    }

    public boolean colisionConJugador(Jugador jugador) {
        double distanciaY = Math.abs(y - jugador.getY());

        if (distanciaY == 0) {
            return true;
        }
        return false;
    }
    public void respawnear() {
        this.y = 1150; // Restablecer la posición Y a la inicial
    }
}