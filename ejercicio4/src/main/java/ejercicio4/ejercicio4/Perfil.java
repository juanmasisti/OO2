package ejercicio4.ejercicio4;

import java.util.ArrayList;

public class Perfil {
	private boolean verificado;
	private ArrayList<Publicacion> publicaciones;
	   public Perfil(boolean verificado) {
	        this.verificado = verificado;
	        this.publicaciones = new ArrayList<>();
	   }
	   
	   // Rename parameter: el parámetro “p” del método agregarPublicacion a "publicacion", también actualizar el cuerpo del método donde se lo usa
	   public void agregarPublicacion(Publicacion publicacion) { publicaciones.add(publicacion); }
	   
	   private int bonus() { return verificado ? 2 : 1; }
	   
	   private int alcanceDePublicaciones() {
		   // Rename Method: Cambiar la invocación dentro del stream de alcanceDePublicaciones()
	       return publicaciones.stream().mapToInt(p -> p.alcance()).sum();
	   }
	   
	   // Rename Method: Cambiar la firma del método de public int calcular() a public int alcance().
	   public int alcance() {
	       return alcanceDePublicaciones() * bonus();
	   }

}
