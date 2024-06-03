package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;	

public class Jugador {
	double x;
	double y;
	double alto, ancho, escala;
	int direccion;
	Image[] imagen;
	double velocidad = 5;
	/* VARIABLE DE INSTNCIA PARA EL SALTO */
	boolean estaSaltando;
	double alturaMaxSalto;
	
	public Jugador(double x, double y) {
		this.x = x;
		this.y = y;
		this.escala = 0.08;
		this.direccion = 0;
		this.imagen = new Image[5]; /* quizas seagn mas porque son mas imagenes */
		estaSaltando=false;
		alturaMaxSalto=0;
		
		
		for (int i = 0; i < imagen.length; i++) {
			imagen[i] = Herramientas.cargarImagen("kratos" + i + ".png");
			this.ancho = imagen[i].getWidth(null) * this.escala/2;
			this.alto = imagen[i].getHeight(null) * this.escala/2;
		}

	}

	void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(imagen[this.direccion], this.x, this.y-5, 0, 0.13);
	}

	void dibujarHitbox(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.RED);
	}

	public void mover(int d) {
		this.direccion = d;

		if (direccion == 0) {
			x -= velocidad;
		}
		if (direccion == 1) {
			x += velocidad;
		}
		// limite del PJ
		if (this.x > 883) {
			x = 883;
		}
		if (this.x < 99) {
			x = 99;
		}
		if (this.y < 30) {
			y = 30;
		}
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
	
	/*SET Y GET BOOLEAN DE SALTO*/
	public void setEstaSaltando(boolean b) {
		this.estaSaltando=b;
	}
	public boolean estaSaltando() {
		return this.estaSaltando;
	}

	public void setAlturaMaxSalto(double i) {
		this.alturaMaxSalto=i;
	}
	
	public void caer(Entorno e) {   
		if(this.y+(this.alto/2) < e.alto()) {			
			this.y+=velocidad;
		}
	}

	
	public void saltar(Entorno e) {  
		if(this.y-(this.alto/2) > 0) {
			if(this.y>this.alturaMaxSalto) {
				this.direccion=2;
				this.y-=5;
			}else {
				this.estaSaltando=false;
			}
		}else {
			this.estaSaltando=false;
		}
	}
	public void correjirColision(Bloque[] bloques) {
		Punto supIzqu= new Punto(this.x-(this.ancho/2), this.y-(this.alto/2));
		Punto supDerch= new Punto(this.x+(this.ancho/2), this.y-(this.alto/2));
		Punto infIzqu= new Punto(this.x-(this.ancho/2), this.y+(this.alto/2));
		Punto infDerch= new Punto(this.x+(this.ancho/2), this.y+(this.alto/2));
		
		for(Bloque bloque : bloques) {
			if(bloque == null) {
				continue;
			}
			if(estaDentro(supIzqu, bloque) || estaDentro(supDerch,bloque)) {	
				this.y=bloque.getY() + (bloque.getAlto()/2)+bloque.getAncho()/2;
			}
			else if(estaDentro(infIzqu, bloque) || estaDentro(infDerch,bloque)){
				this.y= bloque.getY() - (bloque.getAlto()/2)-bloque.getAncho()/2 ;

			}
		}
	}
	private boolean estaDentro(Punto p, Bloque b) {
		if(p.getX() > b.getX() - (b.getAncho()/2) &&  p.getX() < b.getX() + (b.getAncho()/2) &&
				p.getY() > b.getY() -( b.getAlto()/2) && p.getY() < b.getY() + (b.getAlto()/2)) {
			return true;
		}else {
			return false;
		}
	}

}