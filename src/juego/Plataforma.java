package juego;

import java.util.ArrayList;
import java.util.List;
import entorno.Entorno;
import entorno.Herramientas;

public class Plataforma {
    List<Bloque> bloques;

    public Plataforma(int x, int y, int numBloques) {
        bloques = new ArrayList<>();
        for (int i = 0; i < numBloques; i++) {
            // Crear dos bloques rompibles y el resto indestructibles
            boolean rompible = (i < 2);
            Bloque bloque = new Bloque(x + i * 50, y, rompible); // Suponiendo que cada bloque tiene un ancho de 50
            bloques.add(bloque);
        }
    }

    public void dibujar(Entorno g) {
        for (Bloque bloque : bloques) {
            bloque.dibujarse(g);
        }
    }

    // Otros métodos según sea necesario, como verificar colisiones, etc.
}
