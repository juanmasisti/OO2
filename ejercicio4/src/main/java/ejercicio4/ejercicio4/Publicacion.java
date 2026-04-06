package ejercicio4.ejercicio4;

public class Publicacion {
	 private String texto;
	 private int likes;
	   public Publicacion(String texto) {
	      this.texto = texto;
	      this.likes = 0;
	   }
	   
	   public void darLike() { likes++; }
	   
	   public void darDislike() { likes--; }
	   
	   private int procesar() {
	       return likes * 3;
	   }
	   
	   public int calcular() {
	       return procesar() * 10;
	   }
}
