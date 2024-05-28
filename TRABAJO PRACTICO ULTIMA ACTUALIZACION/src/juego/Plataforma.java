//package juego;
//
//import entorno.Entorno;
//import java.util.Random;
//
//public class Plataforma {
//    Bloque[] plataformas;
//
//    public Plataforma(int posX, int posY, int numBloques, int bloqAncho, int bloqAlto) {
//        if (numBloques <= 0) {
//            throw new IllegalArgumentException("El numero de bloques debe ser positivo");
//        }
//        plataformas = new Bloque[numBloques];
//        boolean[] bloqRompibles = new boolean[numBloques];
//
//        int numBloqRompibles=2;
//        Random bloqRompibleRandom = new Random();
//        for (int i = 0; i < numBloqRompibles; i++) {
//            int breakableIndex = bloqRompibleRandom.nextInt(numBloques);
//            while (bloqRompibles[breakableIndex]) {
//                breakableIndex = bloqRompibleRandom.nextInt(numBloques);
//            }
//            bloqRompibles[breakableIndex] = true;
//        }
//
//        for (int i = 0; i < numBloques; i++) {
//            boolean rompible = bloqRompibles[i];
//            Bloque bloque = new Bloque(posX + i * bloqAncho, posY, rompible, bloqAncho, bloqAlto);
//            plataformas[i] = bloque;
//        }
//    }
//
//    public void dibujar(Entorno g) {
//        for (Bloque bloque : plataformas) {
//            bloque.dibujarse(g);
//        }
//    }
//
//    
//}
